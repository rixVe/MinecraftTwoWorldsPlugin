package me.rixv.twoworlds.listeners;

import me.rixv.twoworlds.TwoWorldsPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDieListener implements Listener {

    private final TwoWorldsPlugin plugin;

    public PlayerDieListener(TwoWorldsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();

        if(player.getWorld() == plugin.world2) {
            plugin.addLastLocationWorld2(player.getUniqueId(), player.getLocation());
            player.setGameMode(GameMode.SURVIVAL);
        }
    }

}
