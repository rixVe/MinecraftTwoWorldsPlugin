package me.rixv.twoworlds.listeners;

import me.rixv.twoworlds.TwoWorldsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final TwoWorldsPlugin plugin;

    public PlayerQuitListener(TwoWorldsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

       if(event.getPlayer().getWorld() == plugin.world2)
           plugin.addLastLocationWorld2(event.getPlayer().getUniqueId(), event.getPlayer().getLocation());
       else
           plugin.addLastLocationWorld1(event.getPlayer().getUniqueId(), event.getPlayer().getLocation());
    }
}
