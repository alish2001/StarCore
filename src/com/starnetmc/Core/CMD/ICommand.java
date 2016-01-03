package com.starnetmc.Core.CMD;

import com.starnetmc.Core.Modules.Database.Rank;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * Created by Ali on 1/2/2016 at 5:37 PM.
 */

public abstract interface ICommand {

    public abstract void setCommandCenter(CommandCenter paramCommandCenter);

    public abstract void execute(Player paramPlayer, String[] paramArrayOfString);

    public abstract Collection<String> Aliases();

    public abstract void setAliasUsed(String paramString);

    public abstract Rank getRequiredRank();
}

