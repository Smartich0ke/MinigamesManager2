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
        sender.sendMessage(config.configFile.get("prefix") + "§aConfiguration reloaded successfully!");
        return true;
    }
}
