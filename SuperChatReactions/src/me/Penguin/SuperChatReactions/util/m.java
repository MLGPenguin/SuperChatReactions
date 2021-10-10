package me.Penguin.SuperChatReactions.util;

public class m {

	// messages
	private static String prefix = prefix();
	
	public static String noPermission, invalidPlayerSelf, unknownCommand;
	
	public static void setup() {
		noPermission = u.hc(prefix + "&cYou do not have permission to do this"); 
		invalidPlayerSelf = u.hc(prefix + "&cYou need to be a player to use this command");
		unknownCommand = u.hc(prefix + "&cUnknown Command");
	}

	public static String s() { return u.hc(prefix + ""); }
	public static String invalidPlayerOther(String Name) { return u.hc(prefix + "&c" + Name + "&C is not a player"); }
	public static String prefix() { return u.hc("&8[&7&lSB&6&lZEN &8] | "); }
	
}
