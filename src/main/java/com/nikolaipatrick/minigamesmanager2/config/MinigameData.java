package com.nikolaipatrick.minigamesmanager2.config;

import com.nikolaipatrick.minigamesmanager2.MinigamesManager2;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MinigameData {
    static YamlDocument minigameData;
    public static void createDataFile(){

        try {
            minigameData = YamlDocument.create(new File("plugins/MinigamesManager2/minigame_data.yml"), MinigamesManager2.getPlugin(MinigamesManager2.class).getResource("minigame_data.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createMinigameProfile(String minigameName){
        minigameData.set("minigames." + minigameName + ".name", minigameName);
    }
}



