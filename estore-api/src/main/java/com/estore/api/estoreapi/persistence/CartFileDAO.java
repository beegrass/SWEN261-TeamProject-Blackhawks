package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Jersey;

/**
 * Implements the functionality for JSON file-based peristance for Cart
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Angela Ngo
 */
public class CartFileDAO implements CartDAO {

    private Cart cart;   // Provides a local cache of the jersey objects 
    // so that we don't need to read from the file
    // each time
   
    public CartFileDAO(Cart cart) throws IOException {
       this.cart = cart;
    }

    @Override
    public HashMap<Jersey, Integer> getJerseysInCart() throws IOException {
        return cart.getEntireCart(); 
    }

    @Override
    public boolean decrementJerseyTypeAmount(Jersey jersey) {
        boolean is_decremented = cart.decrementJerseyTypeFromCart(jersey);
        return is_decremented;  
    }

    @Override
    public boolean addJerseyToCart(Jersey jersey) {
       boolean is_added = cart.addJerseyToCart(jersey); 
       return is_added; 
    }

    @Override
    public boolean deleteEntireJerseyFromCart(Jersey jersey) {
        boolean is_deleted = cart.deleteJerseyType(jersey); 
        return is_deleted; 
    }

    @Override
    public boolean deleteEntireCart() throws IOException {
       boolean cart_empty = cart.deleteEntireCart(); 
       return cart_empty; 
    }

}