package me.Penguin.SuperChatReactions;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class MainCmd implements TabExecutor {
	private Main plugin;
	
	public MainCmd(Main plugin) {
		this.plugin = plugin;
		//plugin.getCommand("x").setExecutor(this);
	}

	@Override
	public boolean onCommand( CommandSender s,  Command cmd,  String label, String[] args) {
		
		
		return false;
	}

	@Override
	public List<String> onTabComplete( CommandSender s,  Command cmd, String label,  String[] args) {
		
		
		return null;
	}
}