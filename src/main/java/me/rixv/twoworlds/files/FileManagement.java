package me.rixv.twoworlds.files;

import me.rixv.twoworlds.TwoWorldsPlugin;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileManagement {

    private final TwoWorldsPlugin plugin;

    public FileManagement(TwoWorldsPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        File locationsWorld1 = new File(plugin.getDataFolder(), "locationsWorld1.yml");
        File locationsWorld2 = new File(plugin.getDataFolder(), "locationsWorld2.yml");

        YamlConfiguration locationsWorld1Config = YamlConfiguration.loadConfiguration(locationsWorld1);
        YamlConfiguration locationsWorld2Config = YamlConfiguration.loadConfiguration(locationsWorld2);

        for (String s : locationsWorld1Config.getKeys(false)) {
            UUID id = UUID.fromString(s);
            Location loc = locationsWorld1Config.getLocation(s);
            plugin.addLastLocationWorld1(id, loc);
        }
        for (String s : locationsWorld2Config.getKeys(false)) {
            UUID id = UUID.fromString(s);
            Location loc = locationsWorld2Config.getLocation(s);
            plugin.addLastLocationWorld2(id, loc);
        }
    }

    public void terminate() {
        File locationsWorld1 = new File(plugin.getDataFolder(), "locationsWorld1.yml");
        File locationsWorld2 = new File(plugin.getDataFolder(), "locationsWorld2.yml");

        if(!locationsWorld1.exists()) {
            try {
                locationsWorld1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!locationsWorld2.exists()) {
            try {
                locationsWorld2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration locationsWorld1Config = YamlConfiguration.loadConfiguration(locationsWorld1);
        YamlConfiguration locationsWorld2Config = YamlConfiguration.loadConfiguration(locationsWorld2);

        for (UUID id : plugin.getLastLocationWorld1().keySet()) {
            locationsWorld1Config.set(id.toString(), plugin.getLastLocationWorld1(id));
        }
        for (UUID id : plugin.getLastLocationWorld2().keySet()) {
            locationsWorld2Config.set(id.toString(), plugin.getLastLocationWorld2(id));
        }

        try {
            locationsWorld1Config.save(locationsWorld1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            locationsWorld2Config.save(locationsWorld2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
