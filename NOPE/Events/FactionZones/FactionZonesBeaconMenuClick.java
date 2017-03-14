package me.Tiernanator.Menu.Events.FactionZones;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tiernanator.Factions.Factions.Faction;
import me.Tiernanator.Menu.Main;
import me.Tiernanator.Menu.Commands.PlayerMenu;
import me.Tiernanator.Utilities.Items.Item;

public class FactionZonesBeaconMenuClick implements Listener {

	@SuppressWarnings("unused")
	private Main plugin;
	
	public static String menuName = "          Faction Zones";
	
	public FactionZonesBeaconMenuClick(Main main) {
		plugin = main;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		Inventory inv = event.getInventory();
		if (!(inv.getTitle().equals(PlayerMenu.menuName))) {
			return;
		}
		
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if (!(item.getType() == Material.BEACON)) {
			return;
		}
		
		List<Faction> allZones = Faction.allFactions();
		
		if(allZones == null) {
			event.setCancelled(true);
			return;
		}
		
		int zoneSize = allZones.size();

		double fraction = Math.ceil(zoneSize / 9.0);
		int size = (int) (fraction * 9);
		if(size == 0) {
			size = 9;
		}
		
		Inventory zonesInventory = Bukkit.createInventory(null, size,
				menuName);

		// - 1 : Rogues don't have a zone
		for(int i = 0; i < allZones.size() - 1; i++) {
			
			String name = allZones.get(i).getName();
	
			ItemStack current = Item.renameItem(Material.CAKE,
					ChatColor.DARK_GRAY + name);
			
			zonesInventory.setItem(i * 2, current);
		}
		player.closeInventory();
		player.openInventory(zonesInventory);
		event.setCancelled(true);
	}

}
