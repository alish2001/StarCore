package com.starnetmc.Core.Modules.EMS;

import com.starnetmc.Core.Modules.StarModule;
import com.starnetmc.Core.Utils.F;
import com.starnetmc.Core.Utils.Particles.ParticleEffect;
import com.starnetmc.Core.Utils.ULocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

/**
 * Created by Ali on 1/16/2016 at 2:25 PM.
 */

public class Sharingan extends StarModule {

    public Sharingan(){
        super("EMS", 1.0);
    }

    @Override
    public void onEnable(){

    }

    @Override
    public void onDisable(){

    }

    @EventHandler
    public void onJutsuActivate(PlayerInteractEvent e){
        if (e.getAction() != Action.LEFT_CLICK_AIR) return;
        Player p = e.getPlayer();
        p.sendMessage(F.info("Ocular Style", "AMATERASU!"));

        Entity target = getBestTarget(p);
        if (target == null){
            p.sendMessage(F.error("Amaterasu", "No target found within amaterasu range."));
            return;
        }

        double length = p.getLocation().distanceSquared(target.getLocation());

        Location amaterasu = p.getLocation().clone().add(p.getLocation().getDirection().multiply(length));
        ParticleEffect.SMOKE_LARGE.display(0.15f, 0.2f, 0.15f, 0f, 78, amaterasu.clone().add(0, 0, 1), 128.0);
        ParticleEffect.SMOKE_LARGE.display(0.20f, 0.25f, 0.20f, 0f, 78, amaterasu, 128.0);
        ParticleEffect.SMOKE_LARGE.display(0.15f, 0.2f, 0.15f, 0f, 78, amaterasu.clone().add(1, 0, 0), 128.0);
        //target.setFireTicks(40);
    }

    private Entity getBestTarget(Player player) {
        double optimal = 128;
        double min = 128;

        Entity closest = null;
        Location loc = player.getLocation();
        for (Entity ent : player.getWorld().getEntities()) {
            if (!(ent instanceof LivingEntity)) {
                continue;
            }
            if (!player.hasLineOfSight(ent)) {
                continue;
            }
            if (ent instanceof Player && ((Player) ent).getName().equals(player.getName())) {
                continue;
            }
            Location eLoc = ent.getLocation();
            Vector direction = loc.getDirection();
            Vector pointDir = getDirection(loc, eLoc);
            double diff = Math.abs(direction.subtract(pointDir).length()) * optimal + Math.sqrt(loc.distanceSquared(eLoc));
            if (diff < min) {
                min = diff;
                closest = ent;
            }
        }
        return closest;
    }

    private Vector getDirection(Location pos1, Location pos2) {
        Vector vec1 = pos1.toVector();
        Vector vec2 = pos2.toVector();
        return vec2.subtract(vec1).normalize();
    }
}
