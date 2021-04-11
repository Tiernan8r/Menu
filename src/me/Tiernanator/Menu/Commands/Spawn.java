package me.Tiernanator.Menu.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tiernanator.Menu.MenuMain;
import me.Tiernanator.Utilities.Packets.Titler.PlayerTitler;
import me.Tiernanator.Utilities.Packets.Titler.TitleAction;
import me.Tiernanator.Zoning.Zone.Zone;
import me.Tiernanator.Zoning.Zone.EventCallers.Player.OnPlayerEnterLeaveZone;

public class Spawn implements CommandExecutor {

	@SuppressWarnings("unused")
	private static MenuMain plugin;
	
	// 20 ticks = 1 second
	private int fadeInTicks = 20;
	private int stayTicks = 40;
	private int fadeOutTicks = 30;
	
	 public Spawn(MenuMain main) {
		 plugin = main;
	 }

	// Only works for a single command, unless you test if label = command
	// string:
	// if(label.equalsIgnoreCase("")) {} ,
	// per command
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		if (sender instanceof Player) {
			
			Player player = (Player) sender;
			
			Zone spawn = Zone.getZone("Spawn");
			if(spawn == null) {
				PlayerTitler.playerTitle(player, "There is no Spawn...", false, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, TitleAction.ACTIONBAR);
				return false;
			}
//			MenuClickTeleport.menuClickTeleport(player, plugin, spawn.getCentre());
			player.teleport(spawn.getCentre());
//			PlayerEnterSubZoneEvent.addPlayerInSpecificZones(player, "Spawn", true);
			OnPlayerEnterLeaveZone.addPlayerInSpecificZones(player, spawn, true);
			
			
			PlayerTitler.playerTitle(player, "Welcome to Spawn!", true, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, TitleAction.TITLE);
			PlayerTitler.playerTitle(player, "", true, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, TitleAction.SUBTITLE);


		}
		return false;
	}
}
