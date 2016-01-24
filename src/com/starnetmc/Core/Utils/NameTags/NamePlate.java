package com.starnetmc.Core.Utils.NameTags;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class NamePlate {

    Team team;

    public NamePlate(String team) {
        ScoreboardManager a = Bukkit.getServer().getScoreboardManager();
        Scoreboard b = a.getMainScoreboard();
        Team t = b.getTeam(team);
        if (b.getTeam(team) != null) {
            this.team = t;
        } else {
            b.registerNewTeam(team);
            this.team = t;
        }
    }

    public NamePlate setPlate(Player player, String prefix, String suffix) {
        team.addPlayer(player);
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        return this;
    }
}
