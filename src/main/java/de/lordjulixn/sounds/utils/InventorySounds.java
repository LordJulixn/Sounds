package de.lordjulixn.sounds.utils;

import de.lordjulixn.sounds.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;

public class InventorySounds extends InventoryOverlay {

    public static String inventoryName = "§8» §eSounds";
    private static int itemsPerPage = 45;

    @Override
    public String getInventoryName() {
        return inventoryName;
    }

    @Override
    public Inventory getInventory(Player player, int page) {

        Inventory inventory = Bukkit.createInventory(null, 54, inventoryName+" §8| §8Page §b"+page+"§8/§b"+maxPages());

        ItemStack glass = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName("§8").build();
        for(int i = 45; i < inventory.getSize(); i++) inventory.setItem(i, glass);
        inventory.setItem(53, new ItemBuilder(ItemBuilder.getCustomSkull(Textures.playerHeadNext)).setDisplayName("§7Nächste Seite").build());
        inventory.setItem(45, new ItemBuilder(ItemBuilder.getCustomSkull(Textures.playerHeadPrev)).setDisplayName("§7Vorherige Seite").build());
        inventory.setItem(48, new ItemBuilder(ItemBuilder.getCustomSkull(Textures.playerHeadMinus)).setDisplayName("§3Pitch §a§l-").build());
        inventory.setItem(50, new ItemBuilder(ItemBuilder.getCustomSkull(Textures.playerHeadPlus)).setDisplayName("§3Pitch §a§l+").build());
        inventory.setItem(49, new ItemBuilder(Material.NOTE_BLOCK).setDisplayName("§3Pitch §8» §a"+ Sounds.getInventoryManager().getPitch(player))
                .setLore("§7Klicken zum zurücksetzen").build());

        int from = (page - 1) * itemsPerPage;
        int to = page * itemsPerPage - 1;

        ArrayList<Sound> sounds = new ArrayList<>(Arrays.asList(Sound.values()));

        Sound lastPlayedSound = Sounds.getInventoryManager().getLastPlayedSound(player);
        for(int i = from; i <= to; i++) {
            if(i >= sounds.size()) break;
            if(lastPlayedSound != null && lastPlayedSound == sounds.get(i)) {
                //Last Played Sound
                inventory.addItem(new ItemBuilder(ItemBuilder.getCustomSkull(Textures.playerHeadPlay))
                        .setDisplayName("§a"+sounds.get(i).name()).build());
            } else {
                //Not Last Played Sound
                Material material = Material.PAPER;
                if(sounds.get(i).name().startsWith("BLOCK_NOTE_BLOCK")) material = Material.NOTE_BLOCK;
                if(sounds.get(i).name().startsWith("ENTITY")) material = Material.SKELETON_SPAWN_EGG;

                if(sounds.get(i).name().startsWith("MUSIC")) material = Material.MUSIC_DISC_5;
                if(sounds.get(i).name().startsWith("ITEM_GOAT_HORN")) material = Material.GOAT_HORN;

                inventory.addItem(new ItemBuilder(material).setDisplayName("§a"+sounds.get(i).name()).build());
            }
        }

        return inventory;
    }

    public static int maxPages() {
        int items = Sound.values().length;
        if(items % itemsPerPage == 0) return (int) items/itemsPerPage;
        return ((int) items/itemsPerPage) + 1;
    }
}
