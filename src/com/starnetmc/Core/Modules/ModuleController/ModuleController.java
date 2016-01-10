package com.starnetmc.Core.Modules.ModuleController;

import com.starnetmc.Core.Modules.StarModule;

/**
 * Created by Ali on 1/5/2016 at 7:46 PM.
 */

public class ModuleController extends StarModule {

    public ModuleController(){
        super("ModuleController", 1.0);

        addCommand(new ModuleControllerCMD(this));
    }

    @Override
    public void onEnable(){

    }

    @Override
    public void onDisable(){

    }

}