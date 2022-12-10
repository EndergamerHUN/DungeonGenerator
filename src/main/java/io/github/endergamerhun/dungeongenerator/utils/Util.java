package io.github.endergamerhun.dungeongenerator.utils;

import io.github.endergamerhun.dungeongenerator.DungeonGenerator;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class Util {

    private static final String PREFIX = "[DungeonGenerator]";

    public static void log(String format, Object... objects) {
        String log = String.format(format, objects);
        Bukkit.getConsoleSender().sendMessage(PREFIX + log);
    }

    public static File getRoot() {
        return getInstance().getDataFolder();
    }

    public static DungeonGenerator getInstance() {
        return DungeonGenerator.getInstance();
    }

    public static FileConfiguration getConfig() {
        return getInstance().getConfig();
    }

    public static void saveConfig() {
        getInstance().saveConfig();
    }
    public static void reloadConfig() {
        getInstance().reloadConfig();
    }

}
