package de.lordjulixn.sounds.listener;

import de.lordjulixn.sounds.Sounds;
import de.lordjulixn.sounds.utils.InventoryOverlay;
import de.lordjulixn.sounds.utils.InventorySounds;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(!Sounds.getInventoryManager().hasInventory(player)) return;
        if(Sounds.getInventoryManager().getInventory(player).getInventoryName().equals(InventorySounds.inventoryName)) {
            event.setCancelled(true);
            ItemStack item = event.getCurrentItem();
            if(item == null) return;
            if(!item.hasItemMeta()) return;
            if(!item.getItemMeta().hasDisplayName()) return;
            if(event.getClick() != ClickType.LEFT) return;

            if(item.getItemMeta().getDisplayName().equals("§7Nächste Seite")) {
                if(Sounds.getInventoryManager().getPage(player) >= InventorySounds.maxPages()) {
                    Sounds.getInventoryManager().setPage(player, 1);
                } else Sounds.getInventoryManager().setPage(player, Sounds.getInventoryManager().getPage(player)+1);
                Sounds.getInventoryManager().openInventory(player, new InventorySounds(), Sounds.getInventoryManager().getPage(player));
                return;
            }
            if(item.getItemMeta().getDisplayName().equals("§7Vorherige Seite")) {
                if(Sounds.getInventoryManager().getPage(player) <= 1) {
                    Sounds.getInventoryManager().setPage(player, InventorySounds.maxPages());
                } else Sounds.getInventoryManager().setPage(player, Sounds.getInventoryManager().getPage(player)-1);
                Sounds.getInventoryManager().openInventory(player, new InventorySounds(), Sounds.getInventoryManager().getPage(player));
                return;
            }
            if(item.getItemMeta().getDisplayName().equals("§3Pitch §a§l-")) {
                if(Sounds.getInventoryManager().getPitch(player) <= 0.1) return;
                Sounds.getInventoryManager().changePitch(player, false);
                updatePitch(player, event.getInventory());
                return;
            }
            if(item.getItemMeta().getDisplayName().equals("§3Pitch §a§l+")) {
                Sounds.getInventoryManager().changePitch(player, true);
                updatePitch(player, event.getInventory());
                return;
            }
            if(item.getItemMeta().getDisplayName().equals("§3Pitch §8» §a"+ Sounds.getInventoryManager().getPitch(player))) {
                Sounds.getInventoryManager().setPitch(player, 1.0);
                updatePitch(player, event.getInventory());
                return;
            }

            for(Sound sound : Sound.values()) {
                if(item.getItemMeta().getDisplayName().equals("§a"+sound.name())) {
                    player.playSound(player.getLocation(), sound, 1F, (float) Sounds.getInventoryManager().getPitch(player));
                    if(Sounds.getInventoryManager().getLastPlayedSound(player) == sound) return;
                    Sounds.getInventoryManager().setLastPlayedSound(player, sound);
                    Sounds.getInventoryManager().openInventory(player, new InventorySounds(), Sounds.getInventoryManager().getPage(player));
                    break;
                }
            }

        }
    }

    private void updatePitch(Player player, Inventory inventory) {
        ItemStack newItem = new InventorySounds().getInventory(player, 1).getItem(49);
        inventory.getItem(49).setItemMeta(newItem.getItemMeta());
    }

}
