package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.ArrayList;

import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;

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

    Customer getCustomer(int username) throws IOException;
    /**
     * Creates and saves a {@linkplain Customer customer}
     * 
     * @param customer {@linkplain Customer customer} object to be created and saved
     * <br>
     * The id of the customer object is ignored and a new uniqe id is assigned
     *
     * @return new {@link Customer customer} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Customer createCustomer(Customer customer) throws IOException;

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
    

    ArrayList<Jersey> addJerseyToCart(Customer customer, Jersey jersey) throws IOException;

    ArrayList<Jersey> removeFromCart(Customer customer, Jersey jersey) throws IOException;

    ArrayList<Jersey> emptyCart(Customer customer) throws IOException;

    double getTotalCost(Customer customer) throws IOException;


}
