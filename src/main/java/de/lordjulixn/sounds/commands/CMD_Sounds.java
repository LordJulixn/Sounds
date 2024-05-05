package de.lordjulixn.sounds.commands;

import de.lordjulixn.sounds.Sounds;
import de.lordjulixn.sounds.utils.InventorySounds;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Sounds implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!command.getName().equalsIgnoreCase("sounds")) return false;
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(!player.hasPermission("Sounds.All")) {
            player.sendMessage(Sounds.prefix+"Dazu hast du §ckeine Rechte§7!");
            return true;
        }
        if(args.length != 0) {
            player.sendMessage(Sounds.prefix+"§7Bitte benutze §a/Sounds§7!");
            return true;
        }

        Sounds.getInventoryManager().openInventory(player, new InventorySounds(), Sounds.getInventoryManager().getPage(player));

        return false;
    }

}
