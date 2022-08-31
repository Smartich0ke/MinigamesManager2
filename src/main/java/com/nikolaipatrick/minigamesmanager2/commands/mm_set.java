package com.nikolaipatrick.minigamesmanager2.commands;

import com.nikolaipatrick.minigamesmanager2.config.MinigameData;
import com.nikolaipatrick.minigamesmanager2.config.config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class mm_set implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("minigames.set") || sender.isOp()) {
            if (args.length < 1) {
                sender.sendMessage(config.messagePrefix() + ChatColor.RED + "Not enough args!");
                return true;
            } else {
                if (args[0].equals("maxplayers")) {
                    if (args.length < 3) {
                        sender.sendMessage(config.messagePrefix() + ChatColor.RED + "Not enough args!");
                    } else {
                        int maxPlayers;
                        try {
                            maxPlayers = Integer.parseInt(args[2]);
                        } catch (NumberFormatException ex) {
                            sender.sendMessage(config.messagePrefix() + ChatColor.RED + "invalid number!");
                            return true;
                        }
                        MinigameData.setMaxPlayers(args[1], maxPlayers);
                    }
                    return true;
                }
                if (args[0].equals("tp-points")) {
                    if (args.length < 4) {
                        sender.sendMessage("Not enough args!!");
                        return true;

                    }
                }
            }

        } else {
            sender.sendMessage(config.messagePrefix() + config.denyMessage());
            return true;
        }
        return false;
    }
}
