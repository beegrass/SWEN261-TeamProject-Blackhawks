package com.estore.api.estoreapi.model;

public class Item {
    private int quantity;
    private Jersey jersey; 

    public Item(Jersey jerseyType, int quantity){
        this.quantity = quantity; 
        this.jersey = jerseyType; 
    }

    public int getQuantity(){
        return quantity; 
    }

    public Jersey getJersey(){
        return jersey; 
    }
}
