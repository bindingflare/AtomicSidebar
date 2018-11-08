package com.gmail.flintintoe.sidebar;

import com.gmail.flintintoe.PluginUtils;

import java.util.ArrayList;
import java.util.List;

public class Sidebar {
    private String objectiveheader;
    private String ID;
    private List<String> aliases;
    private List<Score> scores;

    public Sidebar(String objectiveheader, String ID) {
        this.objectiveheader = PluginUtils.colorCode(objectiveheader);
        this.ID = ID;

        aliases = new ArrayList<>();
        scores = new ArrayList<>();
    }

    public void add(Score score) {
        for (Score existing : scores) {
            // Add a space to the score if duplicate
            if (score.getContent().equals(existing)) {
                score = new Score(score.getContent() + ' ');
            }
        }

        scores.add(score);
    }

    public void add(String alias) {
        aliases.add(alias);
    }

    public String info() {
        return "You are using sidebar with name " + getID() + ", header " + getObjectiveheader() + " and aliases " + getAliases().toString();
    }

    public List<Score> getScores() {
        return scores;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getObjectiveheader() {
        return objectiveheader;
    }

    public String getID() {
        return ID;
    }
}
