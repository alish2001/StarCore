package com.starnetmc.Core.Modules;

import com.starnetmc.Core.Utils.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

/**
 * Created by Ali on 1/1/2016 at 5:35 PM.
*/

public class StarModuleManager {

    public static StarModuleManager instance;

    private JavaPlugin core;
    private StarModuleRegistry registry;

    public StarModuleManager(JavaPlugin core, StarModuleRegistry registry){
        this.core = core;
        this.registry = registry;
    }

    public static void init(StarModuleManager manager){
        instance = manager;
        Logger.log("<StarModuleManager> Initializing Module System.");
    }

    public static StarModuleManager get(){
        return instance;
    }

    public void enable(){
        for (StarModule s : registry.getModules()){
            s.startup();
        }

        Logger.log("<StarModuleManager> Module System Enabled.");
    }

    public void disable(){
        for (StarModule m : registry.getModules()){
            m.shutdown();
        }

        Logger.log("<StarModuleManager> Module System Disabled.");
    }

    public JavaPlugin getCore(){
        return core;
    }

    public void setCore(JavaPlugin newCore){
        core = newCore;
    }

    public StarModuleRegistry getModuleRegistry(){
        return registry;
    }

    public void setModuleRegistry(StarModuleRegistry newRegistry){
        registry = newRegistry;
    }

}