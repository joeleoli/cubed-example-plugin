package net.evilblock.cubed.example.command

import net.evilblock.cubed.command.Command
import net.evilblock.cubed.example.menu.ExampleMenu
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object ExampleCommand {

    @Command(
        names = ["example", "example-cmd"],
        description = "This is an example command",
    )
    @JvmStatic
    fun execute(player: Player) {
        player.sendMessage("${ChatColor.GREEN}Opening the example menu...")
        ExampleMenu().openMenu(player)
    }

}