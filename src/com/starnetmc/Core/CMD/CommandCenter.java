package com.starnetmc.Core.CMD;

import com.starnetmc.Core.Modules.Database.Rank;
import com.starnetmc.Core.Utils.F;
import com.starnetmc.Core.Utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * Created by Ali on 1/2/2016 at 5:36 PM.
 */

public class CommandCenter implements Listener{

    public static CommandCenter instance;

    private HashMap<String, ICommand> commands;
    private JavaPlugin core;

    public CommandCenter(JavaPlugin plugin) {
        this.core = plugin;
        this.commands = new HashMap<String, ICommand>();
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static void init(JavaPlugin plugin) {
        instance = new CommandCenter(plugin);
        Logger.log("<Command Center> Initializing Command System.");
    }

    public static CommandCenter get(){
        return instance;
    }

    @EventHandler
    public void runCommands(PlayerCommandPreprocessEvent e) {

        String commandName = e.getMessage().substring(1);
        String[] args = null;

                if (commandName.contains(" ")) {
                    commandName = commandName.split(" ")[0];
                    args = e.getMessage().substring(e.getMessage().indexOf(' ') + 1).split(" ");
                } else {
                    args = new String[0];
                }

                ICommand command = (ICommand) this.commands.get(commandName.toLowerCase());

                if (command != null) {

                    if (!(getRank(e.getPlayer()).compareTo(command.getRequiredRank()) <= 0)) {
                        e.getPlayer().sendMessage(F.error("Permissions", "Such Permission, much not [ " + command.getRequiredRank().toString() + " ] Rank."));
                        return;
            }

            command.setAliasUsed(commandName.toLowerCase());
            command.execute(e.getPlayer(), args);

            e.setCancelled(true);
        } else {
            e.getPlayer().sendMessage("Unknown Command. Type '/help' u skrub.");
        }

    }

    public void addCommand(ICommand command) {
        for (String commandRoot : command.Aliases()) {
            this.commands.put(commandRoot.toLowerCase(), command);
            command.setCommandCenter(this);
        }
    }

    public void removeCommand(ICommand command) {
        for (String commandRoot : command.Aliases()) {
            this.commands.remove(commandRoot.toLowerCase());
            command.setCommandCenter(null);
        }
    }

    public Rank getRank(Player player){
        //TODO ADD ACCOUNT MANAGER RANK SUPPORT.
        return Rank.DEFAULT;
    }

}