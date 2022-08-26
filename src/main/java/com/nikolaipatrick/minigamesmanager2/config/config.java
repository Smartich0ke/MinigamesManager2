package com.nikolaipatrick.minigamesmanager2.config;

import com.nikolaipatrick.minigamesmanager2.MinigamesManager2;
import dev.dejvokep.boostedyaml.YamlDocument;
import net.md_5.bungee.api.ChatColor;

import java.io.File;
import java.io.IOException;

public class config {
    public static YamlDocument configFile;
    public static void createConfigFile() {
        try {
            configFile = YamlDocument.create(new File("plugins/MinigamesManager2/config.yml"), MinigamesManager2.getPlugin(MinigamesManager2.class).getResource("config.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String messagePrefix() {
        return ChatColor.translateAlternateColorCodes('&', configFile.getString("prefix"));
    }
    public static String denyMessage() {
        return ChatColor.translateAlternateColorCodes('&', configFile.getString("deny-message"));
    }
}
