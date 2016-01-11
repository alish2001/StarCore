package com.starnetmc.Core.Modules.ServerSorter;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.starnetmc.Core.Modules.StarModuleManager;
import com.starnetmc.Core.Utils.Logger;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.net.InetSocketAddress;

public class ServerInfoReciever implements PluginMessageListener {

	@Override
	public void onPluginMessageReceived(String channel, Player p, byte[] input) {
		if (!channel.equalsIgnoreCase("BungeeCord")) return;
	    ByteArrayDataInput data = ByteStreams.newDataInput(input);
		String subChannel = data.readUTF();
		
		if (subChannel.equalsIgnoreCase("GetServers")){
	    Logger.log("<Server Sorter> Server list recived. Organizing...");
		String[] serverArray = data.readUTF().split(", ");
		
		 for (String server : serverArray){
			 ((ServerSorter)StarModuleManager.get().getModuleRegistry().getModule("ServerSorter")).getAllServerNames().add(server.trim());
		 }
		 
		 Logger.log("<Server Sorter> Server list fetching successful!");
		 return;
		}
		
		if (subChannel.equalsIgnoreCase("ServerIP")){
			String serverName = data.readUTF();
			String ip = data.readUTF();
			short port = data.readShort();
			InetSocketAddress serverAddress = new InetSocketAddress(ip, port);
		    Logger.log("<Server Sorter> Got ServerIP for server: " + serverName + " ip: " + ip + ":" + port);
			((ServerSorter)StarModuleManager.get().getModuleRegistry().getModule("ServerSorter")).getServerIPs().put(serverName, serverAddress);
			return;
		}
	}
	
}