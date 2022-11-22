package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.ArrayList;

import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;

/**
 * Defines the interface for Customer object persistence
 * 
 * @author Vincent Schwartz
 */
public interface CustomerDAO {

    /**
     * Retrieves all {@linkplain Customer customers}
     * 
     * @return An array of {@link Customer customers} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Customer[] getCustomers() throws IOException;

    /**
     * Finds all {@linkplain Customer customers} whose username or cart match given criteria
     * 
     * @param username The username to match against
     * @param cart The cart to match against
     * 
     * @return An array of {@link Customer customers} whose usernames and/or carts match the given arguments
     * 
     * @throws IOException if an issue with underlying storage
     */
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
     * Retrieves the cart of a {@linkplain Customer customer} with the given id
     * 
     * @param id The id of the {@link Customer customer} to get
     * 
     * @return an array of {@link Jersey jerseys} objects in the customer's cart
     * <br>
     * null if no {@link Customer customer} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
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
     * Adds jersey to and saves the cart of a {@linkplain Customer customer}
     * 
     * @param {@link Customer customer} object to be updated and saved
     * @param {@link Jersey jersey} object to added
     * 
     * @return updated {@link Customer customer} if successful, null if
     * {@link Customer customer} or {@link Jersey jersey} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Customer addJerseyToCart(Customer customer, Jersey jersey) throws IOException;

    /**
     * Removes jersey from and saves the cart of a {@linkplain Customer customer}
     * 
     * @param {@link Customer customer} object to be updated and saved
     * @param {@link Jersey jersey} object to removed
     * 
     * @return updated {@link Customer customer} if successful, null if
     * {@link Customer customer} or {@link Jersey jersey} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Customer removeFromCart(Customer customer, Jersey jersey) throws IOException;

    /**
     * Empties and saves the cart of a {@linkplain Customer customer}
     * 
     * @param {@link Customer customer} object to be updated and saved
     * 
     * @return updated {@link Customer customer} if successful, null if
     * {@link Customer customer} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Customer emptyCart(Customer customer) throws IOException;

    /**
     * Retrieves a {@linkplain Customer customer} with the given id
     * 
     * @param {@link Customer customer} object to be get the total cost of
     * 
     * @return the {@link Double totalCost} totalCost of the cart
     * <br>
     * null if no {@link Customer customer} could be found
     * 
     * @throws IOException if an issue with underlying storage
     */
    double getTotalCost(Customer customer) throws IOException;


}