package com.nikolaipatrick.minigamesmanager2.config;

import com.nikolaipatrick.minigamesmanager2.MinigamesManager2;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MinigameData {
    public static YamlDocument minigameDataFile;
    public static void createDataFile(){

        try {
            minigameDataFile = YamlDocument.create(new File("plugins/MinigamesManager2/minigame_data.yml"), MinigamesManager2.getPlugin(MinigamesManager2.class).getResource("minigame_data.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createMinigameProfile(String minigameName){

        minigameDataFile.set("minigames." + minigameName + ".displayName", minigameName);
        try {
            minigameDataFile.save(new File("plugins/MinigamesManager2/minigame_data.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            minigameDataFile.reload();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addMinigameWorld(String minigameName, String worldName, String worldType) {
        //we need to convert the world name we want to add into an arrayList so that it is added as a list item in the YML:
        ArrayList<String> worldNameList = new ArrayList<String>();
        worldNameList.add(worldName);
        if (worldType.equals("arena")) {
            minigameDataFile.set("minigames." + minigameName + ".arenas", worldNameList);
        } else if (worldType.equals("")) {
            minigameDataFile.set("minigames." + minigameName + ".lobbies", worldNameList);
        } else {
            throw new RuntimeException(worldType + "is not a valid worldType. use arena or lobby.");
        }
        try {
            minigameDataFile.save(new File("plugins/MinigamesManager2/minigame_data.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            minigameDataFile.reload();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}



