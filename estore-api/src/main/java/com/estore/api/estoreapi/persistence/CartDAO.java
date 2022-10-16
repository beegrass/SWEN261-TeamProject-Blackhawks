package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.Set;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Jersey;

/**
 * Defines the interface for Jersey object persistence
 * 
 * @author Angela Ngo and Vincent Schwartz
 */


public interface CartDAO {
    
    /**
     * Retrieves all {@linkplain Jersey jersey's}
     * 
     * @return A set of {@link Jersey jerseys} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Set<Jersey> getJerseysInCart() throws IOException;

    /**
     * Deletes a {@linkplain Jersey jersey} with the given id
     * 
     * @param id The id of the {@link Jersey jersey}
     * 
     * @return true if the {@link Jersey jersey} was deleted
     * <br>
     * false if jersey with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean removeJerseyFromCart(int id) throws IOException;

    /**
     * Creates and saves a {@linkplain Jersey jersey}
     * 
     * @param jersey {@linkplain Jersey jersey} object to be created and saved
     * <br>
     * The id of the jersey object is ignored and a new uniqe id is assigned
     *
     * @return new {@link Jersey jersey} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Jersey addJerseyToCart(Jersey jersey) throws IOException;

}
