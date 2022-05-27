package me.Penguin.SuperChatReactions;


import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.Penguin.SuperChatReactions.files.ReactionFile;
import me.Penguin.SuperChatReactions.files.Usermap;
import me.Penguin.SuperChatReactions.util.config;
import me.Penguin.SuperChatReactions.util.m;

public class Main extends JavaPlugin {
	
	public static boolean guessing;
	public static boolean unscramble;
	public static String word;
	public static Instant start;
	
	public void onEnable() {				
		Logger log = this.getLogger();
		ReactionFile reactionfile = new ReactionFile();
		Usermap usermap = new Usermap();
		Instant Ia = Instant.now();
		
		log.info("Preparing...");	
		
		m.setup();
		config.setup();
		reactionfile.setupWithHeadings("UUID", "Name", "Word", "Time");
		usermap.setupWithHeadings("UUID", "ID", "Name");		
		log.info("Loaded Files");
		
		new MainListener(this);
		new MainCmd(this);		
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
			
		
		new BukkitRunnable() {			
			@Override
			public void run() {
				simulateStartReaction();
				new BukkitRunnable() {	
					@Override
					public void run() {
						endReaction();
					}
				}.runTaskLater(Main.getPlugin(Main.class), config.guessTime);
			}
		}.runTaskTimer(this, config.reactionInterval, config.reactionInterval);		
		log.info("Loaded SuperChatReactions [" + between(Ia, Instant.now()) + "ms]");
	}
	
	private void endReaction() {
		boolean unanswered = guessing;
		guessing = false;
		if (unanswered) Bukkit.broadcastMessage(m.noGuess(Main.word, unscramble));	
	}
	
	private void simulateStartReaction() {
		String word = chooseWord();
		Main.word = word;
		unscramble = shouldUnscramble();		
		if (unscramble) word = scramble(word);
		Bukkit.broadcastMessage(unscramble ? m.unscrambleGlobal(word) : m.typeGlobal(word));
		guessing = true;
		start = Instant.now();
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
	
	private long between(Instant a, Instant b) {
		return Duration.between(a, b).toMillis();
	}

}
