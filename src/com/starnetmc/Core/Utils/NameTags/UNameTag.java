package com.starnetmc.Core.Utils.NameTags;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

/**
 * Created by Ali on 1/17/2016 at 7:10 PM.
 */

public class UNameTag {

    public static void setNameTag(Player p, String prefix, String suffix) {
        NamePlate namePlate = new NamePlate(prefix);
        namePlate.setPlate(p, prefix, suffix);
    }

}
