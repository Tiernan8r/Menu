package me.Tiernanator.Menu.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.Tiernanator.Factions.Factions.Faction;
import me.Tiernanator.Factions.Factions.FactionAccessor;
import me.Tiernanator.Meconomics.Currency;
import me.Tiernanator.Menu.Main;
import me.Tiernanator.Permissions.Group.Group;
import me.Tiernanator.Permissions.Group.GroupAccessor;
import me.Tiernanator.Utilities.Items.Item;
import me.Tiernanator.Zoning.Zone.Zone;

public class PlayerMenu implements CommandExecutor {

	@SuppressWarnings("unused")
	private static Main plugin;

	public PlayerMenu(Main main) {
		plugin = main;
	}

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

		Inventory inv = Bukkit.createInventory(null, 18, menuName);

		// Make a skull that can open a menu to teleport to all players;
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1,
				(short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(player.getName());
		meta.setDisplayName(ChatColor.DARK_GREEN + "Players");
		skull.setItemMeta(meta);
		// When you click the skull, it opens a new menu with a list of player
		// names which you
		// can click on to teleport to

		FactionAccessor factionAccessor = new FactionAccessor(player);
		Faction playerFaction = factionAccessor.getPlayerFaction();
		// Show the player's monetary funds
		String balanceString = ChatColor.GOLD
				+ String.format("%.2f", Currency.getPlayerBalance(player)) + " "
				+ ChatColor.AQUA + playerFaction.getCurrency();

		ItemStack goldIngot = Item.renameItem(Material.GOLD_INGOT,
				balanceString);

		// ItemStack expBottle = Item.renameItem(Material.EXP_BOTTLE,
		// ChatColor.DARK_PURPLE + "Power " + ChatColor.GREEN +
		// Integer.toString(Power.getPlayerPower(player)));

		ItemStack compass = Item.renameItem(Material.COMPASS,
				ChatColor.RED + "Spawn");
		// Create a link to the spawn
		ItemStack diamond = Item.renameItem(Material.DIAMOND,
				ChatColor.AQUA + "All Zones");

		// These aren't a thang no more....
		// ItemStack beacon = Item.renameItem(Material.BEACON,
		// ChatColor.DARK_AQUA + "Faction Zones");

		ItemStack emerald = Item.renameItem(Material.EMERALD,
				ChatColor.GREEN + "Market");

		ItemStack bed = Item.renameItem(Material.BED, ChatColor.BLUE + "Home");

		// String playerUUID = player.getUniqueId().toString();
		// List<String> allZones = SubZone.allZones();
		// if(!(allZones == null)) {
		// for(String zone : allZones) {
		// String owner = SubZone.getZoneOwner(zone);
		// if(owner.equals(playerUUID)) {
		// name = SubZone.getZoneDisplayName(zone);
		// name = ZoneName.parseZoneCodeToName(name);
		// }
		// }
		// }
		String playerUUID = player.getUniqueId().toString();
		Zone playerZone = Zone.getPersonnelZone(playerUUID);
		String name = playerZone.getDisplayName();
//		if (name != null) {
//			if (name.equalsIgnoreCase(playerUUID)) {
//				name = PlayerLogger.getPlayerNameByUUID(playerUUID);
//			}
//		}
		ItemStack cookie = null;
		if (name != null) {
			cookie = Item.renameItem(Material.COOKIE, ChatColor.GOLD + name);
		}

		ItemStack dragonEgg = Item.renameItem(Material.DRAGON_EGG,
				ChatColor.DARK_PURPLE + "The End");

		ItemStack lava = Item.renameItem(Material.LAVA_BUCKET,
				ChatColor.DARK_RED + "The Nether");

		ItemStack faction = Item.renameItem(Material.PRISMARINE_SHARD,
				ChatColor.YELLOW + "Faction: " + ChatColor.DARK_PURPLE
						+ playerFaction.getName());

		GroupAccessor groupAccessor = new GroupAccessor(player);
		Group playerGroup = groupAccessor.getPlayerGroup();
		ItemStack group = Item.renameItem(Material.GOLDEN_APPLE,
				ChatColor.DARK_GREEN + "Group: " + ChatColor.LIGHT_PURPLE
						+ playerGroup.getName());

		ItemStack workBench = Item.renameItem(Material.WORKBENCH,
				ChatColor.DARK_GRAY + "Crafting");

		ItemStack enchantingTable = Item.renameItem(Material.ENCHANTMENT_TABLE,
				ChatColor.GREEN + "Enchanting");

		ItemStack anvil = Item.renameItem(Material.ANVIL,
				ChatColor.GRAY + "Repairing");

		ItemStack enderChest = Item.renameItem(Material.ENDER_CHEST,
				ChatColor.DARK_PURPLE + "Ender Chest");

		inv.setItem(0, group);
		inv.setItem(1, cookie);
		inv.setItem(2, bed);
		inv.setItem(3, lava);
		inv.setItem(4, compass);
		inv.setItem(5, dragonEgg);

		// inv.setItem(7, beacon);
		// inv.setItem(8, expBottle);
		inv.setItem(9, faction);
		inv.setItem(10, diamond);
		inv.setItem(11, enderChest);
		inv.setItem(12, workBench);
		inv.setItem(13, skull);
		inv.setItem(14, enchantingTable);
		inv.setItem(15, anvil);

		inv.setItem(16, emerald);
		inv.setItem(17, goldIngot);

		player.openInventory(inv);
	}

}
