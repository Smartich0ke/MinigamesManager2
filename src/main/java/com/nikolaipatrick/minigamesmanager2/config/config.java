package com.nikolaipatrick.minigamesmanager2.config;

import com.nikolaipatrick.minigamesmanager2.MinigamesManager2;
import dev.dejvokep.boostedyaml.YamlDocument;

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
}
