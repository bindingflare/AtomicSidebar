package com.gmail.flintintoe;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents a class that can send messages to senders
 */
public class Sendable {
    public void send(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
