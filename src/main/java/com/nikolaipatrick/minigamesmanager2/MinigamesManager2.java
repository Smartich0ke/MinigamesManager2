package com.nikolaipatrick.minigamesmanager2;

import com.nikolaipatrick.minigamesmanager2.config.config;
import com.nikolaipatrick.minigamesmanager2.tabcompleters.SetTab;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import com.nikolaipatrick.minigamesmanager2.commands.mm_set;
import com.nikolaipatrick.minigamesmanager2.config.MinigameData;
import com.nikolaipatrick.minigamesmanager2.commands.mm_create;
import com.nikolaipatrick.minigamesmanager2.commands.mm_delete;
import com.nikolaipatrick.minigamesmanager2.commands.mm_list;
import com.nikolaipatrick.minigamesmanager2.commands.mm_add;
import com.nikolaipatrick.minigamesmanager2.commands.mm_reload;
import com.nikolaipatrick.minigamesmanager2.commands.minigame;

public final class MinigamesManager2 extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getConfig().set("thing", "1234");
        saveConfig();
        getLogger().info("Starting MinigamesManager 1.0.0");
        this.registerCommands();
        this.registerTabCompleter();
        config.createConfigFile();
        MinigameData.createDataFile();

    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye!");
    }
    public void registerTabCompleter(){
        this.getCommand("mm-set").setTabCompleter(new SetTab());
    }
    @SuppressWarnings("ConstantConditions")
    public void registerCommands() {
        this.getCommand("mm-create").setExecutor(new mm_create());
        this.getCommand("mm-delete").setExecutor(new mm_delete());
        this.getCommand("mm-list").setExecutor(new mm_list());
        this.getCommand("mm-add").setExecutor(new mm_add());
        this.getCommand("mm-set").setExecutor(new mm_set());
        this.getCommand("mm-reload").setExecutor(new mm_reload());
        this.getCommand("minigame").setExecutor(new minigame());
    }

}

