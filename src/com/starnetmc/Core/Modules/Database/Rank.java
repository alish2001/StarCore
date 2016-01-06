package com.starnetmc.Core.Modules.Database;

import com.starnetmc.Core.Utils.F;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali on 1/2/2016 at 5:38 PM.
 */

public enum Rank {

    OWNER("OWNER", ChatColor.DARK_RED),
    DEV("DEV", ChatColor.LIGHT_PURPLE),
    ADMIN("ADMIN", ChatColor.RED),
    MOD("MOD", ChatColor.GOLD),
    BUILDER("OWNER", ChatColor.BLUE),
    YOUTUBE("YOU[TUBE]", ChatColor.RED),
    TWITCH("OWNER", ChatColor.DARK_RED),
    STAR("OWNER", ChatColor.DARK_RED),
    MVP("MVP", ChatColor.YELLOW),
    DEFAULT("DEFAULT", ChatColor.WHITE);

    private String tag;
    private ChatColor color;

    Rank(String tag, ChatColor color){
        this.tag = tag;
        this.color = color;
    }

    public String getTag(boolean color){

        if (color){

            if (tag == "YOU[TUBE]"){
                tag = F.boldRed + "YOU" + F.boldWhite + "[TUBE]";
                return tag;
            }

            return color + tag;

        } else {

            return tag;
        }
    }

    public ChatColor getColor(){
        return color;
    }

    public static Rank getRankFromString(String rank){
        Rank finalRank = DEFAULT;

        for (Rank r : getAllRanks()) {
            if (r.toString().equalsIgnoreCase(rank)) {
                finalRank = r;
            }
        }

        return finalRank;
    }

    public static List<Rank> getAllRanks(){
        List<Rank> ranks = new ArrayList<Rank>();
        ranks.add(OWNER);
        ranks.add(DEV);
        ranks.add(ADMIN);
        ranks.add(MOD);
        ranks.add(BUILDER);
        ranks.add(YOUTUBE);
        ranks.add(TWITCH);
        ranks.add(STAR);
        ranks.add(MVP);
        ranks.add(DEFAULT);
        return ranks;
    }

    public static List<Rank> getStaffRanks(){
        List<Rank> ranks = new ArrayList<Rank>();
        ranks.add(OWNER);
        ranks.add(DEV);
        ranks.add(ADMIN);
        ranks.add(MOD);
        ranks.add(BUILDER);
        return ranks;
    }
}