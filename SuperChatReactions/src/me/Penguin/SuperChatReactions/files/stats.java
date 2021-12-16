package me.Penguin.SuperChatReactions.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import me.Penguin.SuperChatReactions.Main;
import me.Penguin.SuperChatReactions.reaction;

public class stats {
	
	
	private static HashMap<UUID, Integer> guessed = new HashMap<>();	
	private static HashMap<UUID, reaction> bestReactions = new HashMap<>();
	private static File file = new File("plugins/SuperChatReactions/Stats.yml");
	private static FileConfiguration cfg;
	
	public static void setup() {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		reload();
	}
	private static void loadFromFile() {
		for (String x : cfg.getConfigurationSection("").getKeys(false)) {
			UUID uuid = UUID.fromString(x);
			guessed.put(uuid, cfg.getInt(x + ".guessed"));
			bestReactions.put(uuid, new reaction(cfg.getString(x + ".fastestword"), uuid, cfg.getLong(x + ".fastesttime")));
		}
	}
	public static void reload() { 
		cfg = YamlConfiguration.loadConfiguration(file); 
		loadFromFile();
	}
	
	
	public static void addGuessedReaction(UUID uuid, reaction reaction) { 
		setGuesses(uuid, getGuesses(uuid)+1); 
		updateFastestReaction(reaction);
	}
	public static boolean hasGuesses(UUID uuid) { return guessed.containsKey(uuid); }
	public static int getGuesses(UUID uuid) { return (hasGuesses(uuid) ? guessed.get(uuid) : 0); }
	private static void setGuesses(UUID uuid, int amount) { guessed.put(uuid, amount); saveToFileUUIDGuessed(uuid); }
	
	public static void updateFastestReaction(reaction reaction) {
		reaction old = getFastestReaction(reaction.getUUID());
		if (old == null || reaction.getTime() < old.getTime()) {
			bestReactions.put(reaction.getUUID(), reaction);
			saveToFileUUIDSpeed(reaction.getUUID());			
		}
	}
		
	public static reaction getFastestReaction(UUID uuid) {
		if (hasFastestReaction(uuid))
		return bestReactions.get(uuid);
		else return null;
	}
	
	public static boolean hasFastestReaction(UUID uuid) {
		return bestReactions.keySet().contains(uuid);
	}
	
	
	private static void saveToFileUUIDGuessed(UUID uuid) { 
		String x = uuid.toString();
		cfg.set(x + ".guessed", guessed.get(uuid));
		saveAsync();
	}
	private static void saveToFileUUIDSpeed(UUID uuid) { 
		String x = uuid.toString();
		cfg.set(x + ".fastestword", bestReactions.get(uuid).getWord());
		cfg.set(x + ".fastesttime", bestReactions.get(uuid).getTime());
		saveAsync();
	}
	
	private static void saveall() { 
		for (UUID x : guessed.keySet()) cfg.set(x.toString() + ".guessed", guessed.get(x));		
		for (UUID x : bestReactions.keySet()) {
			cfg.set(x.toString() + ".fastestword", bestReactions.get(x).getWord());
			cfg.set(x.toString() + ".fastesttime", bestReactions.get(x).getTime());
		}
		saveAsync();
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
	
	public static HashMap<Integer, reaction> getFastest10Reactions() {
		Collection<reaction> reactions = bestReactions.values();
		HashMap<Integer, reaction> fastest = new HashMap<>();
		while (fastest.size() < 10 && reactions.size() > 0) {
			reaction r = findFastest(reactions);
			fastest.put(fastest.size(), r);
			reactions.remove(r);
		}
		return fastest;		
	}
	
	public static reaction findFastest(Collection<reaction> reactions) {
		reaction fastest = null;
		for (reaction r : reactions ) {
			if (fastest == null) fastest = r;
			else if (r.getTime() < fastest.getTime()) fastest = r; 
		}
		return fastest;
	}
	
	
	
}
