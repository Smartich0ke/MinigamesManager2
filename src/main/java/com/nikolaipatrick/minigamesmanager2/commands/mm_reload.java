package com.nikolaipatrick.minigamesmanager2.commands;

import com.nikolaipatrick.minigamesmanager2.config.MinigameData;
import com.nikolaipatrick.minigamesmanager2.config.config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class mm_reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("minigamesmanager.reload") || sender.isOp()){
            try {
                MinigameData.minigameDataFile.reload();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                config.configFile.reload();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sender.sendMessage(config.messagePrefix() + "┬žaConfiguration reloaded successfully!");
            return true;
        } else {
            sender.sendMessage(config.denyMessage());
            return true;
        }

    }
}
