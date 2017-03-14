package me.Tiernanator.Menu.Events;

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
import me.Tiernanator.Menu.Locations.MenuClickTeleport;
import me.Tiernanator.Packets.Titler.PlayerTitler;
import me.Tiernanator.Zoning.Zone.Zone;
import me.Tiernanator.Zoning.Zone.EventCallers.Player.OnPlayerEnterLeaveZone;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;

public class SpawnCompassMenuClick implements Listener {

	// 20 ticks = 1 second
	private int fadeInTicks = 20;
	private int stayTicks = 40;
	private int fadeOutTicks = 30;

	private Main plugin;

	public SpawnCompassMenuClick(Main main) {
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
		if (!(item.getType() == Material.COMPASS)) {
			return ;
		}
//		Location spawn = CoreZone.getZoneCentre("Spawn");
		Zone spawn = Zone.getZone("Spawn");
		if(spawn == null) {
			PlayerTitler.playerTitle(player, "There is no Spawn...", false, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.ACTIONBAR);
			return;
		}
		MenuClickTeleport.menuClickTeleport(player, plugin, spawn.getCentre());
		
		OnPlayerEnterLeaveZone.addPlayerInSpecificZones(player, spawn, true);
		PlayerTitler.playerTitle(player, "Welcome to Spawn!", true, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.TITLE);
		PlayerTitler.playerTitle(player, "", true, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.SUBTITLE);
		
		event.setCancelled(true);
		player.closeInventory();
	}

}
