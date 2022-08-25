package com.nikolaipatrick.minigamesmanager2.commands;

import com.nikolaipatrick.minigamesmanager2.config.config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.nikolaipatrick.minigamesmanager2.config.MinigameData;

import static com.nikolaipatrick.minigamesmanager2.config.MinigameData.minigameDataFile;

public class mm_create implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String minigameName = args[0];
        if(!player.hasPermission("minigamesmanager.create")) {
            if(args.length > 1) {
                player.sendMessage(config.configFile.getString("prefix") + ChatColor.RED + "Too many args!" + ChatColor.GREEN + "(Usage:" + ChatColor.YELLOW + "/mm-create <nameOfMinigame>" + ChatColor.GREEN + ")");
                return true;
            }
            if(args.length < 1) {
                player.sendMessage(config.configFile.getString("prefix") + ChatColor.RED + "Not enough args!!" + ChatColor.GREEN + "(Usage:" + ChatColor.YELLOW + "/mm-create <nameOfMinigame>" + ChatColor.GREEN + ")");
                return true;
            }
            if(args.length == 1) {
                MinigameData.createMinigameProfile(minigameName);
                player.sendMessage(config.configFile.getString("prefix") + ChatColor.GREEN + "Minigame created successfully!");
                return true;
            }
        }
        return false;
    }
}
