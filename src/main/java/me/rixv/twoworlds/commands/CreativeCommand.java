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

public class CreativeCommand implements CommandExecutor {

    private final TwoWorldsPlugin plugin;

    public CreativeCommand(TwoWorldsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            UUID id = player.getUniqueId();
            Location location = player.getLocation();

            if(player.getWorld() == plugin.world2) {
                player.sendMessage(ChatColor.RED + "Już jesteś na creativie");
                return true;
            }
            else {
                plugin.addLastLocationWorld1(id, location);
            }

            if(plugin.hasLastLocationWorld2(id)) {
                player.teleport(plugin.getLastLocationWorld2(id));
            }
            else {
                player.teleport(plugin.world2.getSpawnLocation());
            }

            player.setGameMode(GameMode.CREATIVE);
        }
        return true;
    }
}
