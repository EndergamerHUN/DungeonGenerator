package io.github.endergamerhun.dungeongenerator;

import io.github.endergamerhun.dungeongenerator.command.CommandManager;
import io.github.endergamerhun.dungeongenerator.utils.Util;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DungeonGenerator extends JavaPlugin {

    private static DungeonGenerator INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        try {
            Objects.requireNonNull(getCommand("dungeongenerator")).setExecutor(new CommandManager());
        } catch (NullPointerException ignored) {
            Util.log("§cFailed to load commands!");
        }

        saveDefaultConfig();
        Util.log("§aPlugin loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static DungeonGenerator getInstance() {
        return INSTANCE;
    }
}
