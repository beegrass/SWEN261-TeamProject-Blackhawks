package com.estore.api.estoreapi.model;

/**
 * @author Ethan Abbate, Angela Ngo 
 * Parent class for Customer and Admin 
 */

public class User {
    private String username; 
    private boolean type; 
    private int id; 

    public User(String username, boolean type, int id){
        this.type = type; 
        this.username = username; 
        this.id = id; 
    }

    public String getUsername(){
        return username;
    }
    
    public boolean getUserType(){
        return type; 
    }

    public int getUserId(){
        return id; 
    }
   
}
