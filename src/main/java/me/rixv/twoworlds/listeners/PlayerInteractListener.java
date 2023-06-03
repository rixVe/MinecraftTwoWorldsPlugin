package me.rixv.twoworlds.listeners;

import me.rixv.twoworlds.TwoWorldsPlugin;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class PlayerInteractListener implements Listener {

    private final TwoWorldsPlugin plugin;

    public PlayerInteractListener(TwoWorldsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRightClickBlockEvent(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.getPlayer().getWorld() == plugin.world2 && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.ENDER_EYE && Objects.requireNonNull(event.getClickedBlock()).getType() == Material.END_PORTAL_FRAME) {
                event.setCancelled(true);
            }
        }
    }

}
