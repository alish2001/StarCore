package com.starnetmc.Core.Modules.ServerSorter.ServerInfo;

import com.starnetmc.Core.Modules.StarModuleManager;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ServerInfo {
	
	protected String serverName;
	protected ServerType serverType;
	protected String serverMOTD;
	protected int serverPlayers;
	protected int maxServerPlayers;
	
	public ServerInfo(String serverName, String serverMOTD, int serverPlayers, int maxServerPlayers){
		this.serverName = serverName;
		this.serverMOTD = serverMOTD;
		this.serverType = ServerType.getServerTypeFromString(serverMOTD.split("\\|")[0]);
		this.serverPlayers = serverPlayers;
		this.maxServerPlayers = maxServerPlayers;
	}
	
	public void connect(Player p){
		ByteArrayDataOutput ipFetcher = ByteStreams.newDataOutput();
		ipFetcher.writeUTF("Connect");
		ipFetcher.writeUTF(serverName);
		p.sendPluginMessage(StarModuleManager.get().getCore(), "BungeeCord", ipFetcher.toByteArray());
	}
	
	public void setName(String name){
		this.serverName = name;
	}
	
	public void setType(ServerType type){
		this.serverType = type;
	}
	
	public void setMOTD(String motd){
		this.serverMOTD = motd;
	}
	
	public void setPlayerCount(int players){
		this.serverPlayers = players;
	}
	
	public void setMaxPlayerCount(int maxPlayers){
		this.maxServerPlayers = maxPlayers;
	}
	
	public String getName(){
		return serverName;
	}
	
	public ServerType getType(){
		return serverType;
	}
	
	public String getMOTD(){
		return serverMOTD;
	}
	
	public int getPlayerCount(){
		return serverPlayers;
	}
	
	public int getMaxPlayerCount(){
		return maxServerPlayers;
	}
	
}