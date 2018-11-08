package com.gmail.flintintoe.event;

import com.gmail.flintintoe.AtomicSidebar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    private AtomicSidebar plugin;

    private boolean autoSidebar;

    public PlayerJoin(AtomicSidebar plugin) {
        this.plugin = plugin;

        autoSidebar = plugin.configData.getBoolean("auto-sidebar.on-player-join");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (autoSidebar) {
            plugin.sidebars.setSidebar(event.getPlayer(), plugin.sidebars.getFirstSidebar());
        }
    }
}
