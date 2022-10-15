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
    private HashMap<Jersey, Integer> cartMap; 
    // so that we don't need to read from the file
    // each time
   
    public CartFileDAO(Cart cart) throws IOException {
       this.cart = cart;
       cartMap = cart.getEntireCart(); 
    }

    @Override
    public HashMap<Jersey, Integer> getJerseysInCart() throws IOException {
        return cart.getEntireCart(); 
    }

    @Override
    public boolean incrementJerseyTypeAmount(Jersey jersey){
        boolean valid = false; 
        if(cartMap.containsKey(jersey) == true){
            cartMap.put(jersey, cartMap.get(jersey) + 1);
            valid = true; 
        }
        return valid; 
    }

    @Override
    public boolean decrementJerseyTypeAmount(Jersey jersey) {
        boolean valid = false; 
        if(cartMap.containsKey(jersey) == true){
            int newAmount = cartMap.get(jersey) -1;

            if(newAmount <= 0){
                cartMap.remove(jersey); 
            }else{
                cartMap.put(jersey, newAmount);
            } 
            valid = true; 
        }
        return valid; 
    }

    @Override
    public boolean addJerseyToCart(Jersey jersey) {
       cartMap.put(jersey, 1);
       return true; 
    }

    @Override
    public boolean deleteEntireJerseyFromCart(Jersey jersey) {
        boolean valid = false;
        if(cartMap.containsKey(jersey) == true){
            cartMap.remove(jersey);
            valid = true;
        }
        return valid; 
    }

    @Override
    public boolean deleteEntireCart() throws IOException {
        boolean cart_empty = true; 
        if(cartMap.isEmpty() == false){
            for(Jersey key : cartMap.keySet()){
                cartMap.remove(key); 
            }
            cart_empty = true; 
        }
        return cart_empty; 
    }

}