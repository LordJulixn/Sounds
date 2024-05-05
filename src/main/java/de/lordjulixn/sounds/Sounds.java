package de.lordjulixn.sounds;

import de.lordjulixn.sounds.commands.CMD_Sounds;
import de.lordjulixn.sounds.listener.BasicListener;
import de.lordjulixn.sounds.listener.InventoryClickListener;
import de.lordjulixn.sounds.utils.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Sounds extends JavaPlugin {

    public static String prefix = "§8【§eSounds§8】 §7";
    private static InventoryManager inventoryManager;

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage("§8§m--------------------------------------------------");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§e   _____                       _      ");
        Bukkit.getConsoleSender().sendMessage("§e  / ____|                     | |     ");
        Bukkit.getConsoleSender().sendMessage("§e | (___   ___  _   _ _ __   __| |___  ");
        Bukkit.getConsoleSender().sendMessage("§e  \\___ \\ / _ \\| | | | '_ \\ / _` / __| ");
        Bukkit.getConsoleSender().sendMessage("§e  ____) | (_) | |_| | | | | (_| \\__ \\ ");
        Bukkit.getConsoleSender().sendMessage("§e |_____/ \\___/ \\__,_|_| |_|\\__,_|___/ ");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(" §8[§bSounds§8] §7Das Plugin wurde §aaktiviert§7! ");
        Bukkit.getConsoleSender().sendMessage(" §8[§bSounds§8] §7Version §e"+getDescription().getVersion()+" §7by §e" +
                ""+getDescription().getAuthors().get(0)+" ");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§8--------------------------------------------------");

        inventoryManager = new InventoryManager();

        getCommand("sounds").setExecutor(new CMD_Sounds());

        Bukkit.getPluginManager().registerEvents(new BasicListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8§m--------------------------------------------------");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage(" §8[§bSounds§8] §7Das Plugin wurde §cdeaktiviert§7! ");
        Bukkit.getConsoleSender().sendMessage(" §8[§bSounds§8] §7Version §e"+getDescription().getVersion()+" §7by §e" +
                ""+getDescription().getAuthors().get(0)+" ");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§8--------------------------------------------------");

    }

    public static InventoryManager getInventoryManager() {
        return inventoryManager;
    }

}
