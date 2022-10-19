// package com.estore.api.estoreapi.persistence;

// import java.io.IOException;
// import java.util.HashMap;

// import com.estore.api.estoreapi.model.Cart;
// import com.estore.api.estoreapi.model.Jersey;

// /**
//  * Defines the interface for Jersey object persistence
//  * 
//  * @author Vincent Schwartz, Angela Ngo
//  */


// public interface CartDAO {
    
//     /**
//      * Retrieves all {@linkplain Jersey jersey's}
//      * 
//      * @return A set of {@link Jersey jerseys} objects, may be empty
//      * 
//      * @throws IOException if an issue with underlying storage
//      */
//     HashMap<Jersey, Integer> getJerseysInCart() throws IOException;

//     /**
//      * Decremements the amount of {@linkplain Jersey jersey} with the given jersey object in 
//      * cart's map 
//      * 
//      * @param jersey the key of the object incremented {@link Jersey jersey}
//      * 
//      * @return HashMap<Jersey, Integer> if the {@link Jersey jersey} was successfully decremented in the HashMap 
//      * <br>
//      * false if jersey with the given object does not exist in the Hashmap Keys 
//      * 
//      * @throws IOException if there is no Jersey objects in the HashMap keys
//      * */
//     boolean decrementJerseyTypeAmount(Jersey jersey); 

//     /**
//      * Adds a new {@linkplain Jersey jersey} key into the cart's Hashmap 
//      * 
//      * @param jersey the new key of the HashMap{@link Jersey jersey}
//      * 
//      * @return HashMap<Jersey, Integer> if the {@link Jersey jersey} was successfully incremented 
//      * <br>
//      * false if jersey with the given object does not exist
//      * 
//      * @throws IOException if there null fields in the Jersey parameter 
//      * */
//     boolean addJerseyToCart(Jersey jersey); 

//     /**
//      * deletes the jersey{@linkplain Jersey jersey} key and its associated number of values from the cart 
//      * 
//      * @param jersey the key to delete from cart{@link Jersey jersey}
//      * 
//      * @return true if the {@link Jersey jersey} was successfully deleted 
//      * 
//      * @throws IOException if jersey with the given object does not exist
//      * */
//     boolean deleteEntireJerseyFromCart(Jersey jersey);

//     /**
//      * deletes all jerseys from the Cart {@link HashMap<Jersey, Integer> Cart}
//      * @return true if the contents of Cart was successfully entirely deleted 
//      * 
//      * @throws IOException if Cart is empty
//      * */
//     boolean deleteEntireCart() throws IOException; 

//     // /**
//     //  * deletes the jersey{@linkplain Jersey jersey} key and its associated number of values from the cart 
//     //  * 
//     //  * @param jersey the key to delete from cart{@link Jersey jersey}
//     //  * 
//     //  * @return true if the {@link Jersey jersey} was successfully deleted 
//     //  * 
//     //  * @throws IOException if jersey with the given object does not exist
//     //  * */
//     // int getTotalCountJerseys(HashMap<Jersey, Integer> cart);
    
//     // int getTotalCostJerseys(HashMap<Jersey, Integer> cart);
// }
