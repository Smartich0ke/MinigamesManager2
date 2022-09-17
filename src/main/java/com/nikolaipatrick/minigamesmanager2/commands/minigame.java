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
import java.util.concurrent.TimeUnit;

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
                String lobbyToJoin = null;
                for (String worldName : lobbyNamesAndPlayers.keySet()) {
                    int valueToTest = lobbyNamesAndPlayers.get(worldName);
                    if (valueToTest > lobbyMaxPlayerValue && !(valueToTest == MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".maxPlayers"))) {
                        lobbyMaxPlayerValue = valueToTest;
                        lobbyToJoin = worldName;
                    }
                }
                if (lobbyToJoin == null) {
                    lobbyToJoin = lobbyWorldNamesList.get(0);
                }
                Location lobbySpawn = new Location(Bukkit.getWorld(lobbyToJoin), 0, 0, 0);
                player.sendMessage(config.messagePrefix() + ChatColor.AQUA + "Sending you to " + ChatColor.YELLOW + lobbyToJoin + ChatColor.AQUA + "...");
                player.teleport(lobbySpawn);
                MinigameData.minigameDataFile.set("minigames." + minigameToJoin + ".lobbies." + lobbyToJoin + ".onlinePlayers", MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".lobbies." + lobbyToJoin + ".onlinePlayers")+1);
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
                    if(p.getWorld().getName().equals(lobbyToJoin)) {
                        p.sendTitle(MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".lobbies." + lobbyToJoin + ".onlinePlayers") + " / " + MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".maxPlayers") , null, 5, 100, 30);
                        p.playNote(p.getLocation(), Instrument.SNARE_DRUM, Note.sharp(1, Note.Tone.C));

                    }
                }
                if (MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".lobbies." + lobbyToJoin + ".onlinePlayers").equals(MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".maxPlayers")) ) {
                    Set<String> arenaWorldNamesSet = new HashSet<>();
                    try {
                        MinigameData.minigameDataFile.reload();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    arenaWorldNamesSet = MinigameData.minigameDataFile.getSection("minigames." + minigameToJoin + ".arenas").getRoutesAsStrings(false);
                    ArrayList<String> arenaWorldNamesList = new ArrayList<>(arenaWorldNamesSet);
                    String arenaToJoin = null;
                    while (arenaToJoin == null) {
                        for (String worldName : arenaWorldNamesList) {
                            if (!MinigameData.minigameDataFile.getBoolean("minigames." + minigameToJoin + ".arenas." + worldName + ".isInUse")) {
                                arenaToJoin = worldName;
                            }
                        }
                    }
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getWorld().getName().equals(lobbyToJoin)) {
                            for (int countdown = 5; countdown > 0; countdown--) {
                                p.sendTitle(String.valueOf(countdown), null, 0, 15, 5);
                                if(MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".lobbies."+ lobbyToJoin + "onlinePlayers") < MinigameData.minigameDataFile.getInt("minigames." + minigameToJoin + ".maxPlayers")){
                                    countdown = 0;
                                    return true;
                                }
                            }
                            Location arenaSpawn = new Location(Bukkit.getWorld(arenaToJoin), 0, 0, 0);
                            p.teleport(arenaSpawn);
                        }
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
