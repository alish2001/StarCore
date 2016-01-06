package com.starnetmc.Core.Modules.ModuleController;

import com.starnetmc.Core.CMD.CommandBase;
import com.starnetmc.Core.Modules.Database.Rank;
import com.starnetmc.Core.Modules.StarModule;
import com.starnetmc.Core.Modules.StarModuleManager;
import com.starnetmc.Core.Utils.F;
import org.bukkit.entity.Player;

/**
 * Created by Ali on 1/5/2016 at 7:51 PM.
 */

public class ModuleControllerCMD extends CommandBase<ModuleController> {

    public ModuleControllerCMD(ModuleController plugin) {
        super(plugin, Rank.DEV, new String[] { "starmodules", "starmodule", "modulecontrol", "modulecontroller" });
    }

    public void execute(Player p, String[] args){
        if (args.length < 2){
            help(p);
            return;
        }

        if (args[0].equalsIgnoreCase("Enable")){
            if (!StarModuleManager.get().getModuleRegistry().doesExist(args[1])){
                p.sendMessage(F.error("ModuleController", "Sorry but this module doesn't exist in this StarModuleRegistry."));
                return;
            } else
              if (StarModuleManager.get().getModuleRegistry().getModule(args[1]).isEnabled()){
                  p.sendMessage(F.error("ModuleController", "This module is already enabled!"));
                  return;
              }

            StarModuleManager.get().getModuleRegistry().getModule(args[1]).startup();
            p.sendMessage(F.info("ModuleController", StarModuleManager.get().getModuleRegistry().getModule(args[1]).getName() + " has been enabled and has booted up successfully!"));
            return;
        }

        if (args[0].equalsIgnoreCase("Disable")){
            if (!StarModuleManager.get().getModuleRegistry().doesExist(args[1])){
                p.sendMessage(F.error("ModuleController", "Sorry but this module doesn't exist in this StarModuleRegistry."));
                return;
            } else
            if (StarModuleManager.get().getModuleRegistry().getModule(args[1]).isEnabled()){
                p.sendMessage(F.error("ModuleController", "This module is already disabled!"));
                return;
            }

            StarModuleManager.get().getModuleRegistry().getModule(args[1]).shutdown();
            p.sendMessage(F.info("ModuleController", StarModuleManager.get().getModuleRegistry().getModule(args[1]).getName() + " has been disabled and has stopped successfully!"));
            return;
        }

        if (args[0].equalsIgnoreCase("Info")){
            if (!StarModuleManager.get().getModuleRegistry().doesExist(args[1])){
                p.sendMessage(F.error("ModuleController", "Sorry but this module doesn't exist in this StarModuleRegistry."));
                return;
            }

            StarModule m = StarModuleManager.get().getModuleRegistry().getModule(args[1]);

            p.sendMessage(F.listTop("Module Info: " + m.getName()));
            p.sendMessage(F.YELLOW + "Registered Name: " + m.getName());
            p.sendMessage(F.YELLOW + "Current Version: " + m.getVersion());

            if (m.isEnabled()){
                p.sendMessage(F.YELLOW + "Current State: " + F.boldGreen + "ENABLED!");
            } else {
                p.sendMessage(F.YELLOW + "Current State: " + F.boldRed + "DISABLED!");
            }
            
            return;
        }
    }

    private void help(Player p){
        p.sendMessage(F.listTop("Module Controller"));
        p.sendMessage(F.cmdList("/ModuleController Enable <Module Name>", "Enables the module if it is not already enabled.", Rank.DEV));
        p.sendMessage(F.cmdList("/ModuleController Disable <Module Name>", "Disables the module if it is not already disabled.", Rank.DEV));
        p.sendMessage(F.cmdList("/ModuleController Info <Module Name>", "Lists info about the module.", Rank.DEV));
    }
}