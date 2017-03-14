package me.Tiernanator.Menu.Events;

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
import me.Tiernanator.Menu.Commands.PlayerMenu;
import me.Tiernanator.Menu.Locations.MenuClickTeleport;
import me.Tiernanator.Packets.Titler.PlayerTitler;
import me.Tiernanator.Utilities.Locations.Zones.ZoneName;
import me.Tiernanator.Zoning.Zone.Zone;
import me.Tiernanator.Zoning.Zone.EventCallers.Player.OnPlayerEnterLeaveZone;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;

public class ZoneCookieMenuClick implements Listener {

	// 20 ticks = 1 second
	private int fadeInTicks = 20;
	private int stayTicks = 40;
	private int fadeOutTicks = 30;
	
	private Main plugin;

	public ZoneCookieMenuClick(Main main) {
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
		if (!(item.getType() == Material.COOKIE)) {
			return;
		}
		List<Zone> allZones = Zone.allZones();
		if(allZones == null) {
			event.setCancelled(true);
			return;
		}
//		for(Zone zone : allZones) {
			String playerUUID = player.getUniqueId().toString();
//			String owner = zone.getOwnerUUIDs();
//			if(owner.equals(player.getUniqueId().toString())) {
		Zone zone = Zone.getPersonnelZone(playerUUID);
		if(zone == null) {
			player.sendMessage("You don't have a zone.");
			event.setCancelled(true);
			return;
		}
				
				Location centre = zone.getCentre();
				
				MenuClickTeleport.menuClickTeleport(player, plugin, centre);
				
				String zoneName = zone.getDisplayName();
				for(OfflinePlayer offlinePlayer : plugin.getServer().getOfflinePlayers()) {
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
//			}
//		}
//		player.sendMessage("You don't have a zone.");
//		event.setCancelled(true);
	}

}
