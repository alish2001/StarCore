package com.starnetmc.Core.Modules.Heartbeat;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ali on 1/10/2016 at 6:37 PM.
 */

public class Heart implements Runnable {

    private JavaPlugin core;

    public Heart(JavaPlugin core){
        this.core = core;
    }

    public void run(){
        Beat[] arrayOfUpdateType;
        int j = (arrayOfUpdateType = Beat.values()).length;
        for (int i = 0; i < j; i++) {
            Beat beat = arrayOfUpdateType[i];
            if (beat.elapsed()) {
                core.getServer().getPluginManager().callEvent(new HeartbeatEvent(beat));
            }
        }
    }

    public void setCore(JavaPlugin core){
        this.core = core;
    }

    public JavaPlugin getCore(){
        return core;
    }

}
