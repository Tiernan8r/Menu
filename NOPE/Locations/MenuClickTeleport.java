package me.Tiernanator.Menu.Locations;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MenuClickTeleport {

	public static void menuClickTeleport(Player player, JavaPlugin plugin,
			Location teleportLocation) {
		
		World world = player.getWorld();
		Location playerLocation = player.getLocation();
		
		world.playEffect(playerLocation, Effect.ENDER_SIGNAL,
				1);
		//Each float is between 0 & 1
		//float 1 is volume, float 2 is the data value for each sound if it has more than one type
		world.playSound(playerLocation, Sound.ENTITY_ENDERMEN_TELEPORT, 0.75f, 1);
		
//		PlayerTagHandler.removeTextEntities(player);
		
		
//		PlayerTag.getPlayerTag(player).remove();
		
		player.teleport(teleportLocation);
		
//		PlayerTag.setUpDisplayName(player);
		
		//RESET THIS AND ABOVE...
//		PlayerTag.setUp(player);

		world.playEffect(playerLocation, Effect.ENDER_SIGNAL,
				1);
		player.playSound(playerLocation, Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
		//Each float is between 0 & 1
		//float 1 is volume, float 2 is the data value for each sound if it has more than one type
		world.playSound(playerLocation, Sound.ENTITY_ENDERMEN_TELEPORT, 0.75f, 1);
	}
}
