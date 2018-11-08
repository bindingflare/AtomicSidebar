package com.gmail.flintintoe.sidebar;

import com.comphenix.packetwrapper.WrapperPlayServerScoreboardDisplayObjective;
import com.comphenix.packetwrapper.WrapperPlayServerScoreboardObjective;
import com.comphenix.packetwrapper.WrapperPlayServerScoreboardScore;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.gmail.flintintoe.AtomicSidebar;
import com.gmail.flintintoe.PluginUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Sidebars {

    private AtomicSidebar plugin;
    private List<Sidebar> sidebars;

    public Sidebars(AtomicSidebar plugin) {
        this.plugin = plugin;

        sidebars = new ArrayList<>();

        loadSidebars();
    }

    public void loadSidebars() {
        ConfigurationSection section = plugin.sidebarConfig.getConfig().getConfigurationSection("sidebars");

        // For every sidebar section
        for (String path : section.getKeys(false)) {
            // For every sidebar section setting
            String objectiveheader = section.getString(path + ".header");
            String objectivename = path;

            Sidebar sidebar = new Sidebar(objectiveheader, objectivename);

            for (String alias : section.getStringList(path + ".aliases")) {
                sidebar.add(alias);
            }

            for (String entry : section.getStringList(path + ".entries")) {
                sidebar.add(new Score(entry));
            }

            sidebars.add(sidebar);
        }
    }

    public Sidebar getSidebar(String name) {
        for (Sidebar sidebar : sidebars) {
            // Search by objective name
            if (sidebar.getObjectiveheader().equalsIgnoreCase(name)) {
                return sidebar;
            }

            // Search by alias
            for (String alias : sidebar.getAliases()) {
                if (alias.equalsIgnoreCase(name)) {
                    return sidebar;
                }
            }
        }

        return null;
    }

    public Sidebar getSidebarOf(Player player) {
        String objective = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getName();

        objective = objective.substring(0, objective.indexOf(player.getName()));

        for (Sidebar sidebar : sidebars) {
            if (objective.equals(sidebar.getID())) {
                return sidebar;
            }
        }

        return null;
    }

    public void setSidebar(Player player, Sidebar sidebar) {
//        Scoreboard scoreboard = plugin.getServer().getScoreboardManager().getNewScoreboard();
        ProtocolManager pm = ProtocolLibrary.getProtocolManager();
        String objective = sidebar.getID();

        if (objective.length() > 16) {
            plugin.getLogger().log(Level.INFO, "Shortening objective " + objective);
            objective = objective.substring(0, 15);
        }

        // Create objective
        WrapperPlayServerScoreboardObjective boardpacket = new WrapperPlayServerScoreboardObjective();
        boardpacket.setObjectiveName(objective);
        boardpacket.setPacketMode((byte) 0);
        boardpacket.setObjectiveValue(PluginUtils.colorCode(sidebar.getObjectiveheader()));

        boardpacket.sendPacket(player);

//        PacketContainer objectivepacket = pm.createPacket(PacketType.Play.Server.SCOREBOARD_OBJECTIVE);
//        objectivepacket.getStrings().write(0, PluginUtils.colorCode(sidebar.getObjectiveheader().toString()));

        // Create scores
        int count = 0;
        for (Score score : sidebar.getScores()) {
            WrapperPlayServerScoreboardScore scorepacket = new WrapperPlayServerScoreboardScore();

            String content = score.getCompletedContent(player);

            if (content.length() > 40) {
                plugin.getLogger().log(Level.INFO, "Shortening score " + content);
                content = content.substring(0, 39);
            }

            scorepacket.setItemName(content);
            scorepacket.setPacketMode((byte) 0);
            scorepacket.setScoreName(objective);
            scorepacket.setValue(count);

            scorepacket.sendPacket(player);

            count++;
//            PacketContainer scorepacket = pm.createPacket(PacketType.Play.Server.SCOREBOARD_SCORE);
        }

        // Display scoreboard
        WrapperPlayServerScoreboardDisplayObjective displaypacket = new WrapperPlayServerScoreboardDisplayObjective();
        displaypacket.setPosition((byte) 1);
        displaypacket.setScoreName(objective);

        displaypacket.sendPacket(player);

//        PacketContainer displaypacket = pm.createPacket(PacketType.Play.Server.SCOREBOARD_DISPLAY_OBJECTIVE);

    }

    public void updateSidebar(Player player) {
        Sidebar sidebar = getSidebarOf(player);

        if (sidebar != null) {
            setSidebar(player, sidebar);
        }
    }

    public Sidebar getFirstSidebar() {
        return sidebars.get(0);
    }

//    private AtomicSidebar plugin;
//
//    private List<Sidebar> sidebars;
//    private PacketConstructor packetConst;
//
//    public Sidebars(AtomicSidebar plugin) {
//        this.plugin = plugin;
//    }
//
//    public void setup() {
//        // Init packetConstructor
//        packetConst = ProtocolLibrary.getProtocolManager().createPacketConstructor(PacketType.Play.Server
//                .SCOREBOARD_DISPLAY_OBJECTIVE, new ASSidebarPacketConstructor(plugin));
//
//        // Load sidebar
//        loadSidebars();
//    }
//
//    public void loadSidebars() {
//        ASConfigFile configFile = plugin.configM.getASConfigFile("sidebar.yml");
//        FileConfiguration configuration = configFile.getFileConfiguration();
//
//        ConfigurationSection mainSection = configuration.getConfigurationSection("sidebar");
//
//        // For each sidebar section...
//        for (String child : mainSection.getKeys(false)) {
//            ConfigurationSection sidebar = mainSection.getConfigurationSection(child);
//
//            List<String> entryNames = sidebar.getStringList("entries");
//            int score = entryNames.size() - 1;
//
//            List<Score> entries = new ArrayList<>();
//            for (String string: sidebar.getStringList("entries")) {
//                entries.add(new Score(string, score));
//                score--;
//            }
//
//            ASObjective objective = new ASObjective(sidebar.getString("header"), entries);
//            Sidebar scoreboard = new Sidebar(child, objective);
//
//            sidebars.add(scoreboard);
//        }
//    }
//
//    public void setScoreboard(Player player, Sidebar scoreboard) {
//        Packet
//
//        plugin.protocolM.
//    }
//
//    public Sidebar getScoreboard(String name) {
//        for (Sidebar scoreboard : sidebars) {
//            if(scoreboard.getName().equals(name)) {
//                return scoreboard;
//            }
//        }
//
//        return null;
//    }
}
