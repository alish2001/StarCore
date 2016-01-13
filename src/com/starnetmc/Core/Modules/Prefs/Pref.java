package com.starnetmc.Core.Modules.Prefs;

import com.starnetmc.Core.Modules.StarModuleManager;
import com.starnetmc.Core.Utils.F;
import com.starnetmc.Core.Utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ali on 1/11/2016 at 5:22 PM.
 */

public class Pref implements Listener {

    private String name;
    private boolean active;
    private Material iconMat;
    private List<String> desc;

    public Pref(String name, boolean active, Material iconMat, List<String> desc){
        this.name = name;
        this.active = active;
        this.iconMat = iconMat;
        this.desc = desc;
    }

    public Pref(){
        this.name = "NOT FOUND";
        this.active = false;
        this.iconMat = Material.BARRIER;
        this.desc = Arrays.asList("", "DESC. NOT FOUND");
    }

    public void register(){
        Bukkit.getServer().getPluginManager().registerEvents(this, StarModuleManager.get().getCore());
    }

    public void unRegister(){
        HandlerList.unregisterAll(this);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public void setIconMat(Material mat){
        this.iconMat = mat;
    }

    public void setDesc(List<String> desc){
        this.desc = desc;
    }

    public String getName(){
        return name;
    }

    public boolean isActive(){
        return active;
    }

    public Material getIconMat(){
        return iconMat;
    }

    public ItemStack getIcon(){

        ArrayList<String> finalIconDesc = new ArrayList<String>();
        finalIconDesc.add(" ");
        finalIconDesc.addAll(desc);

        ItemStack icon = ItemFactory.createItem(iconMat, F.WHITE + name, finalIconDesc);

        return icon;
    }

    public ItemStack getStatusIcon(boolean enabled){

        ItemStack icon = new ItemStack(Material.AIR);
        ArrayList<String> finalIconDesc = new ArrayList<String>();
        finalIconDesc.add(" ");
        finalIconDesc.addAll(desc);
        finalIconDesc.add(" ");

        if (active){

            finalIconDesc.add(F.YELLOW + getName() + F.WHITE + " Is currently " + F.GREEN + "Enabled");
            finalIconDesc.add(F.WHITE + "Click to " + F.RED + "Disable");
            icon = ItemFactory.createItem(Material.EMERALD_BLOCK, F.boldGreen + "ENABLED", finalIconDesc);

        } else {

            finalIconDesc.add(F.YELLOW + getName() + F.WHITE + " Is currently " + F.RED + "Disabled");
            finalIconDesc.add(F.WHITE + "Click to " + F.GREEN + "Enable");
            icon = ItemFactory.createItem(Material.REDSTONE_BLOCK, F.boldRed + "DISABLED", finalIconDesc);
        }

        return icon;
    }

    public List<String> getDesc(){
        return desc;
    }

}
