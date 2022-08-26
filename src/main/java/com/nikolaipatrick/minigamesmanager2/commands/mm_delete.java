package com.nikolaipatrick.minigamesmanager2.commands;

import com.nikolaipatrick.minigamesmanager2.config.MinigameData;
import com.nikolaipatrick.minigamesmanager2.config.config;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class mm_delete implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("minigamesmanager.delete.minigame")){
            if(args.length > 2){
                sender.sendMessage(config.configFile.getString("prefix") + ChatColor.RED + "Too many args!!");
                return true;
            }
            if(args.length < 2) {
                sender.sendMessage(config.configFile.getString("prefix") + ChatColor.RED + "not enough args!");
                return true;
            }
            if (args[0].equals("minigame")) {
                MinigameData.deleteMinigameProfile(args[1]);
                sender.sendMessage(config.configFile.getString("prefix") + ChatColor.GREEN + "Minigame" + args[1] + "deleted successfully!");
                return true;
            }
        } else {
            sender.sendMessage(config.configFile.getString("deny-message"));
            return true;
        }
        return false;
    }
}
