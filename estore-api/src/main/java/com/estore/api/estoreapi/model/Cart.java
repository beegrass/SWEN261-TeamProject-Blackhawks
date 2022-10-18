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
    
    @JsonProperty("cart") private HashMap<Jersey, Integer> cart;
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
    public Cart(@JsonProperty("cart") HashMap<Jersey, Integer> cart, @JsonProperty("id") int id) {
        this.cart = cart;
        this.totalCost = 0.00;
        this.id = id; 
    }

    /**
     * returns the types of Jerseys that are contained in the cart 
     * @return cart.keySet() of {@linkplain} Jersey jerseys types that are in the cart 
     */
    public Set<Jersey> getJerseys(){
        return cart.keySet();
    }

    /**
     *  returns the entire cart's contents (type of  Jersey, Quantity)
     * @return cart : the hashmap that contains the jersey and the quantity of how of each type 
     */
    public HashMap<Jersey, Integer> getEntireCart(){
        return cart; 
    }

    /**
     * Returns the total cost of the jerseys price * the quantity 
     * @return totalCost of the entire cart
     */
    public double getTotalCost(){
        totalCost = Math.round(totalCost * 100.0)/100.0;
        return totalCost; 
    }

    /**
     * Adds a jersey to cart. If already in cart, increment quantity, otherwise
     * add to cart with quantity 1.
     * Returns True if it works 
     * @param jersey - the jersey you want to add to your cart 
     * @return valid - the jersey was added or not
     */
    public boolean addJerseyToCart(Jersey jersey){
        boolean valid = true;
        if(cart.containsKey(jersey))
        {
            int quantity = cart.get(jersey);
            quantity++;
            cart.put(jersey, quantity);
        }
        else
        {
            cart.put(jersey, 1);
        }
        totalCost += jersey.getPrice();
        return valid; 
    }


    /**
     * this decrements the type of jersey from the cart and deletes the jersey from the cart entirely if it gets to 0
     * @param jersey
     * @return valid boolean
     */
    public boolean decrementJerseyTypeFromCart(Jersey jersey){
        boolean valid = false; 
        if(cart.containsKey(jersey)){
            int quantity = cart.get(jersey);
            quantity--;
            cart.put(jersey, quantity);

            totalCost -= quantity * jersey.getPrice();

            if(cart.get(jersey) == 0)
            {
                cart.remove(jersey);
            }
            valid = true;
        }
        return valid; 
    }

    /**
     * Deletes the given jersey and all of the quantities of it from the cart
     * returns true when done 
     * @param jersey
     * @return Jersey jersey
     */
    public boolean deleteJerseyType(Jersey jersey){
        boolean valid = false;
        if(cart.containsKey(jersey)){
            int quantity = cart.get(jersey);
            totalCost -= jersey.getPrice() * quantity;
            cart.remove(jersey);
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
        if(cart.isEmpty() == false){
            for(Jersey key : cart.keySet()){
                cart.remove(key); 
            }
            cartEmpty = true;
        }
        return cartEmpty; 
    }

    /**
     * This gets the total quantity of jerseys in cart 
     * @return total
     */
    public int totalJerseysInCart(){
        int total = 0;
        for(Jersey jersey: cart.keySet()){
            total += (cart.get(jersey));
        }
        return total; 
    }

    public int getId(){
        return id; 
    }

    // public static void main(String [] args){
    //     /*testing purposes */
    //     HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
    //     Cart cart = new Cart(cartTable); 
    //     Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
    //     cart.addJerseyToCart(jersey); 
    //     cart.addJerseyToCart(jersey); 
    //     cart.addJerseyToCart(jersey); 
    //     System.out.println(cart.getTotalCost());
    //     System.out.println(cart.getEntireCart().toString());
    //     boolean actual = cart.deleteEntireCart();

    //     System.out.println("result: " + actual );

    // }

}
