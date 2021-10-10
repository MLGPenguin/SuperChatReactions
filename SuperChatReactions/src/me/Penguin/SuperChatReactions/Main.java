package me.Penguin.SuperChatReactions;


import org.bukkit.plugin.java.JavaPlugin;

import me.Penguin.SuperChatReactions.util.m;

public class Main extends JavaPlugin {
	
	public void onEnable() {
				
		m.setup();
		
		new MainListener(this);
		new MainCmd(this);		
		
		//getConfig().options().copyDefaults();
		//saveDefaultConfig();
		
	}
	

}
