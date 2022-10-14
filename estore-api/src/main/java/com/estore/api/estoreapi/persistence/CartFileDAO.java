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

    HashMap<Jersey,Integer> cart;   // Provides a local cache of the jersey objects
    // so that we don't need to read from the file
    // each time
   
    public CartFileDAO() throws IOException {
       cart = new HashMap<Jersey, Integer>();
    }

    @Override
    public HashMap<Jersey, Integer> getJerseysInCart() throws IOException {
        return cart; 
    }

    @Override
    public boolean incrementJerseyTypeAmount(Jersey jersey) throws IOException {
        boolean valid = false; 
        if(cart.containsKey(jersey) == true){
            cart.put(jersey, cart.get(jersey) + 1);
            valid = true; 
        }
        return valid; 
    }

    @Override
    public boolean decrementJerseyTypeAmount(Jersey jersey) {
        boolean valid = false; 
        if(cart.containsKey(jersey) == true){
            int newAmount = cart.get(jersey) -1;

            if(newAmount <= 0){
                cart.remove(jersey); 
            }else{
                cart.put(jersey, newAmount);
            } 
            valid = true; 
        }
        return valid; 
    }

    @Override
    public boolean addJerseyToCart(Jersey jersey) {
       cart.put(jersey, 1);
       return true; 
    }

    @Override
    public boolean deleteEntireJerseyFromCart(Jersey jersey) {
        boolean valid = false;
        if(cart.containsKey(jersey) == true){
            cart.remove(jersey);
            valid = true;
        }
        return valid; 
    }

    @Override
    public boolean deleteEntireCart() {
        boolean cart_empty = true; 
        if(cart.isEmpty() == false){
            for(Jersey key : cart.keySet()){
                cart.remove(key); 
            }
            cart_empty = true; 
        }
        return cart_empty; 
    }

}