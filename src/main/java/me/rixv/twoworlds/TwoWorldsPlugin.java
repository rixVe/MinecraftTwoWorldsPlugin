package me.rixv.twoworlds;

import me.rixv.twoworlds.commands.CreativeCommand;
import me.rixv.twoworlds.files.FileManagement;
import me.rixv.twoworlds.commands.SurvivalCommand;
import me.rixv.twoworlds.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class TwoWorldsPlugin extends JavaPlugin {

    private HashMap<UUID, Location> lastLocationWorld1;
    private HashMap<UUID, Location> lastLocationWorld2;

    public World world1;
    public World world2;

    private FileManagement files = new FileManagement(this);

    @Override
    public void onEnable() {
        this.lastLocationWorld1 = new HashMap<>();
        this.lastLocationWorld2 = new HashMap<>();

        this.world1 = Bukkit.createWorld(new WorldCreator("world"));
        this.world2 = Bukkit.createWorld(new WorldCreator("world2"));

        Objects.requireNonNull(getCommand("creative")).setExecutor(new CreativeCommand(this));
        Objects.requireNonNull(getCommand("survival")).setExecutor(new SurvivalCommand(this));

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDieListener(this), this);
        getServer().getPluginManager().registerEvents(new PortalCreateListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerPortalListener(this), this);

        this.files.init();

    }

    @Override
    public void onDisable() {
        this.files.terminate();
    }


    public void addLastLocationWorld1(UUID id, Location location) {
        if(this.lastLocationWorld1.containsKey(id)) {
            this.lastLocationWorld1.replace(id, location);
        }
        else {
            this.lastLocationWorld1.put(id, location);
        }
    }
    public void addLastLocationWorld2(UUID id, Location location){
        if(this.lastLocationWorld2.containsKey(id)) {
            this.lastLocationWorld2.replace(id, location);
        }
        else {
            this.lastLocationWorld2.put(id, location);
        }
    }

    public Location getLastLocationWorld1(UUID id) {
        return this.lastLocationWorld1.get(id);
    }
    public Location getLastLocationWorld2(UUID id) {
        return this.lastLocationWorld2.get(id);
    }

    public boolean hasLastLocationWorld1(UUID id){
        return this.lastLocationWorld1.containsKey(id);
    }
    public boolean hasLastLocationWorld2(UUID id){
        return this.lastLocationWorld2.containsKey(id);
    }

    public HashMap<UUID, Location> getLastLocationWorld1() {
        return lastLocationWorld1;
    }
    public HashMap<UUID, Location> getLastLocationWorld2() {
        return lastLocationWorld2;
    }
}
