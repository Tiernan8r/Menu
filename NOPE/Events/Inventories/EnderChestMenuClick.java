package me.Tiernanator.Menu.Events.Inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tiernanator.Menu.Main;
import me.Tiernanator.Menu.Commands.PlayerMenu;

public class EnderChestMenuClick implements Listener {

	public EnderChestMenuClick(Main main) {
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		Inventory inv = event.getInventory();
		if (!(inv.getTitle().equals(PlayerMenu.menuName))) {
			return;
		}

		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if (!(item.getType() == Material.ENDER_CHEST)) {
			return;
		}

		Inventory enderChest = player.getEnderChest();
		
		event.setCancelled(true);
		player.closeInventory();
		player.openInventory(enderChest);
	}

}
