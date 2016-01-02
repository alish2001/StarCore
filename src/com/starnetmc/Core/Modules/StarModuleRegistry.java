package com.starnetmc.Core.Modules;

import java.util.ArrayList;

/**
 * Created by Ali on 1/1/2016 at 5:35 PM.
 */

public class StarModuleRegistry {

    public ArrayList<StarModule> moduleRegistry;

    public StarModuleRegistry(ArrayList<StarModule> modules){
        moduleRegistry = modules;
    }

    public StarModuleRegistry(){
        moduleRegistry = new ArrayList<StarModule>();
    }

    public ArrayList<StarModule> getModuleRegistry(){
        return moduleRegistry;
    }

    public void setModuleRegistery(ArrayList<StarModule> moduleRegistery) {
        this.moduleRegistry = moduleRegistery;
    }

}