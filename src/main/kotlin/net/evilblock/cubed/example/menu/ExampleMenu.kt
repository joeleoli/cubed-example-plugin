package net.evilblock.cubed.example.menu

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.buttons.GlassButton
import net.evilblock.cubed.menu.buttons.MenuButton
import net.evilblock.cubed.menu.buttons.TexturedHeadButton
import net.evilblock.cubed.menu.menus.ConfirmMenu
import net.evilblock.cubed.util.bukkit.Constants
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.bukkit.Tasks
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.concurrent.ThreadLocalRandom

class ExampleMenu : Menu() {

    init {
        updateAfterClick = true
        autoUpdate = true
    }

    override fun getTitle(player: Player): String {
        return "Example Menu"
    }

    override fun getButtons(player: Player): Map<Int, Button> {
        return hashMapOf<Int, Button>().also { buttons ->
            buttons[10] = GlassButton(ThreadLocalRandom.current().nextInt(10).toByte()) // glass button for designs

            buttons[11] = ExampleTexturedButton() // textured buttons

            buttons[12] = MenuButton().name { player -> // create a button on the fly
                "hey ${player.name}"
            }
            .icon {
                if (ThreadLocalRandom.current().nextBoolean()) {
                    ItemBuilder(Material.INK_SACK).data(5).build()
                } else {
                    ItemBuilder(Material.INK_SACK).data(7).build()
                }
            }
            .lore { player ->
                return@lore arrayListOf<String>().also { lines ->
                    lines.add("this is cool")
                }
            }
            .action { player, clickType ->
                if (clickType.isLeftClick) {
                    player.sendMessage("hey ${player.name}")
                } else if (clickType.isRightClick) {
                    player.sendMessage("bye ${player.name}")
                    player.closeInventory()
                }
            }
        }
    }

    override fun size(buttons: Map<Int, Button>): Int {
        return 27
    }

    /**
     * If the player manually closes the menu using the ESC button,
     * a confirmation will appear. If the player clicks yes, the menu closes,
     * otherwise the menu will re-open. Just to show functionality...
     */
    override fun onClose(player: Player, manualClose: Boolean) {
        if (manualClose) {
            Tasks.delayed(1L) { // delay by 1 tick
                ConfirmMenu("Exit Menu?") { confirmed ->
                    if (confirmed) {
                        player.closeInventory()
                    } else {
                        Tasks.delayed(1L) {
                            openMenu(player)
                        }
                    }
                }.openMenu(player)
            }
        }
    }

    private class ExampleTexturedButton : TexturedHeadButton(Constants.SKULL_RED_TEXTURE) {
        override fun getName(player: Player): String {
            return "${ChatColor.RED}${ChatColor.BOLD}Red Skull"
        }

        override fun getDescription(player: Player): List<String> {
            return arrayListOf<String>().also { lines ->
                lines.add("${ChatColor.GRAY}A rare item!")
            }
        }
    }

}