package me.Penguin.SuperChatReactions;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class MainListener implements Listener{
	
	private Main plugin;
	
	public MainListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

}
