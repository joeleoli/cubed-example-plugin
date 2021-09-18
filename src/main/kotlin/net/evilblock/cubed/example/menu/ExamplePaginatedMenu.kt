package net.evilblock.cubed.example.menu

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.buttons.StaticItemStackButton
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player

class ExamplePaginatedMenu : PaginatedMenu() {

    init {
        updateAfterClick = true
    }

    override fun getPrePaginatedTitle(player: Player): String {
        return "Paginated Menu"
    }

    override fun getAllPagesButtons(player: Player): Map<Int, Button> {
        return hashMapOf<Int, Button>().also { buttons ->
            for (i in 0..36) {
                buttons[i] = StaticItemStackButton(ItemBuilder.of(Material.PAPER).name(i.toString()).build())
            }
        }
    }

    override fun getMaxItemsPerPage(player: Player): Int {
        return 20
    }

}