package me.Penguin.SuperChatReactions.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.Penguin.SuperChatReactions.reaction;

public class ReactionFile extends CSVHandler {

	public ReactionFile() {
		super(new File("plugins/SuperChatReactions/Reactions.yml"));		
	}

	public void addReaction(reaction reaction) {
		addline(reaction.getUUID().toString(), reaction.getName(), reaction.getWord(), String.valueOf(reaction.getTime()));
	}
	
	public List<reaction> getAllReactions() {
		List<reaction> reactions = new ArrayList<>();
		getEntries().forEach(entry -> reactions.add(new reaction(entry[2], UUID.fromString(entry[0]), Long.valueOf(entry[3]), entry[1])));
		return reactions;
	}
	
	

}
