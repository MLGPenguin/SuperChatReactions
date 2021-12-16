package me.Penguin.SuperChatReactions;

import java.util.UUID;

public class reaction {
	
	private String word;
	private UUID uuid;
	private long ms;
	
	public reaction(String word, UUID uuid, long ms) {
		this.word = word;
		this.uuid = uuid;
		this.ms = ms;
	}
	
	public String getWord() { return word; }
	public UUID getUUID() { return uuid; }
	public long getTime() { return ms; }

}
