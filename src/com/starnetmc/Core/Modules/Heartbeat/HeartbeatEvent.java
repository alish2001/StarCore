package com.starnetmc.Core.Modules.Heartbeat;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Ali on 1/10/2016 at 6:41 PM.
 */

public class HeartbeatEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final Beat beat;

    public HeartbeatEvent(Beat beat) {
        this.beat = beat;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Beat getBeat() {
        return beat;

    }

}
