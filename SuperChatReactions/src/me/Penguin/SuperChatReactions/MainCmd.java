package me.Penguin.SuperChatReactions;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import me.Penguin.SuperChatReactions.util.config;
import me.Penguin.SuperChatReactions.util.m;
import me.Penguin.SuperChatReactions.util.u;

public class MainCmd implements TabExecutor {
	private Main plugin;
	
	public MainCmd(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("chatreactions").setExecutor(this);
	}

	@Override
	public boolean onCommand( CommandSender s,  Command cmd,  String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chatreactions")) {
			if (s.hasPermission("superchatreactions.admin")) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						config.reload();
						s.sendMessage(m.reloadedconfig);
						return true;
					} else s.sendMessage(m.unknownCommand);
				} else s.sendMessage(m.unknownCommand);
			} else s.sendMessage(m.noPermission);
		} 
		
		return true;
	}

	@Override
	public List<String> onTabComplete( CommandSender s,  Command cmd, String label,  String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("chatreactions")) {
			if (s.hasPermission("superchatreactions.admin")) {
				if (args.length == 1) {
					return u.TabCompleter(Arrays.asList("reload"), args[0]);
				}
			}
		}
		
		return null;
	}
}