package com.nikolaipatrick.minigamesmanager2.config;

import com.nikolaipatrick.minigamesmanager2.MinigamesManager2;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MinigameData {
public static void createDataFile(){
    YamlDocument minigame_data;
    try {
        minigame_data = YamlDocument.create(new File("plugins/MinigamesManager2/minigame_data.yml"), MinigamesManager2.getPlugin(MinigamesManager2.class).getResource("minigame_data.yml"));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

}



