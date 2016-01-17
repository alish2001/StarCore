package com.starnetmc.Core.Modules.PlanetaryDevastation;

import com.starnetmc.Core.Modules.StarModule;
import com.starnetmc.Core.Modules.StarModuleManager;
import com.starnetmc.Core.Utils.F;
import com.starnetmc.Core.Utils.ULocation;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * Created by Ali on 1/14/2016 at 10:33 PM.
 */

public class PlanetaryDevastation extends StarModule {

    public PlanetaryDevastation(){
        super("PlanetaryDevastation", 1.0);
    }

    @Override
    public void onEnable(){

    }

    @Override
    public void onDisable(){

    }

    @EventHandler
    public void onJutsuActivate(PlayerInteractEvent e){
        if (e.getAction() != Action.LEFT_CLICK_BLOCK) return;
        Player p = e.getPlayer();
        Location gravityCore = p.getLocation().add(0, 35, 0);
        p.sendMessage(F.info("Gravity Style", "PLANETARY DEVASTATION!"));
        //Planetary Devastation/Chibaku Tensei

        gravityCore.getBlock().setType(Material.OBSIDIAN);
        for (Location l : ULocation.getRegionBlocks(p.getLocation().add(7, 0, 0), 1)){
            throwBlockToCore(l, gravityCore, 1, new Vector(0, 2.5, 0));
        }

    }

    private void throwBlockToCore(Location block, Location core, double speed, Vector baseVelocity){

        double g = 20;
        double v = speed * 20;

        double dx = Math.abs(core.getX() - block.getX());
        double dz = Math.abs(core.getZ() - block.getZ());
        double dy = (block.getY() - core.getY()) / 2;

        double dh = Math.sqrt(dx * dx + dz * dz);
        if (dh > 32) {
            dh += Math.sqrt(dh);
        }

        double pitch = Math.atan( (v * v - Math.sqrt(v * v * v * v - g * (g * dh * dh + 2 * 2 * dy * v * v))) / ( g * dh ) );

        if (Double.isNaN(pitch)) {
            return;
        }

        double time = dh/v;
        Vector target = block.toVector().subtract(core.toVector()).add(baseVelocity.multiply(time * 20));
        target.setY(Math.tan(pitch) * Math.sqrt(dh * dh + dy * dy));

        Material bMat = block.getBlock().getType();
        block.getBlock().breakNaturally(new ItemStack(Material.AIR));
        FallingBlock fb = block.getWorld().spawnFallingBlock(block, bMat, (byte)0);
        fb.setVelocity(new Vector(0, 0.75, 0));

        new BukkitRunnable(){
            public void run(){
                fb.setVelocity(target);
            }
        }.runTaskLater(StarModuleManager.get().getCore(), 5);

    }
}
