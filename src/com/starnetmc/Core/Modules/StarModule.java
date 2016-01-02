package com.starnetmc.Core.Modules;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * Created by Ali on 1/1/2016 at 5:35 PM at 8:32 PM.
*/

public class StarModule implements Listener {

    private String moduleName;
    private double moduleVersion;

    public StarModule(String moduleName, double moduleVersion){
        this.moduleName = moduleName;
        this.moduleVersion = moduleVersion;
    }

    public void startup(){
        Bukkit.getServer().getPluginManager().registerEvents(this, StarModuleManager.getCore());
    }

    public void shutdown(){
        HandlerList.unregisterAll(this);
    }

    public void onEnable(){

    }

    public void onDisable(){

    }

    public void registerCMD() {

    }

    public String getName(){
        return moduleName;
    }

    public double getVersion(){
        return moduleVersion;
    }
}