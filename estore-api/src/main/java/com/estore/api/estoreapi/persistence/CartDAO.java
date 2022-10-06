package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.Set;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Jersey;

/**
 * Defines the interface for Jersey object persistence
 * 
 * @author Vincent
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

    

}
