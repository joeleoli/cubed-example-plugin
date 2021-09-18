package net.evilblock.cubed.example.command

import net.evilblock.cubed.Cubed
import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import net.evilblock.cubed.entity.EntityHandler
import net.evilblock.cubed.entity.npc.NpcEntity
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

/**
 * Spawns a [NpcEntity] with the given username and texture, where the player is standing.
 */
object SpawnPlayerNPCCommand {

    @Command(
        names = ["spawn-player-npc"],
        description = "Spawns an NPC from a player username",
        permission = "op",
        async = true
    )
    @JvmStatic
    fun execute(player: Player, @Param(name = "player") playerUUID: UUID) {
        val username = Cubed.instance.uuidCache.name(playerUUID)

        // create the npc
        val npc = NpcEntity(lines = listOf(username), location = player.location)

        // update the NPC's texture
        npc.updateTextureByUUID(playerUUID) { success, exception ->
            if (success) {
                player.sendMessage("${ChatColor.GREEN}Successfully applied $username texture to NPC!")
            } else {
                player.sendMessage("${ChatColor.RED}Failed to apply $username texture to NPC!")
            }
        }

        npc.spawn(player) // instantly display to player

        EntityHandler.trackEntity(npc) // track entity (will start rendering to players nearby)
        EntityHandler.saveData() // save to prevent data loss

        player.sendMessage("${ChatColor.GREEN}Successfully spawned NPC!")
    }

}