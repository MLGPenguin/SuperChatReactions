package me.Penguin.SuperChatReactions;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.Penguin.SuperChatReactions.util.config;
import me.Penguin.SuperChatReactions.util.m;

public class Main extends JavaPlugin {
	
	public void onEnable() {
				
		m.setup();
		
		new MainListener(this);
		new MainCmd(this);		
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		config.setup();	
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				String word = chooseWord();
				if (shouldUnscramble()) word = scramble(word);
			}
		}.runTaskTimer(this, config.reactionInterval, config.reactionInterval);
		
		
	}
	
	private boolean shouldUnscramble() {
		Random r = new Random();
		int chance = config.unscrambleChance;
		return (r.nextInt(101) <= chance);
	}
	
	private String scramble(String word) {
		List<String> letters = new ArrayList<>();
		for (String x : word.split("")) letters.add(x);
		String scrambled = "";
		while (letters.size() > 0) {
			int val = new Random().nextInt(letters.size());
			scrambled = scrambled + letters.get(val);
			letters.remove(val);
		}
		return scrambled;
	}
	
	private String chooseWord() {
		return config.words.get(new Random().nextInt(config.words.size()));
	}
	
	

}
