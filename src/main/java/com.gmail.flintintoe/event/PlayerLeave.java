package com.gmail.flintintoe.event;

import com.gmail.flintintoe.AtomicSidebar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerLeave implements Listener {

    private AtomicSidebar plugin;

    public PlayerLeave(AtomicSidebar plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerLeaveEvent(PlayerLeave event) {

    }
}
