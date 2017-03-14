package me.Tiernanator.Menu.Events.FactionZones;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tiernanator.Menu.Main;

public class ZoneBeaconTeleportMenuClick implements Listener {

//	// 20 ticks = 1 second
//	private int fadeInTicks = 20;
//	private int stayTicks = 40;
//	private int fadeOutTicks = 30;
//	
//	private Main plugin;
	
	public ZoneBeaconTeleportMenuClick(Main main) {
//		plugin = main;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
//
		Inventory inv = event.getInventory();
		if (!(inv.getTitle().equals(FactionZonesBeaconMenuClick.menuName))) {
			return;
		}

//		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if (!(item.getType() == Material.CAKE)) {
			return;
		}
//		String itemName = item.getItemMeta().getDisplayName();
//		
//		List<Faction> allFactions = Faction.allFactions();
//		for(Faction faction : allFactions) {
//			
//			String name = faction.getName();
//			name = ChatColor.DARK_GRAY + name;
//			
//			if(name.equals(itemName)) {
//				
//				if(allFactions.indexOf(faction) == 0) {
//					
//					Location spawn = CoreZone.getZoneCentre("Spawn");
//					MenuClickTeleport.menuClickTeleport(player, plugin, spawn);
//					PlayerTitler.playerTitle(player, "Welcome to Spawn!", true, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.TITLE);
//					PlayerTitler.playerTitle(player, "", true, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.SUBTITLE);
//					
//				}
//				
//				Location centre = CoreZone.getZoneCentre(faction.getName());
//				
//				MenuClickTeleport.menuClickTeleport(player, plugin, centre);
//				String zoneName = faction.getName();
//				PlayerEnterCoreZoneEvent.addPlayerInSpecificZones(player, zoneName, true);
//
//				PlayerTitler.playerTitle(player,
//						"Welcome To The Faction:", true, false,
//						false, ChatColor.DARK_AQUA, fadeInTicks, stayTicks,
//						fadeOutTicks, EnumTitleAction.TITLE);
//				
//				PlayerTitler.playerTitle(player,
//						zoneName, true, false,
//						true, ChatColor.GRAY, fadeInTicks, stayTicks,
//						fadeOutTicks, EnumTitleAction.SUBTITLE);
//				
//				
//				event.setCancelled(true);
//				player.closeInventory();
//				return;
//			}
//		}
//		player.sendMessage(ChatColor.DARK_RED + "Error 404 Zone not found...");
		event.setCancelled(true);
	}

}
