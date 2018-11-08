package com.gmail.flintintoe;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.HashMap;

public class ScoreboardUpdateTask {
    private HashMap<String, Integer> playersOnCooldown = new HashMap<>();
    private HashMap<String, Integer> playerSetSidebar = new HashMap<>();

    private AtomicSidebar plugin;

    private int interval;

    public void SidebarRunnable(AtomicSidebar plugin) {
        this.plugin = plugin;
    }

    //    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String scoreboardName = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getName();
//            plugin.sidebars.getScoreboard(scoreboardName).setTo(player);
        }

    }
}
