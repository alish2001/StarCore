package com.starnetmc.Core.Modules;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ali on 1/1/2016 at 5:35 PM.
*/

public class StarModuleManager {

    public static JavaPlugin core;
    public static StarModuleRegistry registry;

    public static void enable(){
        for (StarModule m : registry.getModuleRegistry()){
            m.startup();
        }
    }

    public static void disable(){
        for (StarModule m : registry.getModuleRegistry()){
            m.shutdown();
        }
    }

    public static JavaPlugin getCore(){
        return core;
    }

    public static void setCore(JavaPlugin newCore){
        core = newCore;
    }

    public static StarModuleRegistry getModuleRegistry(){
        return registry;
    }

    public static void setModuleRegistry(StarModuleRegistry newRegistry){
        registry = newRegistry;
    }

}