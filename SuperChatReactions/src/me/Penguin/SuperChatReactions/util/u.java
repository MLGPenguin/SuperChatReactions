package me.Penguin.SuperChatReactions.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class u {
	
	
	public static String translateHexColorCodes(/*String startTag, String endTag,*/ String message) {
		final char COLOR_CHAR = '\u00A7';
		final Pattern hexPattern = Pattern.compile(/*startTag*/ "#" + "([A-Fa-f0-9]{6})" /*+ endTag*/);
		Matcher matcher = hexPattern.matcher(message);
		StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
		while (matcher.find()) {
			String group = matcher.group(1);
			matcher.appendReplacement(buffer, COLOR_CHAR + "x"
					+ COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
					+ COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
					+ COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
					);
		}
		return matcher.appendTail(buffer).toString();
	}

	public static String cc(String s) { return ChatColor.translateAlternateColorCodes('&', s);	}	
	public static String hc(String s) { if (s == null) return null; else return translateHexColorCodes(cc(s)); }

	public static String dc(int value) {
		String pattern = "###,###,###";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(value); 
	}
	
	public static String twoDecimals(double value) {
		String pattern = "###.##";
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(value);
	}

	public static List<String> TabCompleter(List<String> commands, String Input){
		List<String> wordsThatStartWithArg = new ArrayList<>();
		for (String x : commands) {
			if (x.toLowerCase().startsWith(Input.toLowerCase())) {
				wordsThatStartWithArg.add(x);
			}
		}
		return wordsThatStartWithArg;	
	}

	public static String capitaliseFirstLetters(String s) {
		String[] words = s.split(" ");
		String returnable = "";
		for (String x : words) {
			String firstletter = x.substring(0, 1);
			String notfirstletter = x.substring(1, x.length()).toLowerCase();
			returnable = (returnable.length() == 0 ? returnable: returnable+ " ") + firstletter.toUpperCase() + notfirstletter;
		}
		return returnable;
	}
	
	public static ItemStack itemInMainHand(Player p) { return p.getInventory().getItemInMainHand(); }
	public static void send(Player p, String s) { p.sendMessage(u.cc(s)); }
	public static boolean isMaterial(String s) { return Material.matchMaterial(s) != null; }	
	public static Material getMaterial(String s) { return Material.matchMaterial(s); }	
	public static boolean hasInventorySpace(Player p) { return (p.getInventory().firstEmpty() != -1); }	
	public static boolean isPlayer(CommandSender s) { return (s instanceof Player); }	
	public static boolean isPlayer(String name) { return Bukkit.getPlayer(name) != null; }	
	public static Player getPlayer(String s) { return Bukkit.getPlayer(s); }	
	public static String getNicerName(Material m) { return capitaliseFirstLetters(m.toString().toLowerCase().replaceAll("_", " ")); }	
	public static String getNicerName(ItemStack item) { return getNicerName(item.getType()); }	
	public static boolean isItem(ItemStack item) { return (item != null && item.getType() != Material.AIR); }
	public static int getInt(String s) { return Integer.parseInt(s); }
	public static String getNicerMaterialNameSingular(String materialName) { return removeFinalS(getNicerMaterialName(materialName)); }
	public static void bc(String s) { Bukkit.getPlayer("MLGPenguin").sendMessage(hc(s)); }

	public static List<String> getAllPlayersNames(){
		List<String> names = new ArrayList<>();
		for (Player p : Bukkit.getOnlinePlayers()) names.add(p.getName());
		return names;
	}

	public static List<Player> getOnlinePlayers(){
		List<Player> players = new ArrayList<>();
		players.addAll(Bukkit.getOnlinePlayers());
		return players;
	}
	
	public static String getNicerMaterialName(String materialName) {
		if (isMaterial(materialName)) {
			return getNicerName(Material.matchMaterial(materialName));
		} else return null;
	}

	public static String removeFinalS(String s) {
		if (s.toLowerCase().endsWith("s")) {
			s = s.substring(0, s.length()-1);
		}
		return s;
	}
	
	public static int getRandomNumberBetween(int lower, int higher) {
		Random rand = new Random();
		return lower + rand.nextInt(higher - lower);
	}

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static String pluralise(int amount, String singularName) { return amount == 1 ? amount + " " + singularName : amount + " " + singularName + "s"; }
	public static String pluralise(long amount, String singularName) { return amount == 1 ? amount + " " + singularName : amount + " " + singularName + "s"; }
	public static Timestamp getCurrentTimestamp() { return Timestamp.from(Instant.now()); }

	

public static int getLargest(List<Integer> ints) {
	TreeSet<Integer> set = new TreeSet<>();
	set.addAll(ints);
	return set.last();
}

public static Long getLargestLong(List<Long> longs) {
	TreeSet<Long> set = new TreeSet<>();
	set.addAll(longs);
	return set.last();
}


	


	
	}



