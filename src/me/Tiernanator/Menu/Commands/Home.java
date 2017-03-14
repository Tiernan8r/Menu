package me.Tiernanator.Menu.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Tiernanator.Colours.Colour;
import me.Tiernanator.Menu.Main;
import me.Tiernanator.Packets.Titler.PlayerTitler;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;

public class Home implements CommandExecutor {

	@SuppressWarnings("unused")
	private static Main plugin;

	// 20 ticks = 1 second
	private int fadeInTicks = 20;
	private int stayTicks = 40;
	private int fadeOutTicks = 30;

	public Home(Main main) {
		plugin = main;
	}

	// Only works for a single command, unless you test if label = command
	// string:
	// if(label.equalsIgnoreCase("")) {} ,
	// per command
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		ChatColor good = Colour.GOOD.getColour();
		ChatColor highlight = Colour.HIGHLIGHT.getColour();

		if (sender instanceof Player) {
			Player player = (Player) sender;

			Location bedLocation = player.getBedSpawnLocation();

			if (bedLocation == null) {
//				player.sendMessage("Your home bed is missing or obstructed");
				PlayerTitler.playerTitle(player, "Your home bed is missing or obstructed", false, false, false, ChatColor.WHITE, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.ACTIONBAR);
				player.sendMessage(good + "You can use " + highlight + "/spawn"
						+ good + " to get to spawn.");
				return false;
			}

//			MenuClickTeleport.menuClickTeleport(player, plugin, bedLocation);
			player.teleport(bedLocation);

			PlayerTitler.playerTitle(player, "Welcome Home!", true, false,
					false, ChatColor.BLUE, fadeInTicks, stayTicks, fadeOutTicks,
					EnumTitleAction.TITLE);
			PlayerTitler.playerTitle(player, "", true, false, false, ChatColor.RED, fadeInTicks, stayTicks, fadeOutTicks, EnumTitleAction.SUBTITLE);

		}
		return false;
	}
}
