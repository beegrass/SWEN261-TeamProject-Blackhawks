package com.estore.api.estoreapi.model;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
    private static final Logger LOG = Logger.getLogger(Jersey.class.getName());
    
    @JsonProperty("id") private int id;
    @JsonProperty("username") private String username;
    @JsonProperty("cart") private ArrayList<Jersey> cart;

    public Customer(@JsonProperty("id") int id, @JsonProperty("username") String username) {
        this.id = id;
        this.username = username;
        this.cart = new ArrayList<Jersey>();
    }

    // public Customer(Customer customer) {
    //     this.id = customer.getId();
    //     this.username = customer.getUserName();
    //     this.cart.addAll(customer.getCart());
    // }

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
        //cart.remove(jersey);
        int countOfRemovedJersey= 0; 
        // BUG: it removes it but it removes ALL cases of that one jersey 
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

    public double getTotalCost() {
        double totalCost = 0;
        for(int i = 0; i < cart.size(); i++){
            totalCost += cart.get(i).getPrice();
        }
        return totalCost;
    }

    @Override
    public String toString() {
        return this.username;
    }






}
