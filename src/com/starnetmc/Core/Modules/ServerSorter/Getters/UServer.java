package com.starnetmc.Core.Modules.ServerSorter.Getters;

import com.starnetmc.Core.Modules.ServerSorter.ServerInfo.GameServerInfo;
import com.starnetmc.Core.Modules.ServerSorter.ServerInfo.ServerInfo;

import java.io.IOException;
import java.net.InetSocketAddress;

public class UServer {
	
	public static ServerInfo getServerInfo(String name, String ip, int port){
		InetSocketAddress address = new InetSocketAddress(ip, port);
		return getServerInfo(name, address);
	}
	
	public static ServerInfo getServerInfo(String name, InetSocketAddress address){
		ServerFetcher fetcher = new ServerFetcher(address);
		
		ServerResponse response = null;
		
		try {
			response = fetcher.fetchData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String motd = response.getDescription();
		int onlinePlayers = response.getPlayers().getOnline();
		int maxPlayers = response.getPlayers().getMax();
		
		ServerInfo info = new ServerInfo(name, motd, onlinePlayers, maxPlayers);
		return info;
	}
	
  
	public static GameServerInfo convertServerInfoToGame(ServerInfo inf){
		return new GameServerInfo(inf.getName(), inf.getMOTD(), inf.getPlayerCount(), inf.getMaxPlayerCount());
	}
}