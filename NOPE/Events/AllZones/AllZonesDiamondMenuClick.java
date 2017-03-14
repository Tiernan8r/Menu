package me.Tiernanator.Menu.Events.AllZones;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tiernanator.Menu.Main;
import me.Tiernanator.Menu.Commands.PlayerMenu;
import me.Tiernanator.Utilities.Items.Item;
import me.Tiernanator.Utilities.Locations.Zones.ZoneName;
import me.Tiernanator.Zoning.Zone.Zone;

public class AllZonesDiamondMenuClick implements Listener {

	private Main plugin;

	public static String menuName = "          All Zones";
	
	public AllZonesDiamondMenuClick(Main main) {
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
		if (!(item.getType() == Material.DIAMOND)) {
			return;
		}
		
		List<Zone> allZones = Zone.allZones();
		
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

		for(int i = 0; i < allZones.size(); i++) {
			
			Zone zone = allZones.get(i);
			
			String name = zone.getDisplayName();
	
			for(OfflinePlayer offlinePlayer : plugin.getServer().getOfflinePlayers()) {
				String playerUUID = offlinePlayer.getUniqueId().toString();
				String playerName = offlinePlayer.getName();
				
				if(name.equalsIgnoreCase(playerUUID)) {
					name = playerName;
				}
				
			}
			
			name = ZoneName.parseZoneCodeToName(name);
			
			ItemStack current = Item.renameItem(Material.DIAMOND,
					ChatColor.AQUA + name);
			
			zonesInventory.setItem(i, current);
		}
		player.closeInventory();
		player.openInventory(zonesInventory);
		event.setCancelled(true);
	}

}
