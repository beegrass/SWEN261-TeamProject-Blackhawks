package com.estore.api.estoreapi.persistence;

import java.io.IOException;


import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Jersey;

/**
 * Defines the interface for Jersey object persistence
 * 
 * @author Angela Ngo and Vincent Schwartz, Angela Ngo Schwartz
 */


public interface CartDAO {
    
    /**
     * Retrieves all {@linkplain Jersey jersey's}
     * 
     * @return An array of {@link Jersey jerseys} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Jersey[] getJerseysFromCart(int cartId) throws IOException;

    /**
     * Decremements the amount of {@linkplain Jersey jersey} with the given jersey object in 
     * cart's map 
     * 
     * @param jersey the key of the object decremented {@link Jersey jersey}
     * 
     * @return Cart if the {@link Jersey jersey} was successfully decremented in the arraylist
     * <br>
     * false if jersey with the given object does not exist in the Cart 
     * 
     * @throws IOException if there is no Jersey objects in the Cart
     * */
    Cart decrementJerseyTypeAmount(int cartId, Jersey jersey) throws IOException; 

    /**
     * Adds a new {@linkplain Jersey jersey} key into the cart's arraylist  
     * 
     * @param jersey the jersey wanted to put into array in {@link Cart cart}
     * 
     * @return Cart object if the {@link Jersey jersey} was successfully incremented 
     * <br>
     * false if jersey with the given object does not exist
     * 
     * @throws IOException if there null fields in the Jersey parameter 
     * */
    Cart addJerseyToCart(int cartId, Jersey jersey) throws IOException; 

    /**
     * deletes the jersey{@linkplain Jersey jersey} key and its associated number of values from the cart 
     * 
     * @param jersey the jersey wanted to entirely remove from the arraylist in {@link Cart cart}
     * 
     * @return Cart if all instances of {@link Jersey jersey} was successfully deleted 
     * 
     * @throws IOException if jersey with the given object does not exist
     * */
    Cart deleteEntireJerseyFromCart(int cartId, Jersey jersey) throws IOException;

    /**
     * deletes all jerseys from the carts array list 
     * @return Cart (empty) if the contents of Cart was successfully entirely deleted 
     * 
     * @throws IOException if Cart is empty
     * */
    Cart deleteEntireCart(int cartId) throws IOException; 

    /**
     * Creates a new cart for a user 
     * @param cart the cart that we want to add to the json file 
     * @return Cart if it was successfully created null if otherwise
     * @throws IOException if there was an issue with the cart object
     */
    Cart createNewCart(Cart cart) throws IOException; 

    /**
     * retrieves a specific cart from the hashmap in cartfileDAO
     * @param cartId
     * @return
     * @throws IOException if the cart doesnt exist in the 
     */
    Cart getSpecificCart(int cartId) throws IOException; 
    // /**
    //  * deletes the jersey{@linkplain Jersey jersey} key and its associated number of values from the cart 
    //  * 
    //  * @param jersey the key to delete from cart{@link Jersey jersey}
    //  * 
    //  * @return true if the {@link Jersey jersey} was successfully deleted 
    //  * 
    //  * @throws IOException if jersey with the given object does not exist
    //  * */
    // int getTotalCountJerseys(HashMap<Jersey, Integer> cart);
    
    // int getTotalCostJerseys(HashMap<Jersey, Integer> cart);
}
