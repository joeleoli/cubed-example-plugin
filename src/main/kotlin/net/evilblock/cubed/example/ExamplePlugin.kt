package net.evilblock.cubed.example

import net.evilblock.cubed.command.CommandHandler
import net.evilblock.cubed.example.command.ExampleCommand
import net.evilblock.cubed.example.command.FetchUUIDCommand
import net.evilblock.cubed.example.command.SpawnEntityCommand
import net.evilblock.cubed.example.command.SpawnPlayerNPCCommand
import net.evilblock.cubed.example.command.parameter.EntityTypeParameterType
import net.evilblock.cubed.example.scoreboard.ExampleScoreboard
import net.evilblock.cubed.scoreboard.ScoreboardHandler
import org.bukkit.entity.EntityType
import org.bukkit.plugin.java.JavaPlugin

class ExamplePlugin : JavaPlugin() {

    override fun onEnable() {
        // register our command parameter types
        CommandHandler.registerParameterType(EntityType::class.java, EntityTypeParameterType)

        // register our commands
        CommandHandler.registerClass(ExampleCommand::class.java)
        CommandHandler.registerClass(FetchUUIDCommand::class.java)
        CommandHandler.registerClass(SpawnEntityCommand::class.java)
        CommandHandler.registerClass(SpawnPlayerNPCCommand::class.java)

        // register our scoreboard adapter (title getter, score getter)
        ScoreboardHandler.configure(ExampleScoreboard, ExampleScoreboard)
    }

}