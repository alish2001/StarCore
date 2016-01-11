package com.starnetmc.Core.Modules.Heartbeat;

import com.starnetmc.Core.Modules.StarModule;
import com.starnetmc.Core.Modules.StarModuleManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ali on 1/10/2016 at 6:31 PM.
 */

public class Heartbeat extends StarModule {

    public Heartbeat(){
        super("Heartbeat", 1.0);
    }

    @Override
    public void onEnable(){
        Heart heart = new Heart(StarModuleManager.get().getCore());
        heartbeatTaskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(StarModuleManager.get().getCore(), heart, 0L, 100L);
    }

    @Override
    public void onDisable(){
        Bukkit.getServer().getScheduler().cancelTask(heartbeatTaskID);
    }

    private int heartbeatTaskID;

    public void setHeartbeatTaskID(int id){
        this.heartbeatTaskID = id;
    }

    public int getHeartbeatTaskID(){
        return heartbeatTaskID;
    }

}
