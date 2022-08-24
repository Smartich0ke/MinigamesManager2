package com.nikolaipatrick.minigamesmanager2;

import com.nikolaipatrick.minigamesmanager2.commands.mm_set;
import org.bukkit.plugin.java.JavaPlugin;
import com.nikolaipatrick.minigamesmanager2.commands.mm_create;
import com.nikolaipatrick.minigamesmanager2.commands.mm_delete;
import com.nikolaipatrick.minigamesmanager2.commands.mm_list;
import com.nikolaipatrick.minigamesmanager2.commands.mm_add;
import com.nikolaipatrick.minigamesmanager2.commands.minigame;

public final class MinigamesManager2 extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Starting MinigamesManager 1.0.0");
        getCommand("mm-create").setExecutor(new mm_create());
        getCommand("mm-delete").setExecutor(new mm_delete());
        getCommand("mm-list").setExecutor(new mm_list());
        getCommand("mm-add").setExecutor(new mm_add());
        getCommand("mm-set").setExecutor(new mm_set());
        getCommand("minigame").setExecutor(new minigame());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
