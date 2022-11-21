package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.ArrayList;

import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;
/**
 * @Author Angela and Vince
 */
public interface CustomerDAO {
    /**
     * Retrieves all {@linkplain Jersey jersey's}
     * 
     * @return An array of {@link Jersey jerseys} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Customer[] getCustomers() throws IOException;

    Customer[] findCustomers(String username, ArrayList<Jersey> cart) throws IOException;

    /**
     * Retrieves a {@linkplain Customer customer} with the given id
     * 
     * @param id The id of the {@link Customer customer} to get
     * 
     * @return a {@link Customer customer} object with the matching id
     * <br>
     * null if no {@link Customer customer} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */

    Customer getCustomer(int id) throws IOException;

    /**
     * Returns customers cart 
     * @param id - customers id 
     * @return Jersey[] - returns customers cart
     */
    Jersey[] getCart(int id) throws IOException;
    
    /**
     * Creates and saves a {@linkplain Customer customer}
     * 
     * @param customer {@linkplain Customer customer} object to be created and saved
     * <br>
     * The id of the customer object is ignored and a new uniqe id is assigned
     *
     * @return new {@link Customer customer} if successful, null otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Customer createCustomer(Customer customer) throws IOException;

  
    
    /**
     * Adds a jersey to given customers cart 
     * @param customer - the customer object wanting to add jersey to their cart 
     * @param jersey - the jersey wanted to put into cart
     * @return Customer object if successful, null if not 
     * @throws IOException if an issue with underlying storage
     */
    Customer addJerseyToCart(Customer customer, Jersey jersey) throws IOException;

    /**
     * Removes a jersey from a given customers cart 
     * @param customer - the customer object wanting to remove from  
     * @param jersey - the jersey wanting to remove from cart 
     * @return Customer object - if successful, null if not 
     * @throws IOException - if there is an issue with save() or load() internal server error 
     */
    Customer removeFromCart(Customer customer, Jersey jersey) throws IOException;

    /**
     * This removes all the jerseys from the customers cart 
     * @param customer 9 the customer that wants to remove all jerseys from their cart 
     * @return customer if successful, null if not found 
     * @throws IOException if an issue with underlying storage
     */
    Customer emptyCart(Customer customer) throws IOException;

    /**
     * given a customer, it gets the total cost of the customer's cart and returns it 
     * @param customer - the customer you want to get total cost of their cart 
     * @return double - the total cost of the cart from customer , null if not 
     * @throws IOException - if there is an issue with underlying storage 
     */
    double getTotalCost(Customer customer) throws IOException;


}
