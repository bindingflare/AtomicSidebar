package com.gmail.flintintoe.command.subcommand;

import com.gmail.flintintoe.AtomicSidebar;
import com.gmail.flintintoe.sidebar.Sidebar;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents a subcommand that sets a sidebar to a player
 */
public class Set extends Subcommand {
    private AtomicSidebar plugin;

    public Set(AtomicSidebar plugin) {
        super("set", "atomicsidebar.use", "atomicsidebar.admin.use");

        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (checkIfUser(sender)) {
                if (!(sender instanceof Player)) {
                    // This is a player-only executable command
                    send(sender, "You need to specify a player");
                } else {
                    Player player = (Player) sender;
                    Sidebar sidebar = plugin.sidebars.getSidebar(args[0]);

                    if (sidebar != null) {
                        plugin.sidebars.setSidebar(player, sidebar);
                    } else {
                        send(sender, "That is not a valid sidebar name/ alias");
                    }
                }
            } else sendPermissionError(sender);

        } else if (args.length == 2) {
            if (checkIfAdmin(sender)) {
                Player target = Bukkit.getPlayer(args[0]);
                Sidebar sidebar = plugin.sidebars.getSidebar(args[1]);

                if (target == null) {
                    send(sender, "Could not find player");
                } else if (sidebar == null) {
                    send(sender, "Could not find sidebar");
                }

                if (target != null && sidebar != null) {
                    plugin.sidebars.setSidebar(target, sidebar);

                    send(sender, "Set sidebar of " + target.getName() + " to " + sidebar.getID());
                    send(target, "Your sidebar has been set to " + sidebar.getID());
                }
            } else sendPermissionError(sender);

        } else sendManyArgumentsError(sender);
    }
}
