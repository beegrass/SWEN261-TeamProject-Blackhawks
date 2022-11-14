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
    public void removeFromCart(Jersey jersey) {cart.remove(jersey);}

    /**
     * Removes all jerseys from the cart of the user
     */
    public void emptyCart() {cart.clear();}

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
