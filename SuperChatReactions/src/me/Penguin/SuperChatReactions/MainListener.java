package me.Penguin.SuperChatReactions;

import java.time.Duration;
import java.time.Instant;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

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
				e.setCancelled(true);
				new BukkitRunnable() {					
					@Override
					public void run() { 
						
						win(e.getPlayer());
					}
				}.runTask(plugin);				
			}
		}
	}
	
	
	private void win(Player p) {
		Location spot = p.getLocation();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scrates givekey Vote " + p.getName() + " 1");
		p.spawnParticle(Particle.VILLAGER_HAPPY, spot, 10, 1, 1, 1);
		new BukkitRunnable() {		
			int it = 0;
			@Override
			public void run() {
				if (it == 3) {
					cancel();
					return;
				}
				p.playSound(spot, Sound.BLOCK_NOTE_BLOCK_BELL, 3, 10);
				it++;
			}
		}.runTaskTimer(plugin, 0, 2);
		
		
	}
	
	  
	
	
}
