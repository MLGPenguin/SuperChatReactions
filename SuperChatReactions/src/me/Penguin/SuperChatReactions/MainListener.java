package me.Penguin.SuperChatReactions;

import java.time.Duration;
import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.Penguin.SuperChatReactions.util.m;

public class MainListener implements Listener{
	
	private Main plugin;
	
	public MainListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (Main.guessing) {
			if (e.getMessage().equalsIgnoreCase(Main.word)) {
				long timetaken = Duration.between(Main.start, Instant.now()).toMillis();
				Main.guessing = false;
				String name = e.getPlayer().getName();
				Bukkit.broadcastMessage(Main.unscramble ? m.UnscrambledWord(name, Main.word, timetaken) : m.typedWord(name, Main.word, timetaken));
				// rewards
			}
		}
	}
	
	
}
