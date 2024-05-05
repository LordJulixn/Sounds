package de.lordjulixn.sounds.listener;

import de.lordjulixn.sounds.Sounds;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class BasicListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Sounds.getInventoryManager().removeInventory(event.getPlayer());
        Sounds.getInventoryManager().setPitch(event.getPlayer(), 1.0);
        Sounds.getInventoryManager().setPage(event.getPlayer(), 1);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if(Sounds.getInventoryManager().hasInventory(player)) Sounds.getInventoryManager().removeInventory(player);
    }

}
