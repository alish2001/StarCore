package com.starnetmc.Core;

import com.starnetmc.Core.CMD.CommandCenter;
import com.starnetmc.Core.Modules.EMS.Sharingan;
import com.starnetmc.Core.Modules.Heartbeat.Heartbeat;
import com.starnetmc.Core.Modules.HubStabilizer.HubStabilizer;
import com.starnetmc.Core.Modules.ModuleController.ModuleController;
import com.starnetmc.Core.Modules.PlanetaryDevastation.PlanetaryDevastation;
import com.starnetmc.Core.Modules.ServerSorter.ServerSorter;
import com.starnetmc.Core.Modules.StarModuleManager;
import com.starnetmc.Core.Modules.StarModuleRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ali on 1/1/2016 at 4:10 PM.
*/

public class StarCore extends JavaPlugin {

    @Override
    public void onEnable(){

        //Command System Init
        CommandCenter.init(this);

        //Registry
        StarModuleRegistry registry = new StarModuleRegistry();
        registry.addModule(new ModuleController());
        registry.addModule(new Heartbeat());
        registry.addModule(new HubStabilizer());
        //registry.addModule(new ServerSorter());
        registry.addModule(new PlanetaryDevastation());
        registry.addModule(new Sharingan());

        //Module System Init
        StarModuleManager.init(new StarModuleManager(this, registry));
        StarModuleManager.get().enable();
        ((HubStabilizer)registry.getModule("hubstabilizer")).setDamage(true);
    }

    @Override
    public void onDisable(){
        StarModuleManager.get().disable();
    }
}