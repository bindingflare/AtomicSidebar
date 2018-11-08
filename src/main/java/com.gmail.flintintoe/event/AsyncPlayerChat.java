package com.gmail.flintintoe.event;

import com.gmail.flintintoe.AtomicSidebar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {
    private AtomicSidebar plugin;

    private boolean autoUpdate;

    public AsyncPlayerChat(AtomicSidebar plugin) {
        this.plugin = plugin;

        autoUpdate = plugin.configData.getBoolean("auto-sidebar-update.on-player-chat");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void asyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if (autoUpdate) {
            plugin.sidebars.updateSidebar(event.getPlayer());
        }
    }
}
