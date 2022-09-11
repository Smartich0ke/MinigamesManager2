package com.nikolaipatrick.minigamesmanager2.commands;

import com.nikolaipatrick.minigamesmanager2.config.MinigameData;
import com.nikolaipatrick.minigamesmanager2.config.config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class mm_add implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("minigamesmanager.add") || sender.isOp()) {
            if (args[0].equals("world")){
                MinigameData.addMinigameWorld(args[1], args[3], args[2]);
                sender.sendMessage(config.messagePrefix() + ChatColor.GREEN + "World " + ChatColor.YELLOW + args[3] + ChatColor.GREEN + " added as a " + ChatColor.YELLOW + args[2] + ChatColor.GREEN + " to the minigame " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " successfully!");
                return true;
            }
            else {
                return false;
            }
        }
        else {
            sender.sendMessage(config.messagePrefix() + config.denyMessage());
            return true;
        }
    }
}
