package com.starnetmc.Core.Modules.ServerSorter.GUI;

import com.starnetmc.Core.Modules.ServerSorter.GUI.ServerGUI;
import com.starnetmc.Core.Modules.ServerSorter.ServerInfo.GameServerInfo;
import com.starnetmc.Core.Modules.ServerSorter.ServerInfo.GameState;
import com.starnetmc.Core.Modules.ServerSorter.ServerInfo.GameType;
import com.starnetmc.Core.Modules.ServerSorter.ServerSorter;
import com.starnetmc.Core.Modules.StarModuleManager;
import com.starnetmc.Core.Utils.F;
import com.starnetmc.Core.Utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class GameServerGUI extends ServerGUI {
	
	private GameType type;
	
	public GameServerGUI(GameType type){
		this.type = type;
		gui = Bukkit.getServer().createInventory(null, 54, type.getName());
	}

	@EventHandler
	public void UI(InventoryClickEvent e){
		e.setCancelled(true);
		if (e.getClickedInventory() == null) return;
		if (e.getClickedInventory().getName() == null) return;
		if (!e.getClickedInventory().getName().equalsIgnoreCase(gui.getName())) return;
		if (e.getCurrentItem() == null) return;
		if (e.getCurrentItem().getType() == null) return;
		if (e.getCurrentItem().getType() == Material.AIR) return;
		if (e.getCurrentItem().getType() == Material.SIGN) return;
		if (!e.getCurrentItem().hasItemMeta()) return;
		if (!ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().split("\\s+")[0]).trim().equalsIgnoreCase("Server")) return;
		
		String serverName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().split("\\s+")[1]).trim();

		for (GameServerInfo server : ((ServerSorter)StarModuleManager.get().getModuleRegistry().getModule("ServerSorter")).getGameServers(type)){
			
			if (server.getName().equalsIgnoreCase(serverName)){
				server.connect((Player) e.getWhoClicked());
				return;
			}
		}
		
	}
	
	
	@Override
	public void refresh(){
		int descPosition = 13;
		//int specPosition = 50;
		
		int centerPosition = 31;
		int position = centerPosition;
		
		gui.clear();
		
		ItemStack descriptor = ItemFactory.createItem(Material.SIGN, F.boldYellow + type.getName());
	    gui.setItem(descPosition, descriptor);
	    
		
		for (GameServerInfo server : ((ServerSorter)StarModuleManager.get().getModuleRegistry().getModule("ServerSorter")).getGameServers(type)){
			if (server.getGameState() != GameState.LOBBY && server.getGameState() != GameState.LOADING) continue;
				if (position == centerPosition){
					gui.setItem(position, getServerIcon(server));
					position++;
					continue;
				}
			    
				if (position > centerPosition){
					for (int i = position; gui.getItem(i) != null && gui.getItem(i).getType() != Material.AIR; i--){
						position--;
					}
					
					gui.setItem(position, getServerIcon(server));
					continue;
				}
					
				if (position < centerPosition){
					for (int i = position; gui.getItem(i) != null && gui.getItem(i).getType() != Material.AIR; i--){
						position++;
					}
					
					gui.setItem(position, getServerIcon(server));
					continue;
				}

			}
			
		}
	
	private ItemStack getServerIcon(GameServerInfo server){
		List<String> lore = Arrays.asList(" ",
				"" + server.getGameState().getStatus(false),
				" ",
				F.YELLOW + "Players: " + F.WHITE + server.getPlayerCount() + "/" + server.getMaxPlayerCount(),
				F.YELLOW + "Game: " + F.WHITE + server.getGameType().getName(),
				F.YELLOW + "Map: " + F.WHITE + server.getMapName(),
				F.boldYellow + ">   CLICK TO JOIN   <");
		
		Material mat;
		if (server.getGameState() == GameState.LOBBY || server.getGameState() == GameState.LOADING){
			mat = Material.EMERALD_BLOCK;
		} else {
			mat = Material.REDSTONE_BLOCK;
		}
		
		ItemStack serverIcon = ItemFactory.createItem(mat, F.boldYellow + "Server " + server.getName(), lore, server.getPlayerCount());
		return serverIcon;
	}
}
