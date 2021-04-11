package me.Tiernanator.Menu;

import org.bukkit.plugin.java.JavaPlugin;

import me.Tiernanator.Menu.Commands.Home;
import me.Tiernanator.Menu.Commands.Spawn;
import me.Tiernanator.Menu.MenuAPI.PlayerMenu;

public class MenuMain extends JavaPlugin {
	
	@Override
	public void onEnable() {
		registerCommands();
		registerEvents();
		
//		GetLocation.setPlugin(this);
	}

	@Override
	public void onDisable() {
	}

	public void registerCommands() {
		getCommand("menu").setExecutor(new PlayerMenu(this));
		getCommand("spawn").setExecutor(new Spawn(this));
		getCommand("home").setExecutor(new Home(this));
	}

	public void registerEvents() {
//		PluginManager pm = getServer().getPluginManager();
//		pm.registerEvents(new SpawnCompassMenuClick(this), this);
//		pm.registerEvents(new BalanceGoldMenuClick(this), this);
//		pm.registerEvents(new PowerExperienceBottleMenuClick(this), this);
//		pm.registerEvents(new MarketEmeraldMenuClick(this), this);
//		pm.registerEvents(new NetherLavaMenuClick(this), this);
//		pm.registerEvents(new EndEggMenuClick(this), this);
//		pm.registerEvents(new HomeBedMenuClick(this), this);
//		pm.registerEvents(new ZoneCookieMenuClick(this), this);
//		
//		pm.registerEvents(new AllZonesDiamondMenuClick(this), this);
//		pm.registerEvents(new ZoneDiamondTeleportMenuClick(this), this);
//		
//		pm.registerEvents(new FactionZonesBeaconMenuClick(this), this);
//		pm.registerEvents(new ZoneBeaconTeleportMenuClick(this), this);
//		
//		pm.registerEvents(new AllPlayersHeadMenuClick(this), this);
//		pm.registerEvents(new PlayerNameTagTeleportMenuClick(this), this);
//		
//		pm.registerEvents(new FactionPrismarineMenuClick(this), this);
//		pm.registerEvents(new GroupGoldenAppleMenuClick(this), this);
//		
//		pm.registerEvents(new CraftingTableMenuClick(this), this);
//		pm.registerEvents(new EnchantingTableMenuClick(this), this);
//		pm.registerEvents(new EnderChestMenuClick(this), this);
//		pm.registerEvents(new AnvilMenuClick(this), this);
	}

}
