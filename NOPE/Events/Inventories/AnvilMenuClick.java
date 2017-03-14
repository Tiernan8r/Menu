package me.Tiernanator.Menu.Events.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tiernanator.Menu.Main;
import me.Tiernanator.Menu.Commands.PlayerMenu;

public class AnvilMenuClick implements Listener {

	public AnvilMenuClick(Main main) {
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		Inventory inv = event.getInventory();
		if (!(inv.getTitle().equals(PlayerMenu.menuName))) {
			return;
		}

		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if (!(item.getType() == Material.ANVIL)) {
			return;
		}

		Inventory anvilInventory = Bukkit.createInventory(player, InventoryType.ANVIL);
		
		event.setCancelled(true);
		player.closeInventory();
		player.openInventory(anvilInventory);
	}

}
