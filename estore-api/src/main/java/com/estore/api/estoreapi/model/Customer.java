package com.estore.api.estoreapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Customer extends User{
    @JsonProperty("userCart") private Cart cart;
    @JsonProperty("username") private String username;
    @JsonProperty("typeUser") private boolean type;
    @JsonProperty("id") private int id; 
    /**
     * Constructor for a customer in e store 
     * @param username - username of user
     * @param type - should be  false for customer 
     * @param cart - holds  user cart 
     */
    public Customer(@JsonProperty("username") String username, @JsonProperty("typeUser") boolean type, @JsonProperty("userCart") Cart cart, @JsonProperty("id") int id) {
        super(username, type, id);
        this.cart = cart; 
        this.username = username; 
        this.type = type;    
        this.id = id; 
    }

    public Cart getUsersCart(){
        return cart; 
    }
    public void updateCart(Cart cart){
        this.cart =cart; 
    }
    
}
