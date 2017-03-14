package me.Tiernanator.Menu.Events.Players;

import java.util.Collection;

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
import me.Tiernanator.Menu.Locations.MenuClickTeleport;
import me.Tiernanator.Packets.Titler.PlayerTitler;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;

public class PlayerNameTagTeleportMenuClick implements Listener {

	// 20 ticks = 1 second
	private int fadeInTicks = 20;
	private int stayTicks = 40;
	private int fadeOutTicks = 30;

	private Main plugin;

	public PlayerNameTagTeleportMenuClick(Main main) {
		plugin = main;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		Inventory inv = event.getInventory();
		if (!(inv.getTitle().equals(AllPlayersHeadMenuClick.menuName))) {
			return;
		}

		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if (!(item.getType() == Material.NAME_TAG)) {
			return;
		}
		String itemName = item.getItemMeta().getDisplayName();

		Collection<? extends Player> allPlayers = plugin.getServer()
				.getOnlinePlayers();

		for (Player onlinePlayer : allPlayers) {

			if (onlinePlayer.equals(player)) {
				continue;
			}

			String name = onlinePlayer.getName();
			name = ChatColor.DARK_GREEN + name;

			if (name.equals(itemName)) {

				Location toLocation = onlinePlayer.getLocation();

				MenuClickTeleport.menuClickTeleport(player, plugin, toLocation);
				String playerName = onlinePlayer.getName();

				PlayerTitler.playerTitle(player, "You have teleported to:",
						true, false, false, ChatColor.DARK_BLUE, fadeInTicks,
						stayTicks, fadeOutTicks, EnumTitleAction.TITLE);

				PlayerTitler.playerTitle(player, playerName, true, false, true,
						ChatColor.DARK_AQUA, fadeInTicks, stayTicks,
						fadeOutTicks, EnumTitleAction.SUBTITLE);

				event.setCancelled(true);
				player.closeInventory();
				return;
			}
		}
		player.sendMessage(
				ChatColor.DARK_RED + "Error 404 Player not found...");
		event.setCancelled(true);
	}

}
