package com.starnetmc.Core.CMD;

import com.starnetmc.Core.Modules.Database.Rank;
import com.starnetmc.Core.Modules.StarModule;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Ali on 1/2/2016 at 7:50 PM.
 */

public class CommandBase<PluginType extends StarModule> implements ICommand{

    private Rank _requiredRank;
    private List<String> _aliases;
    protected PluginType Plugin;
    protected String aliasUsed;
    protected CommandCenter CommandCenter;

    public CommandBase(PluginType plugin, Rank requiredRank, String... aliases){
        this.Plugin = plugin;
        this._requiredRank = requiredRank;
        this._aliases = Arrays.asList(aliases);
    }

    public Collection<String> Aliases(){
        return this._aliases;
    }

    public void setAliasUsed(String alias){
        this.aliasUsed = alias;
    }

    public Rank getRequiredRank(){
        return this._requiredRank;
    }

    public void setCommandCenter(CommandCenter commandCenter){
        this.CommandCenter = commandCenter;
    }


    public void execute(Player paramPlayer, String[] paramArrayOfString){

    }
}