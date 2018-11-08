package com.gmail.flintintoe.sidebar;

import com.gmail.flintintoe.PluginUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a score with placeholders
 */
public class Score {
    private String content;
    private List<String> sections;

    public Score(String content) {
        this.content = content;

        sections = new ArrayList<>();
        StringBuilder sectionBuilder = new StringBuilder();

        char[] chars = content.toCharArray();

        // Section builder code
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (ch == PluginUtils.PLACEHOLDER_SYMBOL) {
                if (i + 1 == chars.length) {
                    sections.add(sectionBuilder.toString());
                    sectionBuilder.setLength(0);
                } else if (chars[i + 1] == PluginUtils.PLACEHOLDER_SYMBOL) {
                    sectionBuilder.append(ch);
                    i++;
                } else {
                    sections.add(sectionBuilder.toString());
                    sectionBuilder.setLength(0);
                }
            } else {
                sectionBuilder.append(ch);
            }
        }

        sections.add(sectionBuilder.toString());
    }

    public String getContent() {
        return content;
    }

    public String getCompletedContent(Player player) {
        StringBuilder builder = new StringBuilder();

        for (String string : sections) {
            builder.append(PluginUtils.setPlaceholders(player, string));
        }

        return builder.toString();
    }

    public List<String> getSections() {
        return sections;
    }
}
