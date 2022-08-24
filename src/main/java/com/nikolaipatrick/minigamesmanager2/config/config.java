package com.nikolaipatrick.minigamesmanager2.config;

import com.nikolaipatrick.minigamesmanager2.MinigamesManager2;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;

public class config {
    public static void createConfig() {
        try {
            YamlDocument config = YamlDocument.create(new File("config.yml"), MinigamesManager2.getPlugin(MinigamesManager2.class).getResource("config.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
