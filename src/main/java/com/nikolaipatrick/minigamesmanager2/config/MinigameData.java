package com.nikolaipatrick.minigamesmanager2.config;

import com.nikolaipatrick.minigamesmanager2.MinigamesManager2;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        minigameDataFile.set("minigames." + minigameName + ".maxPlayers", 4);
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
    public static void deleteMinigameProfile(String minigameName) {
        minigameDataFile.set("minigames." + minigameName , null);
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

    public static void addMinigameWorld(String minigameName, String worldName, String worldType ) {
        if (worldType.equals("arena")) {
            minigameDataFile.set("minigames." + minigameName + ".arenas." + worldName + ".onlinePlayers", 0);
            minigameDataFile.set("minigames." + minigameName + ".arenas." + worldName + ".isInUse", false);
        } else if (worldType.equals("lobby")) {
            minigameDataFile.set("minigames." + minigameName + ".lobbies." + worldName + ".onlinePlayers", 0);
            minigameDataFile.set("minigames." + minigameName + ".lobbies." + worldName + ".isInUse", false);
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
    public static void setMaxPlayers(String minigame, int maxPlayers) {
        minigameDataFile.set("minigames." + minigame + "maxPlayers", maxPlayers);
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
    public static void setTpPoint (String minigame, int point, int x, int y, int z){
        minigameDataFile.set("minigames." + minigame + ".tp-points." + point + ".x", x);
        minigameDataFile.set("minigames." + minigame + ".tp-points." + point + ".y", y);
        minigameDataFile.set("minigames." + minigame + ".tp-points." + point + ".z", z);
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
    public static String getOnlinePlayersInArena(String minigame, String world){
        return minigameDataFile.getString("minigames." + minigame + ".arena." + minigame);

    }

}



