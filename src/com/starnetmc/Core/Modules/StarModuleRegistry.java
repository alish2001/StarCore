package com.starnetmc.Core.Modules;

import com.starnetmc.Core.Utils.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ali on 1/1/2016 at 5:35 PM.
*/

public class StarModuleRegistry {

    private HashMap<String, StarModule> registry;

    public StarModuleRegistry(HashMap<String, StarModule> modules){
        registry = modules;
    }

    public StarModuleRegistry(){
        registry = new HashMap<String, StarModule>();
        Logger.log("<Module Registry> New Module Registry file created!");
    }

    public HashMap<String, StarModule> getRegistry(){
        return registry;
    }

    public List<StarModule> getModules(){
        List<StarModule> modules = new ArrayList<StarModule>();

        for (Map.Entry<String, StarModule> e : registry.entrySet()){
            modules.add(e.getValue());
        }

        return modules;
    }

    public void setModules(HashMap<String, StarModule> moduleRegistry) {
        this.registry = moduleRegistry;
    }

    public void addModule(StarModule module){
        registry.put(module.getName().toLowerCase(), module);
    }

    public void removeModule(StarModule module){
        registry.remove(module.getName().toLowerCase());
    }

    public StarModule getModule(String id){
        StarModule module = registry.get(id.toLowerCase());
        return module;
    }

    public boolean doesExist(String id){
        return getModule(id) != null;
    }

}