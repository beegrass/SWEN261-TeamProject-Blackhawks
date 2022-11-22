package com.estore.api.estoreapi.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Customer entity
 * NOTE: deleted logger cause it was an unused import 
 * @author Vincent Schwartz
 */

public class Customer {
  
    
    @JsonProperty("id") private int id;
    @JsonProperty("username") private String username;
    @JsonProperty("cart") private ArrayList<Jersey> cart;

    /**
     * Create a customer with the given username
     * @param id The id of the customer
     * @param username The username of the customer
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields. If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Customer(@JsonProperty("id") int id, @JsonProperty("username") String username) {
        this.id = id;
        this.username = username;
        this.cart = new ArrayList<Jersey>();
    }

    /**
     * Retrieves the id of the customer
     * @return The id of the customer
     */
    public int getId() {return id;}

    /**
     * Retrieves the username of the customer
     * @return The username of the customer
     */
    public String getUserName() {return username;}

    /**
     * Retrieves the cart of the customer
     * @return The cart of the customer
     */
    public ArrayList<Jersey> getCart() {return cart;}

    /**
     * Adds a jersey to the cart of the user
     * @param jersey The jersey to place in cart
     * 
     */
    public void addToCart(Jersey jersey) {cart.add(jersey);}

    /**
     * Removes a jersey from the cart of the user
     * @param jersey The jersey to remove from cart
     */
    public void removeFromCart(Jersey jersey) {
        int countOfRemovedJersey= 0; 
        ArrayList<Jersey> newCart = new ArrayList<Jersey>();
        for(int i = 0; i < cart.size(); i++){
            int jerseyId = cart.get(i).getId(); 
            if(jerseyId != jersey.getId() || countOfRemovedJersey == 1){ // this is a brute force way 
                newCart.add(cart.get(i)); 
            }else{
                countOfRemovedJersey = 1; 
            }
        }

        cart = newCart; 
        
        System.out.println("this is the cart after removing from the cart  : "); 
        System.out.println(cart); 
    }

    /**
     * Removes all jerseys from the cart of the user
     */
    public void emptyCart() {
        cart = new ArrayList<Jersey>();
    }

    /**
     * Gets the total cost of the customers cart
     * @return totalCost double 
     */
    public double getTotalCost() {
        double totalCost = 0;
        for(int i = 0; i < cart.size(); i++){
            totalCost += cart.get(i).getPrice();
        }
        return totalCost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.username;
    }






}