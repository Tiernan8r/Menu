package me.Tiernanator.Menu.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tiernanator.Menu.Main;
import me.Tiernanator.Menu.Commands.PlayerMenu;

public class GroupGoldenAppleMenuClick implements Listener {

	public GroupGoldenAppleMenuClick(Main main) {
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		
		Inventory inv = event.getInventory();
		if (!(inv.getTitle().equals(PlayerMenu.menuName))) {
			return;
		}

		ItemStack item = event.getCurrentItem();
		if (!(item.getType() == Material.GOLDEN_APPLE)) {
			return;
		}

		event.setCancelled(true);

	}

}