package me.rixv.twoworlds.commands;

import me.rixv.twoworlds.TwoWorldsPlugin;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SurvivalCommand implements CommandExecutor {

    private final TwoWorldsPlugin plugin;

    public SurvivalCommand(TwoWorldsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            UUID id = player.getUniqueId();
            Location location = player.getLocation();

            if(player.getWorld() == plugin.world2) {
                plugin.addLastLocationWorld2(id, location);
            }

            if(player.getWorld() == plugin.world1) {
                player.sendMessage(ChatColor.RED + "Już jesteś na survivalu");
                return true;
            }

            if(plugin.hasLastLocationWorld1(id)) {
                player.teleport(plugin.getLastLocationWorld1(id));
            }
            else {
                player.teleport(plugin.world1.getSpawnLocation());
            }

            player.setGameMode(GameMode.SURVIVAL);
        }
        return true;
    }
}