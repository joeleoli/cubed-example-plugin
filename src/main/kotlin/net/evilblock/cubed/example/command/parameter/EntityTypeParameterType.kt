package net.evilblock.cubed.example.command.parameter

import net.evilblock.cubed.command.data.parameter.ParameterType
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.lang.Exception

object EntityTypeParameterType : ParameterType<EntityType> {

    override fun transform(sender: CommandSender, source: String): EntityType? {
        return try {
            EntityType.valueOf(source.toUpperCase())
        } catch (e: Exception) {
            sender.sendMessage("${ChatColor.RED}That is not a valid EntityType!")
            null
        }
    }

    override fun tabComplete(player: Player, flags: Set<String>, source: String): List<String> {
        return arrayListOf<String>().also { completions ->
            for (type in EntityType.values()) {
                if (type.name.startsWith(source, ignoreCase = true)) {
                    completions.add(type.name)
                }
            }
        }
    }

}