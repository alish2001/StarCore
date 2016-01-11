package com.starnetmc.Core.Modules.ServerSorter.ServerInfo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public enum GameType {

	SPLEEF("Spleef"),
	DRAGONSWORD("Dragon's Blade"),
	SURVIVALGAMES("Survival Games");
	
	private String name;
	
	GameType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public static GameType getGameTypeFromVarName(String var_name){
		var_name = ChatColor.stripColor(var_name);
		GameType returnType = GameType.DRAGONSWORD;
		
		for (GameType type : getAllGameTypes()){
			if (var_name.equalsIgnoreCase(type.toString().toLowerCase())){
				returnType = type;
			}
		}
		
		return returnType;
	}
	
	public static GameType getGameTypeFromName(String name){
		GameType returnType = GameType.DRAGONSWORD;
		name = ChatColor.stripColor(name).trim();
		
		for (GameType type : getAllGameTypes()){
			if (name.equalsIgnoreCase(type.getName())){
				returnType = type;
			}
		}
		
		return returnType;
	}
	
	public static List<GameType> getAllGameTypes(){
		List<GameType> allGames = new ArrayList<GameType>();
		allGames.add(SPLEEF);
		allGames.add(DRAGONSWORD);
		allGames.add(SURVIVALGAMES);
		return allGames;
	}
}