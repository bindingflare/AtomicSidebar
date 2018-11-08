package com.gmail.flintintoe.sidebar;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class ScoreboardPacket {
    private ProtocolManager pm;

    public ScoreboardPacket() {
        pm = ProtocolLibrary.getProtocolManager();
    }
}
