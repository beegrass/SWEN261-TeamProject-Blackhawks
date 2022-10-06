package com.estore.api.estoreapi.model;

import java.util.Hashtable;

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
    private Hashtable<Jersey, Integer> jerseysDict;

    //will keep track of the total cost of the cart
    private double totalCost;

    public Cart()
    {
        jerseysDict = new Hashtable<Jersey, Integer>();
        totalCost = 0.0;
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
