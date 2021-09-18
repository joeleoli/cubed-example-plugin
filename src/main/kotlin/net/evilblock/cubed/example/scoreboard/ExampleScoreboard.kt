package net.evilblock.cubed.example.scoreboard

import net.evilblock.cubed.scoreboard.ScoreGetter
import net.evilblock.cubed.scoreboard.TitleGetter
import org.apache.commons.lang3.StringUtils
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object ExampleScoreboard : TitleGetter, ScoreGetter {

    private val BAR = "${ChatColor.GRAY}${ChatColor.BOLD}${StringUtils.repeat('-', 20)}"

    override fun getTitle(player: Player): String {
        return "${ChatColor.GREEN}${ChatColor.BOLD}Example Plugin"
    }

    override fun getScores(scores: LinkedList<String>, player: Player) {
        scores.addAll(arrayListOf<String>().also { lines ->
            lines.add(BAR)
            lines.add("Hello there,")
            lines.add(player.name)
            lines.add("")
            lines.add(ThreadLocalRandom.current().nextInt(999).toString())
            lines.add(BAR)
        })
    }

}