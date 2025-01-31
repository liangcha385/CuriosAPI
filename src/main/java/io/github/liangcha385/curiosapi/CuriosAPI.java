package io.github.liangcha385.curiosapi;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import de.tr7zw.changeme.nbtapi.NBT;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import io.github.liangcha385.curiosapi.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CuriosAPI extends JavaPlugin {
    @Override
    public void onLoad() {
        SettingsManager settingsManagerConfig = SettingsManagerBuilder
                .withYamlFile(new File("config.yml"))
                .configurationData(Config.class)
                .useDefaultMigrationService()
                .create();

        String verboseOutput = settingsManagerConfig.getProperty(Config.COMMANDAPI_VERBOSEOUTPUT);

        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(Boolean.parseBoolean(verboseOutput)));
    }

    @Override
    public void onEnable() {
        if (!NBT.preloadApi()) {
            getLogger().warning("NBT-API wasn't initialized properly, disabling the plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        CommandAPI.onEnable();

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
             getLogger().info("Found PlaceholderAPI, registering related modules.");
        } else {
            getLogger().warning("Could not find PlaceholderAPI! The relevant modules will not be available!");
        }
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
        getLogger().warning("CuriosAPI is uninstalling! If this operation is not expected, please check the server operation log!");
    }
}
