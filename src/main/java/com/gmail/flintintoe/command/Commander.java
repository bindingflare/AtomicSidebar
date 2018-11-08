package com.gmail.flintintoe.command;

import com.gmail.flintintoe.AtomicSidebar;
import com.gmail.flintintoe.Sendable;
import com.gmail.flintintoe.command.subcommand.Info;
import com.gmail.flintintoe.command.subcommand.Set;
import com.gmail.flintintoe.command.subcommand.Subcommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Commander extends Sendable implements CommandExecutor {
    private AtomicSidebar plugin;

    private List<Subcommand> subcommands;

    public Commander(AtomicSidebar plugin) {
        this.plugin = plugin;

        subcommands = new ArrayList<>();
        subcommands.add(new Info(plugin));
        subcommands.add(new Set(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (Subcommand subcommand : subcommands) {
            if (args[0].equalsIgnoreCase(subcommand.getLabel())) {
                String[] newargs = new String[args.length - 1];

                int count = 0;
                for (int i = 1; i < args.length; i++) {
                    newargs[count] = args[i];
                }

                subcommand.execute(sender, newargs);
            }
        }

        return true;
    }
}
