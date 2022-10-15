package com.estore.api.estoreapi.model;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * Represents a Cart entity
 * 
 * @author Angela Ngo 
 */

public class Cart {
    
    //will hold the jerseys in cart
        //chose dict bc we dont need ordering and it has easy access
        //Jersey is the jersey object
        //Integer is a quantity counter, how many of jersey in cart
    private static final Logger LOG = Logger.getLogger(Jersey.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Jersey [id=%d, name=%s]";
    
    @JsonProperty("jerseysDict") private HashMap<Jersey, Integer> cart;
    @JsonProperty("totalCost") private double totalCost;
    
    /**
     * Create a jersey with the given name, number, price, color, and image path
     * @param id The id of the jersey
     * @param name The name that goes on the jersey
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Cart(@JsonProperty("cart") HashMap<Jersey, Integer> cart) {
        this.cart = cart;
        this.totalCost = 0.00;
    }

    public Set<Jersey> getJerseys(){
        return cart.keySet();
    }

    public HashMap<Jersey, Integer> getEntireCart(){
        return cart; 
    }
    public double getTotalCost(){
        for(Jersey jersey: cart.keySet()){
            int quantity_jersey_type = cart.get(jersey); 
            totalCost += (jersey.getPrice() * quantity_jersey_type); 
        }
        return totalCost; 
    }

    public boolean addNewJerseyToCart(Jersey jersey){
        cart.put(jersey, 1); 
        return true; 
    }

    public boolean decrementJerseyTypeFromCart(Jersey jersey){
        boolean valid = false; 
        if(cart.containsKey(jersey) == true){
            int newAmount = cart.get(jersey) -1;

            if(newAmount <= 0){
                cart.remove(jersey); 
            }else{
                cart.put(jersey, newAmount);
            } 
            valid = true; 
        }
        return valid; 
    }

    public boolean incrementJerseyTypeFromCart(Jersey jersey){
        boolean valid = false; 
        if(cart.containsKey(jersey) == true){
            cart.put(jersey, cart.get(jersey) + 1);
            valid = true; 
        }
        return valid;  
    }

    public boolean deleteJerseyType(Jersey jersey){
        boolean valid = false;
        if(cart.containsKey(jersey) == true){
            cart.remove(jersey);
            valid = true;
        }
        return valid; 
    }

    public boolean deleteEntireCart(){
        boolean cart_empty = false; 
        if(cart.isEmpty() == false){
            for(Jersey key : cart.keySet()){
                cart.remove(key); 
            }
            cart_empty = true;
        }
        return cart_empty; 
    }


    public static void main(String [] args){
        /*testing purposes */
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey); 
        boolean actual = cart.deleteEntireCart();
        

        System.out.println("result: " + actual );

    }

}
