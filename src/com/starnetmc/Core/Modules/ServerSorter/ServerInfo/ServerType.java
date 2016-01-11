package com.starnetmc.Core.Modules.ServerSorter.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public enum ServerType {

	GAME,
	LOBBY,
	SPECIAL;
	
	public static ServerType getServerTypeFromString(String type){
		ServerType fType = GAME;
		
		for (ServerType _type : getAllServerTypes()){
			if (type.equalsIgnoreCase(_type.toString())){
				fType = _type;
			}
		}
		return fType;
	}
	
	public static List<ServerType> getAllServerTypes(){
		List<ServerType> allServers = new ArrayList<ServerType>();
		allServers.add(GAME);
		allServers.add(LOBBY);
		allServers.add(SPECIAL);
		return allServers;
	}
}