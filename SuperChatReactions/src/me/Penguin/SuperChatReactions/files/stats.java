package me.Penguin.SuperChatReactions.files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import me.Penguin.SuperChatReactions.Main;

public class stats {
	
	
	private static HashMap<UUID, Integer> guessed = new HashMap<>();	
	private static File file = new File("plugins/SuperChatReaction/Stats.yml");
	private static FileConfiguration cfg;
	
	public static void setup() {
		if (!file.exists()) {
			try { file.createNewFile(); } 
			catch (IOException e) { e.printStackTrace(); } }
		reload();
	}
	private static void loadFromFile() { 
		for (String x : cfg.getConfigurationSection("GuessedReactions").getKeys(false)) guessed.put(UUID.fromString(x), cfg.getInt("GuessedReactions." + x)); 
	}
	public static void reload() { 
		cfg = YamlConfiguration.loadConfiguration(file); 
		loadFromFile();
	}
	
	
	public static void addGuessedReaction(UUID uuid) { setGuesses(uuid, getGuesses(uuid)+1); }
	public static boolean hasGuesses(UUID uuid) { return guessed.containsKey(uuid); }
	public static int getGuesses(UUID uuid) { return (hasGuesses(uuid) ? guessed.get(uuid) : 0); }
	private static void setGuesses(UUID uuid, int amount) { guessed.put(uuid, amount); saveuuid(uuid); }
	
	
	private static void saveuuid(UUID uuid) { 
		cfg.set("GuessedReactions." + uuid.toString(), guessed.get(uuid));
		saveAsync();
	}
	
	private static void savewhole() { 
		for (UUID x : guessed.keySet()) cfg.set("GuessedReactions." + x.toString(), guessed.get(x));		
		save();
	}
	
	private static void save() {
		try { cfg.save(file); } 
		catch (IOException e) { e.printStackTrace(); }
	}
	
	private static void saveAsync() {
		new BukkitRunnable() {			
			@Override
			public void run() {
				save();
			}
		}.runTaskAsynchronously(Main.getPlugin(Main.class));
	}
	
	
	
	
	
}
