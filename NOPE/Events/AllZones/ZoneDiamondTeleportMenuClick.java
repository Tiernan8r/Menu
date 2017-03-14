package me.Tiernanator.Menu.Events.AllZones;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tiernanator.Menu.Main;
import me.Tiernanator.Menu.Locations.MenuClickTeleport;
import me.Tiernanator.Packets.Titler.PlayerTitler;
import me.Tiernanator.Utilities.Locations.Zones.ZoneName;
import me.Tiernanator.Zoning.Zone.Zone;
import me.Tiernanator.Zoning.Zone.EventCallers.Player.OnPlayerEnterLeaveZone;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;

public class ZoneDiamondTeleportMenuClick implements Listener {

	// 20 ticks = 1 second
	private int fadeInTicks = 20;
	private int stayTicks = 40;
	private int fadeOutTicks = 30;
	
	private Main plugin;

	public ZoneDiamondTeleportMenuClick(Main main) {
		plugin = main;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		Inventory inv = event.getInventory();
		if (!(inv.getTitle().equals(AllZonesDiamondMenuClick.menuName))) {
			return;
		}

		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if (!(item.getType() == Material.DIAMOND)) {
			return;
		}
		String itemName = item.getItemMeta().getDisplayName();
		
		List<Zone> allZones = Zone.allZones();
		for(Zone zone : allZones) {
			
			String name = zone.getName();
			
			for(OfflinePlayer offlinePlayer : plugin.getServer().getOfflinePlayers()) {
				String playerUUID = offlinePlayer.getUniqueId().toString();
				String playerName = offlinePlayer.getName();
				
				if(name.equalsIgnoreCase(playerUUID)) {
					name = playerName;
				}
			}
			
			name = ChatColor.AQUA + name;
			
			if(name.equals(itemName)) {
				
				Location centre = zone.getCentre();
				
				MenuClickTeleport.menuClickTeleport(player, plugin, centre);
				
				String zoneName = zone.getDisplayName();
				for(OfflinePlayer offlinePlayer : plugin.getServer().getOfflinePlayers()) {
					String playerUUID = offlinePlayer.getUniqueId().toString();
					String playerName = offlinePlayer.getName();
					
					if(zoneName.equalsIgnoreCase(playerUUID)) {
						zoneName = playerName;
					}
				}
				OnPlayerEnterLeaveZone.addPlayerInSpecificZones(player, zone, true);
				zoneName = ZoneName.parseZoneCodeToName(zoneName);

				PlayerTitler.playerTitle(player,
						"Welcome To:", true, false,
						false, ChatColor.LIGHT_PURPLE, fadeInTicks, stayTicks,
						fadeOutTicks, EnumTitleAction.TITLE);
				
				PlayerTitler.playerTitle(player,
						zoneName, true, false,
						true, ChatColor.DARK_PURPLE, fadeInTicks, stayTicks,
						fadeOutTicks, EnumTitleAction.SUBTITLE);
				
				
				event.setCancelled(true);
				player.closeInventory();
				return;
			}
		}
		player.sendMessage(ChatColor.DARK_RED + "Error 404 Zone not found...");
		event.setCancelled(true);
	}

}
