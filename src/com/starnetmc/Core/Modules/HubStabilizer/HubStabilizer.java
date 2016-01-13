package com.starnetmc.Core.Modules.HubStabilizer;

import com.starnetmc.Core.Modules.Database.Rank;
import com.starnetmc.Core.Modules.StarModule;
import com.starnetmc.Core.Utils.F;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.help.HelpTopic;

/**
 * Created by Ali on 1/10/2016 at 5:13 PM.
 */

public class HubStabilizer extends StarModule {

    public HubStabilizer(){
        super("HubStablilizer", 1.0);
    }

    @Override
    public void onEnable(){
        damageController = true;
        foodController = true;
        explosionController = true;
        dropController = true;
        loginController= true;
        weatherController = true;
        inventoryController = false;
    }

    @Override
    public void onDisable(){
        damageController = false;
        foodController = false;
        explosionController = false;
        dropController = false;
        loginController= false;
        weatherController = false;
        inventoryController = false;
    }

    private boolean damageController;
    private boolean foodController;
    private boolean explosionController;
    private boolean dropController;
    private boolean loginController;
    private boolean weatherController;
    private boolean inventoryController;

    public void setDamage(boolean b){
        damageController = b;
    }

    public void setFood(boolean b){
        foodController = b;
    }

    public void setExplosion(boolean b){
        explosionController = b;
    }

    public void setDrop(boolean b){
        dropController = b;
    }

    public void setLogin(boolean b){
        loginController = b;
    }

    public void setWeather(boolean b){
        weatherController = b;
    }

    public void setInventory(boolean b){
        inventoryController = b;
    }

    public boolean getDamage(){
        return damageController;
    }

    public boolean getFood(){
        return foodController;
    }

    public boolean getExplosion(){
        return explosionController;
    }

    public boolean getDrop(){
        return dropController;
    }

    public boolean getLogin(){
        return loginController;
    }

    public boolean getWeather(){
        return weatherController;
    }

    public boolean getInventory(){
        return inventoryController;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (!loginController) return;
        Player player = e.getPlayer();
        player.setMaxHealth(20D);
        player.setFoodLevel(20);
        player.setHealthScale(20D);
        e.setJoinMessage(F.boldAqua + "<" + F.boldGreen + "+" + F.boldAqua + ">" + ChatColor.RESET + F.YELLOW + " " + player.getName());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        if (!loginController) return;
        e.setQuitMessage(F.boldAqua + "<" + F.boldRed + "-" + F.boldAqua + ">" + ChatColor.RESET + F.YELLOW + " " + e.getPlayer().getName());
    }

    @EventHandler
    public void onInv(InventoryClickEvent e){
        if (!inventoryController) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onFight(EntityDamageByEntityEvent e){
        if (!damageController) return;

        e.setDamage(0.0D);
        e.setCancelled(true);

        if (e.getCause() == EntityDamageEvent.DamageCause.VOID){

            Entity en = e.getEntity();
            en.teleport(en.getWorld().getSpawnLocation());
        }
    }

    @EventHandler
    public void onBurn(EntityCombustEvent e){
        if (!damageController) return;
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        if (!foodController) return;
        e.setFoodLevel(20);
        e.setCancelled(true);
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent e){
        if (!explosionController) return;
        e.blockList().clear();
    }

    @EventHandler
    public void onNonWhitelistLogin(AsyncPlayerPreLoginEvent e){
        if (e.getLoginResult() == AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST) {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, F.error("StarNet", "This server is currently in private mode."));
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if (!dropController) return;
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onWeatherChange(WeatherChangeEvent e) {
        if (!weatherController) return;
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void blockCommands(PlayerCommandPreprocessEvent e) throws Exception {

        if (e.getMessage().startsWith("/stop")) {

            e.setCancelled(true);
            //TODO ADD RANK SUPPORT
           // if (AccountManager.getAccount(e.getPlayer()).getRank() != Rank.OWNER){
            //    e.getPlayer().sendMessage(F.error("Permissions", "Much deny permissions, so wow."));
              //  return;
           // }

            Bukkit.broadcastMessage(F.error("StarNet", "This server is shutting down."));
            try {
                for (Entity en : e.getPlayer().getWorld().getEntities()) {

                    if (en instanceof Player) {
                        return;
                    }

                    en.remove();

                }

            } finally {
                Bukkit.getServer().shutdown();
            }

        }

        if (e.getMessage().startsWith("/me")) {
            e.setCancelled(true);
            //TODO ADD RANK SUPPORT
            e.getPlayer().sendMessage(F.error("Permissions", "Much deny permissions, so wow."));
        }

        if (e.getMessage().startsWith("/help") || e.getMessage().startsWith("/?")) {
            e.setCancelled(true);
            //TODO SEND HELP
        }

    }

}
