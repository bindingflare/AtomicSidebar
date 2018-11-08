package com.gmail.flintintoe.event;

import com.gmail.flintintoe.AtomicSidebar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    private AtomicSidebar plugin;

    private boolean autoUpdate;

    public PlayerMove(AtomicSidebar plugin) {
        this.plugin = plugin;

        autoUpdate = plugin.configData.getBoolean("auto-sidebar-update.on-player-move");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlock().getLocation() != event.getTo().getBlock().getLocation() && autoUpdate) {
            plugin.sidebars.updateSidebar(event.getPlayer());
        }
    }
}
