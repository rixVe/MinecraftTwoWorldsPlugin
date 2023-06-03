package me.rixv.twoworlds.listeners;

import me.rixv.twoworlds.TwoWorldsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class PortalCreateListener implements Listener {

    private final TwoWorldsPlugin plugin;

    public PortalCreateListener(TwoWorldsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPortalCreateEvent(PortalCreateEvent event) {
        if(event.getWorld() == plugin.world2) {
            event.setCancelled(true);
        }
    }

}
