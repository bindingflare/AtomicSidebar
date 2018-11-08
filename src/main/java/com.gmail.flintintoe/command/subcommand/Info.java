package com.gmail.flintintoe.command.subcommand;

import com.gmail.flintintoe.AtomicSidebar;
import com.gmail.flintintoe.sidebar.Sidebar;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents a subcommand that displays information of a sidebar
 */
public class Info extends Subcommand {
    private AtomicSidebar plugin;

    public Info(AtomicSidebar plugin) {
        super("info", "atomicsidebar.see", "atomicsidebar.admin.see");

        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (checkIfUser(sender)) {
                if (!(sender instanceof Player)) {
                    // This is a player-only executable command
                    send(sender, "You need to specify a player");
                } else {
                    Player player = (Player) sender;

                    if (checkIfUser(sender)) {
                        Sidebar sidebar = plugin.sidebars.getSidebarOf(player);

                        if (sidebar == null) {
                            send(player, "You have no sidebar set");
                        } else {
                            send(player, sidebar.info());
                        }
                    } else sendPermissionError(sender);
                }
            }

        } else if (args.length == 1) {
            if (checkIfAdmin(sender)) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target == null) {
                    send(sender, "Player not found");
                } else {
                    Sidebar sidebar = plugin.sidebars.getSidebarOf(target);

                    if (sidebar == null) {
                        send(sender, target.getName() + " has no sidebar set");
                        ;
                    } else {
                        send(sender, sidebar.info());
                    }
                }
            } else sendPermissionError(sender);

        } else sendManyArgumentsError(sender);
    }
}
