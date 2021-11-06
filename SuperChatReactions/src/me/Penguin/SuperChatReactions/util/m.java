package me.Penguin.SuperChatReactions.util;

public class m {

	// messages
	private static String prefix = prefix();
	
	public static String noPermission, invalidPlayerSelf, unknownCommand, reloadedconfig;
	
	public static void setup() {
		reloadedconfig = msg("&7Successfully reloaded config!");
		noPermission = u.hc(prefix + "&cYou do not have permission to do this"); 
		invalidPlayerSelf = u.hc(prefix + "&cYou need to be a player to use this command");
		unknownCommand = u.hc(prefix + "&cUnknown Command");
	}

	public static String s() { return u.hc(prefix + ""); }
	public static String typeGlobal(String word) { return u.hc(prefix + "&7First person to type &6" + word + "&7 will win a reward!"); }
	public static String unscrambleGlobal(String word) { return u.hc(prefix + "&7First person to unscramble &6" + word + "&7 will win a reward!"); }
	public static String invalidPlayerOther(String Name) { return u.hc(prefix + "&c" + Name + "&C is not a player"); }
	public static String prefix() { return u.hc("&8[&7&lSB&6&lZEN&8] "); }
	public static String UnscrambledWord(String playername, String word, long milliseconds) { 
		return u.hc(prefix + "&6" +playername +"&7 unscrambled &6" + word + "&7 in &6" + u.twoDecimals((double)milliseconds/1000) + " seconds!"); 
	}
	public static String typedWord(String playername, String word, long milliseconds) { 
		return u.hc(prefix + "&6" +playername +"&7 typed &6" + word + "&7 in &6" + u.twoDecimals((double) milliseconds/1000) + " seconds!"); 
	}
	public static String noGuess(String word, boolean unscramble) { return msg("&7Time's up! No one typed the word correctly in time!" + (unscramble?" The word was &6" + word + "&7!":"")); }
	
	
	private static String msg(String msg) {
		return u.hc(prefix + msg);
	}
	
}
