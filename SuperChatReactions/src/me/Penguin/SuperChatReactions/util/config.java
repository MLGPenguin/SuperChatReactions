package me.Penguin.SuperChatReactions.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import me.Penguin.SuperChatReactions.Main;

public class config {
	
	private static Main plugin; 
	private static FileConfiguration cfg;
		
	public static int reactionInterval;
	public static List<String> words;
	public static int unscrambleChance;
	public static int guessTime;
	
	public static void setup() {
		plugin = Main.getPlugin(Main.class);
		cfg = plugin.getConfig();
				
		/**
		 * in ticks.
		 */
		reactionInterval = getInt("ReactionInterval") * 20;
		words = getStringList("WordList");
		unscrambleChance = getInt("UnscrambleChance");
		/**
		 * in ticks
		 */
		guessTime = (getInt("GuessTime") < reactionInterval ? getInt("GuessTime") : reactionInterval - 1) * 20;
	}
	
	private static int getInt(String path) {
		return cfg.contains(path)?cfg.getInt(path):0;
	}
	
	private static List<String> getStringList(String path){
		return cfg.contains(path) ? cfg.getStringList(path) : new ArrayList<>();
	}
	
	
	public static void reload() {
		plugin.reloadConfig();
		cfg = plugin.getConfig();
		setup();
	}
		
	
	

}
