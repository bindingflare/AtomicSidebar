package com.gmail.flintintoe;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PluginUtils {
    public static final char PLACEHOLDER_SYMBOL = '%';


    public static String colorCode(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String setPlaceholders(Player player, String section) {
        return PlaceholderAPI.setPlaceholders(player, section);
    }
}
