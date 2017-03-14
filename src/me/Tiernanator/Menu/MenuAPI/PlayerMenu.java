package me.Tiernanator.Menu.MenuAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.Tiernanator.Factions.Factions.Faction;
import me.Tiernanator.Factions.Factions.FactionAccessor;
import me.Tiernanator.Meconomics.Currency;
import me.Tiernanator.Menu.Main;
import me.Tiernanator.Menu.Menu;
import me.Tiernanator.Menu.MenuAction;
import me.Tiernanator.Menu.MenuEntry;
import me.Tiernanator.Permissions.Group.Group;
import me.Tiernanator.Permissions.Group.GroupAccessor;
import me.Tiernanator.Zoning.Zone.Zone;

public class PlayerMenu implements CommandExecutor {

	private static Main plugin;

	public PlayerMenu(Main main) {
		plugin = main;
	}

	// private static Menu menu;

	public static String menuName = "          User Helper";

	// Only works for a single command, unless you test if label = command
	// string:
	// if(label.equalsIgnoreCase("")) {} ,
	// per command
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			makeMenu(player);

		}
		return false;
	}

	public static void makeMenu(Player player) {

		List<MenuEntry> menuEntries = new ArrayList<MenuEntry>();
		// Make a skull that can open a menu to teleport to all players;
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1,
				(short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(player.getName());
		meta.setDisplayName(ChatColor.DARK_GREEN + "Players");
		skull.setItemMeta(meta);
		// When you click the skull, it opens a new menu with a list of
		// player
		// names which you
		// can click on to teleport to
		List<MenuEntry> playerMenuEntries = new ArrayList<MenuEntry>();
		Collection<? extends Player> onlinePlayers = plugin.getServer().getOnlinePlayers();
		for (Player onlinePlayer : onlinePlayers) {
			if(onlinePlayer.equals(player)) {
				continue;
			}
			String name = ChatColor.DARK_GREEN + onlinePlayer.getName();
			MenuEntry tagEntry = new MenuEntry(name,
					new ItemStack(Material.NAME_TAG), MenuAction.TELEPORT,
					onlinePlayer);
			playerMenuEntries.add(tagEntry);
		}
		
		Menu playerMenu = new Menu("All Players", playerMenuEntries);

		if (!playerMenuEntries.isEmpty()) {

			MenuEntry skullEntry = new MenuEntry(
					ChatColor.DARK_GREEN + "Players", skull, MenuAction.OPEN,
					playerMenu, 13);
			menuEntries.add(skullEntry);

		}

		FactionAccessor factionAccessor = new FactionAccessor(player);
		Faction playerFaction = factionAccessor.getPlayerFaction();
		// Show the player's monetary funds
		String balanceString = ChatColor.GOLD
				+ String.format("%.2f", Currency.getPlayerBalance(player)) + " "
				+ ChatColor.AQUA + playerFaction.getCurrency();
		MenuEntry goldIngot = new MenuEntry(balanceString,
				new ItemStack(Material.GOLD_INGOT), MenuAction.NOTHING, null,
				17);
		menuEntries.add(goldIngot);

		Zone spawn = Zone.getZone("Spawn");
		if (spawn != null) {

			MenuEntry compass = new MenuEntry(ChatColor.RED + "Spawn",
					new ItemStack(Material.COMPASS), MenuAction.TELEPORT,
					spawn.getCentre(), 4);
			menuEntries.add(compass);

		}

		List<MenuEntry> zoneMenuEntries = new ArrayList<MenuEntry>();
		List<Zone> allZones = Zone.allZones();
		if (allZones != null) {
			for (Zone zone : allZones) {
				String displayName = zone.getDisplayName();
				String name = ChatColor.AQUA + displayName;
				Location teleportLocation = zone.getCentre();
				MenuEntry zoneEntry = new MenuEntry(name,
						new ItemStack(Material.DIAMOND), MenuAction.TELEPORT,
						teleportLocation);
				boolean contains = false;
				for (MenuEntry entry : zoneMenuEntries) {
					String entryName = entry.getEntryName();
					if (entryName.equalsIgnoreCase(name)) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					zoneMenuEntries.add(zoneEntry);
				}
			}
		}

		if (!zoneMenuEntries.isEmpty()) {

			Menu allZonesMenu = new Menu("All Zones", zoneMenuEntries);
			MenuEntry zonesEntry = new MenuEntry(ChatColor.AQUA + "All Zones",
					new ItemStack(Material.DIAMOND), MenuAction.OPEN,
					allZonesMenu, 10);
			menuEntries.add(zonesEntry);

		}

		Object bedVariable = player.getBedSpawnLocation();
		MenuAction bedAction = MenuAction.TELEPORT;
		if (bedVariable == null) {
			bedVariable = ChatColor.WHITE
					+ "Your home bed is missing or obstructed.";
			bedAction = MenuAction.MESSAGE;
		}
		MenuEntry bed = new MenuEntry(ChatColor.BLUE + "Home",
				new ItemStack(Material.BED), bedAction, bedVariable, 2);
		menuEntries.add(bed);

		String playerUUID = player.getUniqueId().toString();
		Zone playerZone = Zone.getPersonnelZone(playerUUID);
		if (playerZone != null) {

			String name = playerZone.getDisplayName();
			ItemStack cookieItem = new ItemStack(Material.COOKIE);
			String zoneName = ChatColor.GOLD + name;
			Location zoneLocation = playerZone.getCentre();
			MenuEntry cookie = new MenuEntry(zoneName, cookieItem,
					MenuAction.TELEPORT, zoneLocation, 1);
			menuEntries.add(cookie);

		}

		Zone theEnd = Zone.getZone("The End");
		if (theEnd != null) {

			MenuEntry dragonEgg = new MenuEntry(ChatColor.DARK_PURPLE + "The End",
					new ItemStack(Material.DRAGON_EGG), MenuAction.TELEPORT,
					theEnd.getCentre(), 5);
			menuEntries.add(dragonEgg);

		}
		
		Zone nether = Zone.getZone("The Nether");
		if (nether != null) {

			MenuEntry lava = new MenuEntry(ChatColor.DARK_RED + "The Nether",
					new ItemStack(Material.LAVA_BUCKET), MenuAction.TELEPORT,
					nether.getCentre(), 3);
			menuEntries.add(lava);

		}

		String factionString = ChatColor.YELLOW + "Faction: "
				+ ChatColor.DARK_PURPLE + playerFaction.getName();

		MenuEntry faction = new MenuEntry(factionString,
				new ItemStack(Material.PRISMARINE_SHARD), MenuAction.NOTHING,
				null, 9);
		menuEntries.add(faction);

		GroupAccessor groupAccessor = new GroupAccessor(player);
		Group playerGroup = groupAccessor.getPlayerGroup();
		String groupString = ChatColor.DARK_GREEN + "Group: "
				+ ChatColor.LIGHT_PURPLE + playerGroup.getName();

		MenuEntry group = new MenuEntry(groupString,
				new ItemStack(Material.GOLDEN_APPLE), MenuAction.NOTHING, null,
				0);
		menuEntries.add(group);

		Inventory workBench = Bukkit.createInventory(player,
				InventoryType.WORKBENCH);
		MenuEntry craftingTable = new MenuEntry(ChatColor.GRAY + "Crafting",
				new ItemStack(Material.WORKBENCH), MenuAction.OPEN, workBench,
				12);
		menuEntries.add(craftingTable);

		Inventory enchantingInventory = Bukkit.createInventory(player,
				InventoryType.ENCHANTING);
		MenuEntry enchantingTable = new MenuEntry(
				ChatColor.GREEN + "Enchanting",
				new ItemStack(Material.ENCHANTMENT_TABLE), MenuAction.OPEN,
				enchantingInventory, 14);
		menuEntries.add(enchantingTable);

		Inventory anvilInventory = (Inventory) Bukkit.createInventory(player,
				InventoryType.ANVIL);
		MenuEntry anvil = new MenuEntry(ChatColor.GRAY + "Repairing",
				new ItemStack(Material.ANVIL), MenuAction.OPEN, anvilInventory,
				15);
		menuEntries.add(anvil);

		Inventory enderChestContents = (Inventory) player.getEnderChest();
		MenuEntry enderChest = new MenuEntry(
				ChatColor.DARK_PURPLE + "Ender Chest",
				new ItemStack(Material.ENDER_CHEST), MenuAction.OPEN,
				enderChestContents, 11);
		menuEntries.add(enderChest);
		Menu menu = new Menu(menuName, menuEntries, 18);
		menu.makeMenu(player);
	}

}
