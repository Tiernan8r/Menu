package me.Tiernanator.Menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tiernanator.Utilities.Items.Item;

//import me.Tiernanator.TestPlugin;

public class InventorySword implements CommandExecutor {

	// Only works for a single command, unless you test if label = command string:
	// if(label.equalsIgnoreCase("")) {} ,
	// per command

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Inventory inv = Bukkit.createInventory(null, 9, "Equip Inventory");
			
			ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
			diamondSword.addUnsafeEnchantment(Enchantment.DIG_SPEED, 999);
			diamondSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 999);
			diamondSword.addUnsafeEnchantment(Enchantment.OXYGEN, 999);
			Item.renameItem(diamondSword, ChatColor.DARK_RED + "Sword");
			
			//Add as many ItemStacks as you wish, separate them by a comma(,)
			inv.addItem(diamondSword);
			player.openInventory(inv);
		}
		return false;
	}

}
