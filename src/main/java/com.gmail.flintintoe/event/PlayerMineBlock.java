package com.gmail.flintintoe.event;

import com.gmail.flintintoe.AtomicSidebar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerMineBlock implements Listener {

    private AtomicSidebar plugin;

    private boolean autoUpdate;

    public PlayerMineBlock(AtomicSidebar plugin) {
        this.plugin = plugin;

        autoUpdate = plugin.configData.getBoolean("auto-sidebar-update.on-player-mine-block");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMineBlock(BlockBreakEvent event) {
        if (autoUpdate) {
            plugin.sidebars.updateSidebar(event.getPlayer());
        }
    }
}
