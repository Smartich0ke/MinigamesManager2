package com.nikolaipatrick.minigamesmanager2.tabcompleters;

import com.nikolaipatrick.minigamesmanager2.config.MinigameData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddTab implements TabCompleter {
    List<String> tabArgs = new ArrayList<String>();



    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> result = new ArrayList<String>();
        if (args.length == 0) {
            tabArgs.add("world");
            for (String a : tabArgs) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        }
        if (args.length == 1) {
            if (args[0].equals("world")){
                Set<String> minigamesSet = new HashSet<>();
                minigamesSet = MinigameData.minigameDataFile.getSection("minigames").getRoutesAsStrings(false);
                ArrayList<String> minigamesList = new ArrayList<>(minigamesSet);
                for (String a : minigamesList) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
                        result.add(a);
                    }
                }
                return result;
            } else {
                return null;
            }
        }
        if (args.length == 2) {
            if (args[0].equals("world")){
                tabArgs.clear();
                tabArgs.add("lobby");
                tabArgs.add("arena");
                for (String a : tabArgs) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                        result.add(a);
                    }
                }
                return result;
            } else {
                return null;
            }
        }
        if (args.length == 3) {
            if (args[0].equals("world")){
                tabArgs.clear();
                tabArgs.add("[<name_of_world>]");
                result = tabArgs;
                return result;
            } else {
                return null;
            }
        }
        return null;
    }
}
