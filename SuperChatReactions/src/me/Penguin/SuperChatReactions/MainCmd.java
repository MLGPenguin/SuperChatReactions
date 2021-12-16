package me.Penguin.SuperChatReactions;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import me.Penguin.SuperChatReactions.files.stats;
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
						stats.reload();
						s.sendMessage(m.reloadedconfig);
						return true;
					} else if (args[0].equalsIgnoreCase("top")) {
						Instant start = Instant.now();
						HashMap<Integer, reaction> reactions = stats.getFastest10Reactions();
						for (int i = 0 ; i < reactions.size() ; i++) {
							reaction r = reactions.get(i);
							s.sendMessage("&7" + (i+1) + ": " + r.getName() + " - " + r.getWord() + " [" + u.twoDecimals((double) r.getTime()/1000) + "s]");
						}
						Instant end = Instant.now();
						s.sendMessage(u.cc("&7") + Duration.between(start, end).toMillis() +" elapsed");
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