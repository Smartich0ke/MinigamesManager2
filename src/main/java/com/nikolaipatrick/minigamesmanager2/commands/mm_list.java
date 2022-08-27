package com.nikolaipatrick.minigamesmanager2.commands;

import com.nikolaipatrick.minigamesmanager2.config.MinigameData;
import com.nikolaipatrick.minigamesmanager2.config.config;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class mm_list implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("minigamesmanager.list") || sender.isOp()) {
            if(args.length > 1) {
                sender.sendMessage(config.messagePrefix() + ChatColor.RED + "Too many args!!");
                return true;
            }
            if(args.length < 1) {
                sender.sendMessage(config.messagePrefix() + ChatColor.RED + "Not enough args!!");
                return true;
            }
            sender.sendMessage(MinigameData.minigameDataFile.getSection("minigames").getKeys().toString());
            return true;
        } else {
            sender.sendMessage(config.messagePrefix() + config.denyMessage());
            return true;
        }
    }
}
