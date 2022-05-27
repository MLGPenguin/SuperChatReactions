package me.Penguin.SuperChatReactions.files;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Usermap extends CSVHandler {
	
	public Usermap() {
		super(new File("plugins/SuperChatReactions/usermap.yml"));
	}
	
	public void updateUser(Player p) {
		UUID uuid = p.getUniqueId();
		String[] data = null;
		List<String[]> entries = getEntries();
		for (String[] s : entries) {
			if (s[0].equals(uuid.toString())) {
				data = s;
			}
		}
		
	}

}
