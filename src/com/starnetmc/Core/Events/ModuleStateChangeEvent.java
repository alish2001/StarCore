package com.starnetmc.Core.Events;

import com.starnetmc.Core.Modules.StarModule;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Ali on 1/10/2016 at 5:59 PM.
 */

public class ModuleStateChangeEvent extends Event {

    private static HandlerList handlers = new HandlerList();
    private StarModule module;

    public ModuleStateChangeEvent(StarModule module) {
        this.module = module;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public StarModule getModule() {
        return module;
    }

}
