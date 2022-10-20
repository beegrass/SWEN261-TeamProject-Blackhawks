package com.estore.api.estoreapi.model;


import java.util.ArrayList;
import java.util.List;
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
    
    @JsonProperty("cartArray") private List<Jersey> cart;
    @JsonProperty("totalCost") private double totalCost;
    @JsonProperty("id") private int id;
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
    public Cart(@JsonProperty("cartArray") List<Jersey> cart, @JsonProperty("id") int id) {
        this.cart = cart;
        this.totalCost = 0.00;
        this.id = id; 
    }

    /**
     * returns the jerseys that are contained in the cart 
     * @return cart.keySet() of {@linkplain} Jersey jerseys types that are in the cart 
     */
    public Jersey[] getEntireCart(){
        Jersey [] returned = new Jersey[cart.size()];
        cart.toArray(returned);
        return returned;  
    }

    /**
     * Returns the amount of given jersey in cart 
     * @param jersey
     * @return count - amount of given jersey
     */
    public int getQuantity(Jersey jersey){
        int count = 0;
        for(int i = 0; i < cart.size(); i++){
            if(cart.get(i).getId() == jersey.getId()){
                count++;
            }
        }
        return count;
   }
    /**
     * Returns the total cost of the the entire cart
     * @return totalCost of the entire cart
     */
    public double getTotalCost(){
        double total = 0;
        for(int i = 0 ; i < cart.size(); i++){
            total+= cart.get(i).getPrice(); 
        }

        total = Math.round(total * 100.0)/100.0;
        totalCost = total; 
        return total; 
    }

    /**
     * Adds a jersey to cart. If already in cart, increment quantity, otherwise
     * add to cart with quantity 1.
     * Returns True if it works 
     * @param jersey - the jersey you want to add to your cart 
     * @return valid - the jersey was added or not
     */
    public boolean addJerseyToCart(Jersey jersey){
        cart.add(jersey); 
        return true; 
    }


    /**
     * this decrements the type of jersey from the cart and deletes the jersey from the cart entirely if it gets to 0
     * @param jersey
     * @return valid boolean
     */
    public boolean decrementJerseyTypeFromCart(Jersey jersey){
        boolean valid = false;
        if(cart.contains(jersey) == true && getQuantity(jersey) > 0){
            cart.remove(jersey); 
            totalCost = getTotalCost(); 
            valid = true; 
        }
        return valid; 
    }

    /**
     * Deletes the given jersey and all of the quantities of it from the cart
     * returns true when done 
     * @param jersey
     * @return valid 
     */
    public boolean deleteJerseyType(Jersey jersey){
        boolean valid = false;
        if(cart.contains(jersey) == true){
            while(cart.contains(jersey) == true){
                cart.remove(jersey);
            }
            //totalCost = getTotalCost(); 
            valid = true;
        }

        return valid; 
    }

    /**
     * Deletes every single jersey and its quantities from the HashMap cart
     * @return cartEmpty returns true if works false if not 
     */
    public boolean deleteEntireCart(){
        boolean cartEmpty = false;
        if(cart.size() > 0){
            cart.removeAll(cart); 
            totalCost = 0.00; 
            cartEmpty = true; 
        } 
        return cartEmpty; 
    }

    /**
     * This gets the total quantity of jerseys in cart 
     * @return total
     */
    public int totalJerseysInCart(){
        return cart.size();  
    }

    public int getId(){
        return id; 
    }

    public static void main(String [] args){
        /*testing purposes */
        ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
        Cart cart = new Cart(jerseyArray,1); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addJerseyToCart(jersey); 
        cart.addJerseyToCart(jersey); 
        cart.addJerseyToCart(jersey); 
      
        boolean actual =cart.deleteJerseyType(jersey); 
        System.out.println("cart:" + cart.getQuantity(jersey));
        System.out.println("result: " + actual );

    }

}
