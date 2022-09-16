package com.nikolaipatrick.minigamesmanager2.commands;

import com.nikolaipatrick.minigamesmanager2.config.MinigameData;
import com.nikolaipatrick.minigamesmanager2.config.config;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.*;

public class minigame implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            String minigameToJoin = args[1];
            Player player = (Player) sender;
            if (args[0].equals("join")) {

                HashMap<String, Integer> lobbyNamesAndPlayers = new HashMap<String, Integer>();
                Set<String> lobbyWorldNamesSet = new HashSet<>();
                try {
                    MinigameData.minigameDataFile.reload();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                lobbyWorldNamesSet = MinigameData.minigameDataFile.getSection("minigames." + minigameToJoin + ".lobbies").getRoutesAsStrings(false);
                ArrayList<String> lobbyWorldNamesList = new ArrayList<>(lobbyWorldNamesSet);

                for (String worldName : lobbyWorldNamesList) {
                    try {
                        MinigameData.minigameDataFile.reload();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    int onlinePlayers = MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".lobbies." + worldName + ".onlinePlayers");
                    lobbyNamesAndPlayers.put(worldName, onlinePlayers);
                }
                int lobbyMaxPlayerValue = 0;
                String lobbyMaxPlayerWorld = null;
                for (String worldName : lobbyNamesAndPlayers.keySet()) {
                    int valueToTest = lobbyNamesAndPlayers.get(worldName);
                    if (valueToTest > lobbyMaxPlayerValue) {
                        lobbyMaxPlayerValue = valueToTest;
                        lobbyMaxPlayerWorld = worldName;
                    }
                }
                if (lobbyMaxPlayerWorld == null) {
                    lobbyMaxPlayerWorld = lobbyWorldNamesList.get(0);
                }
                Location lobbySpawn = new Location(Bukkit.getWorld(lobbyMaxPlayerWorld), 0, 0, 0);
                player.sendMessage(config.messagePrefix() + ChatColor.AQUA + "Sending you to " + ChatColor.YELLOW + lobbyMaxPlayerWorld + ChatColor.AQUA + "...");
                player.teleport(lobbySpawn);
                MinigameData.minigameDataFile.set("minigames." + minigameToJoin + ".lobbies." + lobbyMaxPlayerWorld + ".onlinePlayers", MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".lobbies." + lobbyMaxPlayerWorld + ".onlinePlayers")+1);
                try {
                    MinigameData.minigameDataFile.save();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    MinigameData.minigameDataFile.reload();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getWorld().getName().equals(lobbyMaxPlayerWorld)) {
                        p.sendTitle(MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".lobbies." + lobbyMaxPlayerWorld + ".onlinePlayers") + " / " + MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".maxPlayers") , null, 5, 100, 30);
                        p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.sharp(1, Note.Tone.C));

                    }
                }
                return true;
            }
        } else {
            sender.sendMessage(config.messagePrefix() + ChatColor.RED + "This command must be run in-game to work properly.");
            return true;
        }


        return false;
    }
}
