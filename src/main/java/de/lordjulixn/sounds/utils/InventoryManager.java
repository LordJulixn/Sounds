package de.lordjulixn.sounds.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class InventoryManager {

    private HashMap<UUID, InventoryOverlay> playerInventorys;
    private HashMap<UUID, Integer> playerPage;
    private HashMap<UUID, Double> playerPitch;
    private HashMap<UUID, Sound> lastPlayedSound;

    public InventoryManager() {
        playerInventorys = new HashMap<>();
        playerPage = new HashMap<>();
        playerPitch = new HashMap<>();
        lastPlayedSound = new HashMap<>();
    }

    public void setInventory(Player player, InventoryOverlay inventoryOverlay) {
        removeInventory(player);
        playerInventorys.put(player.getUniqueId(), inventoryOverlay);
    }
    public void removeInventory(Player player) {
        playerInventorys.remove(player.getUniqueId());
    }
    public boolean hasInventory(Player player) {
        return playerInventorys.containsKey(player.getUniqueId());
    }
    public InventoryOverlay getInventory(Player player) {
        return playerInventorys.getOrDefault(player.getUniqueId(), null);
    }
    public void openInventory(Player player, InventoryOverlay inventoryOverlay, int page) {
        player.openInventory(inventoryOverlay.getInventory(player, page));
        setInventory(player, inventoryOverlay);
    }

    public void setPage(Player player, int page) {
        removePage(player);
        if(page < 1) page = 1;
        playerPage.put(player.getUniqueId(), page);
    }
    public void removePage(Player player) {
        playerPage.remove(player.getUniqueId());
    }
    public int getPage(Player player) {
        return playerPage.getOrDefault(player.getUniqueId(), 1);
    }

    public void setPitch(Player player, double pitch) {
        playerPitch.remove(player.getUniqueId());
        playerPitch.put(player.getUniqueId(), pitch);
    }
    public void changePitch(Player player, boolean plus) {
        int currentpitch = (int) (playerPitch.getOrDefault(player.getUniqueId(), 1.0) * 10);
        if(plus) currentpitch++;
        else currentpitch--;
        playerPitch.remove(player.getUniqueId());
        playerPitch.put(player.getUniqueId(), ((double) currentpitch)/10);

    }
    public double getPitch(Player player) {
        return playerPitch.getOrDefault(player.getUniqueId(), 1.0);
    }

    public void setLastPlayedSound(Player player, Sound sound) {
        lastPlayedSound.remove(player.getUniqueId());
        lastPlayedSound.put(player.getUniqueId(), sound);
    }
    public Sound getLastPlayedSound(Player player) {
        return lastPlayedSound.getOrDefault(player.getUniqueId(), null);
    }


}
