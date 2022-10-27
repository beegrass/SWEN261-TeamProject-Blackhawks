package com.estore.api.estoreapi.model;

/**
 * @author Ethan Abbate, Angela Ngo 
 * Interface for Customer and Admin 
 */
public abstract class User {
    private String username; 
    private boolean type; 

    public User(String username, boolean type){
        this.type = type; 
        this.username = username; 
    }

    public String getUsername(){
        return username;
    }
    
    public boolean getUserType(){
        return type; 
    }
   
}
