package me.Tiernanator.Menu.Events.Players;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tiernanator.Menu.Main;
import me.Tiernanator.Menu.Commands.PlayerMenu;
import me.Tiernanator.Utilities.Items.Item;

public class AllPlayersHeadMenuClick implements Listener {

	private Main plugin;
	
	public static String menuName = "          All Players";
	
	public AllPlayersHeadMenuClick(Main main) {
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
		if (!(item.getType() == Material.SKULL_ITEM)) {
			return;
		}
		
		Collection<? extends Player> allPlayers = plugin.getServer().getOnlinePlayers();
		
		int zoneSize = allPlayers.size();
		double fraction = Math.ceil(zoneSize / 9.0);
		int size = (int) (fraction * 9);
		if(size == 0) {
			size = 9;
		}

		Inventory zonesInventory = Bukkit.createInventory(null, size,
				menuName);
		int i = 0;
		for(Player onlinePlayer : allPlayers) {

			if(!(onlinePlayer.equals(player))) {
				String name = onlinePlayer.getName();
				
				ItemStack current = Item.renameItem(Material.NAME_TAG,
						ChatColor.DARK_GREEN + name);
				
				zonesInventory.setItem(i, current);
				
				i += 1;
				
			}
		}
		player.closeInventory();
		player.openInventory(zonesInventory);
		event.setCancelled(true);
	}

}
