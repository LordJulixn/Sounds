package de.lordjulixn.sounds.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class InventoryOverlay {

    public abstract String getInventoryName();
    public abstract Inventory getInventory(Player player, int page);

}
