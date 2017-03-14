package me.Tiernanator.Menu.Locations;

import java.util.List;

import org.bukkit.Location;

import me.Tiernanator.Config.ConfigAccessor;
import me.Tiernanator.Menu.Main;

public class GetLocation {
	
	private static Main plugin;
	
	public static void setPlugin(Main main) {
		plugin = main;
	}
	
	public static Location getLocation(String path, String world) {
		
//		if(path.equalsIgnoreCase("Spawn")) {
//			return GetCoreZones.getCoreZoneCentre("Spawn");
//		}
		ConfigAccessor locationsAccessor = new ConfigAccessor(plugin, "locations.yml");
		
		List<String> location = locationsAccessor.getConfig().getStringList(path);
		
		Double x = Double.parseDouble(location.get(0));
		Double y = Double.parseDouble(location.get(1));
		Double z = Double.parseDouble(location.get(2));
		
		return new Location(plugin.getServer().getWorld(world), x, y, z);
	}
}
