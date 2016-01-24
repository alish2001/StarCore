package com.starnetmc.Core.Modules.ServerSorter;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.starnetmc.Core.Modules.Heartbeat.Beat;
import com.starnetmc.Core.Modules.Heartbeat.HeartbeatEvent;
import com.starnetmc.Core.Modules.ServerSorter.GUI.GameServerGUI;
import com.starnetmc.Core.Modules.ServerSorter.GUI.ServerGUI;
import com.starnetmc.Core.Modules.ServerSorter.Getters.UServer;
import com.starnetmc.Core.Modules.ServerSorter.ServerInfo.*;
import com.starnetmc.Core.Modules.StarModule;
import com.starnetmc.Core.Modules.StarModuleManager;
import com.starnetmc.Core.Utils.F;
import com.starnetmc.Core.Utils.Logger;
import com.starnetmc.Core.Utils.Tickifier;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ali on 1/10/2016 at 6:15 PM.
 */

public class ServerSorter extends StarModule {

    public ServerSorter() {
        super("ServerSorter", 1.0);
    }

    @Override
    public void onEnable() {

        servers = new HashMap<ServerInfo, ServerType>();
        serverIPs = new HashMap<String, InetSocketAddress>();
        allServerNames = new ArrayList<String>();
        serverGUIs = new ArrayList<GameServerGUI>();

        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(StarModuleManager.get().getCore(), "BungeeCord", new ServerInfoReciever());
        initServerGUIs();

        if (Bukkit.getServer().getOnlinePlayers().size() != 0){
            refresh();
            isReady = true;
        } else {
            isReady = false;
            log("Refraining from Sending fetch requests since no players are online.");
            return;
        }

    }

    @Override
    public void onDisable() {
        unInitServerGUIs();
    }

    private boolean isReady = false;

    private HashMap<ServerInfo, ServerType> servers;
    private HashMap<String, InetSocketAddress> serverIPs;
    private List<String> allServerNames;
    private ArrayList<GameServerGUI> serverGUIs;

    public List<String> getAllServerNames(){
        return allServerNames;
    }

    public HashMap<String, InetSocketAddress> getServerIPs(){
        return serverIPs;
    }

    public void initServerGUIs(){
        for (ServerGUI gui : serverGUIs){
            gui.unRegister();
        }

        serverGUIs.clear();

        for (GameType type : GameType.getAllGameTypes()){
            GameServerGUI gui = new GameServerGUI(type);
            gui.register();
            serverGUIs.add(gui);
        }
    }

    public void unInitServerGUIs(){
        for (ServerGUI gui : serverGUIs){
            gui.unRegister();
        }
    }

    public void refresh(){
        fetchServers();

        new BukkitRunnable() {

            @Override
            public void run() {
                fetchServerIPs();
            }
        }.runTaskLater(StarModuleManager.get().getCore(), Tickifier.tickify(1, Tickifier.Time.SECONDS));
    }

    public void fetchServerIPs(){
        Logger.log("<Server Sorter> Fetching ServerIP Map...");
        serverIPs.clear();
        for (String name : allServerNames){
            ByteArrayDataOutput ipFetcher = ByteStreams.newDataOutput();
            ipFetcher.writeUTF("ServerIP");
            ipFetcher.writeUTF(name);
            Iterables.getFirst(Bukkit.getOnlinePlayers(), null).sendPluginMessage(StarModuleManager.get().getCore(), "BungeeCord", ipFetcher.toByteArray());
        }
    }

    public void fetchServers(){
        Logger.log("<Server Sorter> Fetching Server list...");
        allServerNames.clear();
        ByteArrayDataOutput serverFetcher = ByteStreams.newDataOutput();
        serverFetcher.writeUTF("GetServers");
        Iterables.getFirst(Bukkit.getOnlinePlayers(), null).
                sendPluginMessage(StarModuleManager.get().getCore(), "BungeeCord", serverFetcher.toByteArray());
    }

    public void sortServers(){
        servers.clear();

        for (Map.Entry<String, InetSocketAddress> server : serverIPs.entrySet()){
            String ip = Bukkit.getServer().getIp();
            int port = Bukkit.getServer().getPort();
            if (server.getValue().getHostString().equalsIgnoreCase(ip) && server.getValue().getPort() == port) continue;
            ServerInfo data = UServer.getServerInfo(server.getKey(), server.getValue());
            servers.put(data, data.getType());
        }
    }

    public ArrayList<GameServerInfo> getGameServers(GameType type){
        ArrayList<GameServerInfo> gameServers = new ArrayList<GameServerInfo>();

        for (Map.Entry<ServerInfo, ServerType> server : servers.entrySet()){
            if (server.getValue() != ServerType.GAME) continue;
            GameServerInfo data = UServer.convertServerInfoToGame(server.getKey());

            if (data.getGameType() != type) continue;
            gameServers.add(data);
        }

        return gameServers;
    }

    public GameServerGUI getGameGUI(GameType type){
        GameServerGUI rGUI = serverGUIs.get(0);

        for (ServerGUI gui : serverGUIs){
            if (!(gui instanceof GameServerGUI)) continue;
            GameType gType = GameType.getGameTypeFromName(ChatColor.stripColor(gui.getInventory().getName()));
            if (gType != type) continue;

            rGUI = (GameServerGUI) gui;
        }

        return rGUI;
    }

    @EventHandler
    public void serverData(ServerListPingEvent e){
        e.setMotd(F.AQUA + "LOBBY|" + Bukkit.getServer().getName().split("-")[1].trim());
    }

    @EventHandler
    public void serverListUpdater(HeartbeatEvent e){
        if (e.getBeat() != Beat.SECOND) return;
        sortServers();

        for (ServerGUI gui : serverGUIs){
            gui.refresh();
        }
    }

    @EventHandler
    public void onReady(PlayerJoinEvent e){
        if (isReady) return;

        log("Player logged in, fetching data in 5 seconds...");
        isReady = true;
        refresh();
    }

}
