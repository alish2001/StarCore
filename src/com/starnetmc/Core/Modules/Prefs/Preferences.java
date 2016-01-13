package com.starnetmc.Core.Modules.Prefs;

import com.starnetmc.Core.Modules.StarModule;

import java.util.HashMap;

/**
 * Created by Ali on 1/10/2016 at 7:19 PM.
 */

public class Preferences extends StarModule {

    public Preferences(){
        super("Preferences", 1.0);
    }

    private HashMap<String, HashMap<String, Pref>> prefRegistry;

    public HashMap<String, HashMap<String, Pref>> getPrefRegistry(){
        return prefRegistry;
    }
}
