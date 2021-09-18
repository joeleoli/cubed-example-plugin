package net.evilblock.cubed.example.command

import net.evilblock.cubed.Cubed
import net.evilblock.cubed.command.Command
import net.evilblock.cubed.command.data.parameter.Param
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import java.util.*

/**
 * This is an example command that fetches a player's UUID from their username, and can be executed by console
 * or any player who is OP.
 *
 * Cubed makes commands extremely easy to make, and provides a ton of functionality out of the box. For example, the
 * player UUID/name is fetched for you via the use of [net.evilblock.cubed.command.data.parameter.ParameterType]s.
 *
 * The default [net.evilblock.cubed.command.data.parameter.impl.UUIDParameterType] will try fetching the
 * UUID from the cache, if that fails, the Mojang API will be queried as a fallback. Because this adapter
 * might have to run code asynchronously, we set [Command.async] to true.
 *
 * If a UUID cannot be fetched from either source, the parameter adapter will send
 * a message to the sender, and the command will not be processed.
 */
object FetchUUIDCommand {

    @Command(
        names = ["fetch-uuid", "find-uuid"],
        description = "Find the UUID of a username",
        permission = "op",
        async = true,
        hidden = true
    )
    @JvmStatic
    fun execute(sender: CommandSender, @Param(name = "player") playerUUID: UUID) {
        val username = Cubed.instance.uuidCache.name(playerUUID) // username is always cached after lookup
        sender.sendMessage("${ChatColor.GREEN}Found UUID of $username: ${ChatColor.WHITE}$playerUUID")
    }

}