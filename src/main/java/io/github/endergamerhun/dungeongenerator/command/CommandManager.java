package io.github.endergamerhun.dungeongenerator.command;

import io.github.endergamerhun.dungeongenerator.utils.DungeonUtil;
import io.github.endergamerhun.dungeongenerator.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.StringUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

public class CommandManager implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("dungeongenerator")) if (args.length>0) runCommand(sender, args[0], Arrays.copyOfRange(args, 1, args.length));
        return true;
    }
    private void runCommand(CommandSender sender, String subCommand, String[] args) {
        switch (subCommand.toLowerCase()) {
            case "reload" -> {
                Util.reloadConfig();
                sender.sendMessage("§aConfig reloaded!");
            }
            case "generate" -> {
                Player p = enforcePlayer(sender);
                if (p == null) return;
                Location loc = p.getLocation();
                StructureManager sm = Bukkit.getStructureManager();


            }
            case "list" -> {
                if (args.length == 0) {
                    String[] dungeons = DungeonUtil.getDungeonsAsString();
                    if (dungeons == null) {
                        sender.sendMessage("§cThere are no dungeons!");
                        return;
                    }
                    sender.sendMessage("§fDungeons:");
                    for (String dungeon: dungeons) {
                        sender.sendMessage("§f- §e" + dungeon);
                    }
                    return;
                }
                if (args.length < 2) {
                    sender.sendMessage("§cToo few argument!");
                    return;
                }
                if (!Arrays.asList(DungeonUtil.getDungeonsAsString()).contains(args[0])) {
                    sender.sendMessage("§cThat is not a valid dungeon!");
                    return;
                }
                if (!List.of("X","T","I","L","R","O").contains(args[1])) {
                    sender.sendMessage("§cThat is not a valid room type!");
                    return;
                }
                sender.sendMessage("Path: §e" + new File(Util.getRoot(),"dungeons\\" + args[0] + "\\rooms\\" + args[1]).getAbsolutePath());
                String[] rooms = new File(Util.getRoot(),"dungeons\\" + args[0] + "\\rooms\\" + args[1]).list();
                if (rooms == null) {
                    sender.sendMessage("§cThere are no schematics!");
                    return;
                }
                sender.sendMessage("§fRoom list:");
                for (String room : rooms) {
                    sender.sendMessage("§f- §e" + room);
                }
            }

            default -> sender.sendMessage(String.format("§cUnknown subcommand §e%s§c!",subCommand));
        }
    }

    private Player enforcePlayer(CommandSender sender) {
        if (sender instanceof Player p) {
            return p;
        }
        sender.sendMessage("§cThis can only be executed by players!");
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        int length = args.length;
        if (length == 1) {
            for (String sub : new String[]{"reload","generate", "list"}) {
                if (sender.hasPermission("dungeongenerator"+sub)) completions.add(sub);
            }
        }
        switch (args[0].toLowerCase()) {
            case "generate" -> {
                if (length == 2) {
                    completions.addAll(List.of(DungeonUtil.getDungeonsAsString()));
                }
            }
            case "list" -> {
                switch (length) {
                    case 2 -> completions.addAll(List.of(DungeonUtil.getDungeonsAsString()));
                    case 3 -> completions.addAll(List.of("X","T","I","L","R","O"));
                }
            }
        }

        return StringUtil.copyPartialMatches(args[length-1], completions, new ArrayList<>());
    }
}
