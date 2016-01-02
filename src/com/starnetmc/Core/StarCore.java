package com.starnetmc.Core;

import com.starnetmc.Core.Modules.StarModuleManager;
import com.starnetmc.Core.Modules.StarModuleRegistry;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ali on 1/1/2016 at 4:10 PM.
 */

public class StarCore extends JavaPlugin {

    @Override
    public void onEnable(){
        StarModuleRegistry registry = new StarModuleRegistry();
        StarModuleManager.setModuleRegistry(registry);
        StarModuleManager.enable();
    }
    
    @Override
    public void onDisable() {
        StarModuleManager.disable();
    }
}
