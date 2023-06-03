package me.rixv.twoworlds.listeners;

import me.rixv.twoworlds.TwoWorldsPlugin;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final TwoWorldsPlugin plugin;

    public PlayerJoinListener(TwoWorldsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        if(plugin.hasLastLocationWorld2(event.getPlayer().getUniqueId()))
            event.getPlayer().teleport(plugin.getLastLocationWorld2(event.getPlayer().getUniqueId()));
        else
            event.getPlayer().teleport(plugin.world2.getSpawnLocation());

        event.getPlayer().setGameMode(GameMode.CREATIVE);
    }
}
