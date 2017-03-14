package me.Tiernanator.Menu.Events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
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
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;

public class HomeBedMenuClick implements Listener {

	// 20 ticks = 1 second
	private int fadeInTicks = 20;
	private int stayTicks = 40;
	private int fadeOutTicks = 30;
	
	private Main plugin;

	public HomeBedMenuClick(Main main) {
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

		if (!(item.getType() == Material.BED)) {
			return;
		}
		
		Location bedLocation = player.getBedSpawnLocation();
		
		if(bedLocation == null) {
			player.sendMessage("Your home bed is missing or obstructed");
//			Location spawn = CoreZone.getZoneCentre("Spawn");
//			MenuClickTeleport.menuClickTeleport(player, plugin, spawn);
			return;
		}

		MenuClickTeleport.menuClickTeleport(player, plugin, bedLocation);
		PlayerTitler.playerTitle(player, "Welcome Home!", true, false, false, ChatColor.BLUE, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.TITLE);
		PlayerTitler.playerTitle(player, "", true, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.SUBTITLE);

		event.setCancelled(true);
		player.closeInventory();
	}

}
