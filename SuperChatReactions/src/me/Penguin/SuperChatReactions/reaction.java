package me.Penguin.SuperChatReactions;

import java.util.UUID;

public class reaction {
	
	private String word;
	private UUID uuid;
	private long ms;
	private String name;
	
	public reaction(String word, UUID uuid, long ms, String name) {
		this.word = word;
		this.uuid = uuid;
		this.ms = ms;
		this.name = name;
	}
	
	public String getWord() { return word; }
	public UUID getUUID() { return uuid; }
	public long getTime() { return ms; }
	public String getName() { return name; }

}
