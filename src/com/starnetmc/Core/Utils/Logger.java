package com.starnetmc.Core.Utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Ali on 1/1/2016 at 6:09 PM.
 */

public class Logger {

    private static ArrayList<Player> logees = new ArrayList<Player>();

    public static void log(String msg){
        String log = "<Logger> " + msg;
        System.out.println(log);

        log = F.boldYellow + log;
        for (Player p : logees){
            p.sendMessage(log);
        }
    }

    public static void addLogee(Player p){
        logees.add(p);
    }

    public static void removeLogee(Player p){
        if (isLogee(p)){
            logees.remove(p);
        }
    }

    public static ArrayList<Player> getLogees(){
        return logees;
    }

    public static boolean isLogee(Player p){
        return logees.contains(p);
    }
}