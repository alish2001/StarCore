package com.starnetmc.Core;

import com.starnetmc.Core.CMD.CommandCenter;
import com.starnetmc.Core.Modules.Database.ExampleModule;
import com.starnetmc.Core.Modules.StarModuleManager;
import com.starnetmc.Core.Modules.StarModuleRegistry;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ali on 1/1/2016 at 4:10 PM.
*/

public class StarCore extends JavaPlugin {

    @Override
    public void onEnable(){

        //Command System Init
        CommandCenter.init(this);

        //Module System Init
        StarModuleRegistry registry = new StarModuleRegistry();
        //registry.addModule(new ExampleModule());
        StarModuleManager.init(new StarModuleManager(this, registry));
        StarModuleManager.get().enable();
    }

    @Override
    public void onDisable() {
        StarModuleManager.get().disable();
    }
}