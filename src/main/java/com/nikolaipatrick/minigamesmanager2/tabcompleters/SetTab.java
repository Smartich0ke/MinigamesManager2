package com.nikolaipatrick.minigamesmanager2.tabcompleters;

import com.nikolaipatrick.minigamesmanager2.config.MinigameData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class SetTab implements TabCompleter {

    List<String> tabArgs = new ArrayList<String>();


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(tabArgs.isEmpty()) {
            tabArgs.add("max-players");
            tabArgs.add("tp-points");
        }
        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            result.clear();
            for (String a : tabArgs) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        }
        if (args.length == 2) {
            if(args[0].equals("max-players")){
                result.clear();
                result.add("[<number>]");
                return result;
            }
            if (args[0].equals("tp-points")){
                Set<String> minigamesSet = new HashSet<>();
                minigamesSet = MinigameData.minigameDataFile.getSection("minigames").getRoutesAsStrings(false);
                ArrayList<String> minigamesList = new ArrayList<>(minigamesSet);
                for (String a : minigamesList) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
                        result.add(a);
                    }
                }
                return result;
            }
        }
        if (args.length == 3) {
            if (args[0].equals("tp-points")) {
                result.clear();
                result.add("[<number>]");
                return result;
            }
        }
        if (args.length == 4) {
            if (args[0].equals("tp-points")) {
                result.clear();
                result.add("[<x>]");
                return result;
            }
        }
        if (args.length == 5) {
            if (args[0].equals("tp-points")) {
                result.clear();
                result.add("[<y>]");
                return result;
            }
        }
        if (args.length == 6) {
            if (args[0].equals("tp-points")) {
                result.clear();
                result.add("[<z>]");
                return result;
            }
        }
        return null;
    }
}
