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
import me.Tiernanator.Menu.Locations.GetLocation;
import me.Tiernanator.Menu.Locations.MenuClickTeleport;
import me.Tiernanator.Packets.Titler.PlayerTitler;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;

public class NetherLavaMenuClick implements Listener {

	// 20 ticks = 1 second
	private int fadeInTicks = 20;
	private int stayTicks = 40;
	private int fadeOutTicks = 30;

	private Main plugin;

	public NetherLavaMenuClick(Main main) {
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
		if (!(item.getType() == Material.LAVA_BUCKET)) {
			return;
		}

		event.setCancelled(true);
		
		Location nether = GetLocation.getLocation("Nether", "world_nether");
		MenuClickTeleport.menuClickTeleport(player, plugin, nether);

		PlayerTitler.playerTitle(player, "Welcome to The Nether!", true, false, false, ChatColor.GOLD, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.TITLE);
		PlayerTitler.playerTitle(player, "", true, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.SUBTITLE);
		
		event.setCancelled(true);
		player.closeInventory();
	}

}
