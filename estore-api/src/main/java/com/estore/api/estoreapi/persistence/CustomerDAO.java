package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;

/**
 * Defines the interface for Customers object persistance
 * @author Angela Ngo 
 */

 public interface CustomerDAO{
    //get cart ? I think thats it

    /**
     * gets the cart of the customer 
     * @param userId - the user id that has a cart 
     * @return Cart if it exists, null if it doesnt 
     */
    Cart getCart(int userId);

    /**
     * gets the specific customer from json file 
     * @param userId - of the user 
     * @return Customer is it exists, null if it doesnt 
     * @throws IOException 
     */
    Customer getSpecificCustomer(String username) throws IOException; 

    /**
     * 
     * @param customer - the customer that we want to create
     * @return the created customer 
     * @throws IOException
     */
    Customer createNewCustomer(Customer customer)throws IOException; 

    /**
     * allows customer to add jersey to cart 
     * @param userId - the  user id to get the specific customer 
     * @param jersey - the jersey that you want to add to the cart
     * @return customer if added null if not 
     * @throws IOException
     */
    Customer addToCart(int userId, Jersey jersey) throws IOException;

    /**
     * deletes the entire jersey type from the cart 
     * @param userId - the user id of the specific customer 
     * @param jersey - the jersey that you want to add to the cart
     * @return customer if added null if not found 
     * @throws IOException
     */
    Customer deleteEntireJerseyFromCart(String username, Jersey jersey) throws IOException;

    /**
     * decrements the jersey type amount from the customers cart 
     * @param userId - the user id of the specific customer  
     * @param jersey - the jersey that you want to add to the cart
     * @return customer if added null if not found 
     * @throws IOException
     */
    Customer decrementJerseyTypeAmount(int userId, Jersey jersey) throws IOException;

    /**
     * delete the entire contents of the cart from the customers cart 
     * @param userId - the user id of the specific customer  
     * @return customer if added null if not found 
     * @throws IOException
     */
    Customer deleteEntireCart(int userId) throws IOException;

}