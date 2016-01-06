package com.starnetmc.Core.Modules;

import com.starnetmc.Core.CMD.CommandCenter;
import com.starnetmc.Core.CMD.ICommand;
import com.starnetmc.Core.Utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.ArrayList;

/**
 * Created by Ali on 1/1/2016 at 5:35 PM at 8:32 PM.
*/

public class StarModule implements Listener {

    private String moduleName;
    private double moduleVersion;
    private ArrayList<ICommand> cmds;
    private boolean enabled;

    public StarModule(String moduleName, double moduleVersion){
        this.moduleName = moduleName;
        this.moduleVersion = moduleVersion;
    }

    public StarModule(){
    }

    public void startup(){
        Bukkit.getServer().getPluginManager().registerEvents(this, StarModuleManager.get().getCore());
        for (ICommand cmd : cmds){
            CommandCenter.get().addCommand(cmd);
        }

        onEnable();
        setState(true);
        log("has been started up!");
    }

    public void shutdown(){
        HandlerList.unregisterAll(this);
        for (ICommand cmd : cmds){
            CommandCenter.get().removeCommand(cmd);
        }

        onDisable();
        setState(false);
        log("has been shutdown.");
    }

    public void onEnable(){

    }

    public void onDisable() {

    }

    public void addCommand(ICommand command){
        if (!cmds.contains(command)){
            cmds.add(command);
        }
    }

    public void removeCommand(ICommand command){
        if (cmds.contains(command)){
            cmds.remove(command);
        }
    }

    public void setState(boolean enabled){
        this.enabled = enabled;
        if (enabled){
            log("The module state has been changed to enabled.");
        } else {
            log("The module state has been changed to disabled.");
        }
    }

    public void log(String msg){
        Logger.log("<" + moduleName + "> " + msg);
    }

    public String getName(){
        return moduleName;
    }

    public double getVersion(){
        return moduleVersion;
    }

    public ArrayList<ICommand> getCmds(){
        return cmds;
    }

    public boolean isEnabled(){
        return enabled;
    }
}