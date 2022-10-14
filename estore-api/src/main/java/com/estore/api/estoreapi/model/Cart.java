package com.estore.api.estoreapi.model;

import java.util.Hashtable;
import java.util.Set;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.estore.api.estoreapi.model.Jersey;

/**
 * Represents a Cart entity
 * 
 * @author Vincent Schwartz
 */

public class Cart {
    
    //will hold the jerseys in cart
        //chose dict bc we dont need ordering and it has easy access
        //Jersey is the jersey object
        //Integer is a quantity counter, how many of jersey in cart
    private static final Logger LOG = Logger.getLogger(Jersey.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Jersey [id=%d, name=%s]";
    
    @JsonProperty("jerseysDict") private Hashtable<Jersey, Integer> cart;
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
    public Cart(@JsonProperty("cart") Hashtable<Jersey, Integer> cart, 
        @JsonProperty("totalCost") double totalCost) {
        this.cart = cart;
        this.totalCost = totalCost;
    }

    public Set<Jersey> getJerseys(){
        return cart.keySet();
    }

    

    



    // /**
    //  * Given a jersey, adds said jersey to the dictionary and adds
    //  * its cost to the total cost. If jersey is already in the cart,
    //  * increase its quantity count.
    //  * @param jersey the jersey being added to cart
    //  * @return void
    //  */
    // public void addToCart(Jersey jersey)
    // {
    //     int jerseyId = jersey.getId();
    //     if(jerseysDict.contains(jerseyId))
    //     {
    //         int previousQuantity = jerseysDict.get(jersey);
    //         jerseysDict.put(jersey, previousQuantity++);
    //     }
    //     else
    //     {
    //         jerseysDict.put(jersey, 1);
    //     }

    //     double jerseyCost = jersey.getPrice();
    //     totalCost += jerseyCost;
    // }

    // /**
    //  * Given a jersey, removes one of said jersey from cart
    //  * and subtracts its price from the totalCost
    //  * @param jersey the jersey being removed from the cart
    //  * @return void
    //  */
    // public void removeFromCart(Jersey jersey)
    // {

    // }


}
