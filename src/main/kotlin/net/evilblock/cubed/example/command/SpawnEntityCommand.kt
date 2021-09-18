package net.evilblock.cubed.example.command

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.ChatColor
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

object SpawnEntityCommand {

    @Command(
        names = ["spawn-entity"],
        description = "Spawns an entity of the given type",
        permission = "op"
    )
    @JvmStatic
    fun execute(player: Player, @Param(name = "entityType") entityType: EntityType) {
        try {
            val entity = player.world.spawnEntity(player.location, entityType)
            entity.teleport(player.location)
        } catch (e: Exception) {
            player.sendMessage("${ChatColor.RED}Failed to spawn a $entityType entity!")
            e.printStackTrace()
        }
    }

}