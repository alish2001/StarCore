package com.starnetmc.Core.Modules.ServerSorter.Getters;

import java.util.List;

public class ServerResponse {
	
    private String description;
    private Players players;
    private Version version;
    private String favicon;
    private int time;
    
    public ServerResponse(String description, Players players, Version version, String favicon, int time){
    	this.description = description;
    	this.players = players;
    	this.version = version;
    	this.favicon = favicon;
    	this.time = time;
    }
    
    public void setDescription(String description){
    	this.description = description;
    }
    
    public void setPlayers(Players players){
    	this.players = players;
    }
    
    public void setVersion(Version version){
    	this.version = version;
    }
    
    public void setFavicon(String favicon){
    	this.favicon = favicon;
    }
    
    public void setTime(int time){
    	this.time = time;
    }
    
    public String getMOTD(){
    	return description;
    }
    
    public String getDescription(){
    	return description;
    }
    
    public Players getPlayers(){
    	return players;
    }
    
    public Version getVersion(){
    	return version;
    }
    
    public String getFavicon(){
    	return favicon;
    }
    
    public int getTime(){
    	return time;
    }
    
    public class Players {
        private int max;
        private int online;
        //private List<Player> sample;
        
        public Players(int max, int online, List<Player> sample){
        	this.online = online;
        	this.max = max;
        	//this.sample = sample;
        }
        
        public void setMax(int max){
        	this.max = max;
        }
        
        public void setOnline(int online){
        	this.online  = online;
        }
        
        /*public void setSample(List<Player> sample){
        	this.sample = sample;
        }*/
        
        public int getMax(){
        	return max;
        }
        
        public int getOnline(){
        	return online;
        }
        
        /*public List<Player> get(){
        	return sample;
        }*/
    }
    
    public class Player {
        private String name;
        private String id;
        
        public Player(String name, String id){
        	this.name = name;
        	this.id = id;
        }
        
        public void setName(String name){
        	this.name = name;
        }
        
        public void setID(String id){
        	this.id = id;
        }
        
        public String getName(){
        	return name;
        }
        
        public String getID(){
        	return id;
        }
    }
    
    public class Version {
        private String name;
        private String protocol;
        
        public Version(String name, String protocol){
        	this.name = name;
        	this.protocol = protocol;
        }
        
        public void setName(String name){
        	this.name = name;
        }
        
        public void setProtocol(String protocol){
        	this.protocol = protocol;
        }
        
        public String getName(){
        	return name;
        }
        
        public String getProtocol(){
        	return protocol;
        }
    }
}