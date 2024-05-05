package de.lordjulixn.sounds.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta itemmeta;

    public ItemBuilder(Material material) {
        //
        this.item = new ItemStack(material);
        this.itemmeta = item.getItemMeta();
        //
    }
    public ItemBuilder(ItemStack item) {
        //
        this.item = item;
        this.itemmeta = item.getItemMeta();
        //
    }
    public ItemBuilder(Material material, Color color) {
        //
        this.item = new ItemStack(material);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(color);
        item.setItemMeta(meta);
        this.itemmeta = item.getItemMeta();
        //
    }
    public ItemBuilder(Material material, String skullowner) {
        //
        this.item = new ItemStack(material, 1, (short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(skullowner);
        item.setItemMeta(meta);
        this.itemmeta = item.getItemMeta();
        //
    }
    public ItemBuilder(ItemStack item, String skullowner) {
        //
        this.item = item;
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(skullowner);
        item.setItemMeta(meta);
        this.itemmeta = item.getItemMeta();
        //
    }
    public ItemBuilder setDisplayName(String name) {
        //
        itemmeta.setDisplayName(name);
        return this;
        //
    }
    public ItemBuilder setLore(String... lore) {
        //
        ArrayList<String> toSet = new ArrayList<>();
        for(String strings : lore) {
            if(strings != null) toSet.add(strings);
        }
        itemmeta.setLore(toSet);
        return this;
        //
    }
    public ItemBuilder setLore(ArrayList<String> lore) {
        //
        itemmeta.setLore(lore);
        return this;
        //
    }
    public ItemBuilder hideAttributes() {
        //
        itemmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemmeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemmeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemmeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
        //
    }
    public ItemBuilder setUnbreakable(boolean unbreakable) {
        //
        itemmeta.setUnbreakable(unbreakable);
        return this;
        //
    }
    public ItemBuilder setAmount(int amount) {
        //
        item.setAmount(amount);
        return this;
        //
    }
    public ItemBuilder addEnchant(Enchantment enchantment, boolean idk, int amount) {
        //
        itemmeta.addEnchant(enchantment, amount, idk);
        return this;
        //
    }
    public ItemStack build() {
        //
        item.setItemMeta(itemmeta);
        return item;
        //
    }
    public ItemBuilder addGlow() {
        //
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        itemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
        //
    }
    public static ItemStack getCustomSkull(String base64) {

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if (base64.isEmpty()) return head;

        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", base64));

        try {
            Method mtd = skullMeta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(skullMeta, profile);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            ex.printStackTrace();
        }

        head.setItemMeta(skullMeta);
        return head;
    }



}
