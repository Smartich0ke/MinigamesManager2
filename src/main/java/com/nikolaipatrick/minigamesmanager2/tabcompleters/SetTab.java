package com.nikolaipatrick.minigamesmanager2.tabcompleters;

import com.nikolaipatrick.minigamesmanager2.config.MinigameData;
import com.nikolaipatrick.minigamesmanager2.config.config;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
                result.clear();
                return (List<String>) MinigameData.minigameDataFile.getSection("configSection").getRoutesAsStrings(false);
            }
        }
        return null;
    }
}
