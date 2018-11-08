package com.gmail.flintintoe;


import com.gmail.flintintoe.command.Commander;
import com.gmail.flintintoe.config.ConfigAccessor;
import com.gmail.flintintoe.event.AsyncPlayerChat;
import com.gmail.flintintoe.event.PlayerJoin;
import com.gmail.flintintoe.event.PlayerMove;
import com.gmail.flintintoe.sidebar.Sidebars;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class AtomicSidebar extends JavaPlugin {

    public FileConfiguration configData;
    public ConfigAccessor sidebarConfig;

    public Sidebars sidebars;

    @Override
    public void onEnable() {
        // Data files
        getConfigs();

        sidebars = new Sidebars(this);

        registerEvents();
        registerCommandExecutors();
    }

    public void getConfigs() {
        configData = getConfig();
        sidebarConfig = new ConfigAccessor(this, "sidebars.yml");

        if (!getDataFolder().exists()) {
            if (getDataFolder().mkdirs()) {
                getLogger().log(Level.INFO, "Creating plugin datafolder...");
            } else {
                getLogger().log(Level.SEVERE, "Could not create plugin datafolder!");
            }

            configData.options().copyDefaults(true);
            saveDefaultConfig();
            sidebarConfig.getConfig().options().copyDefaults(true);
            sidebarConfig.saveDefaultConfig();
        } else {
            sidebarConfig.getConfig();
        }
    }

    public void reloadConfigs() {
        reloadConfig();
        sidebarConfig.reloadConfig();
    }

    private void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new AsyncPlayerChat(this), this);
        pm.registerEvents(new PlayerJoin(this), this);
//        pm.registerEvents(new PlayerLeave(this), this);
        pm.registerEvents(new PlayerMove(this), this);
    }

    private void registerCommandExecutors() {
        getCommand("asidebar").setExecutor(new Commander(this));
//        this.getCommand("atomicsidebaradmin").setExecutor(new AdminCommand());
//        this.getCommand("atomicsidebar").setExecutor(new PlayerCommand());
    }
}
