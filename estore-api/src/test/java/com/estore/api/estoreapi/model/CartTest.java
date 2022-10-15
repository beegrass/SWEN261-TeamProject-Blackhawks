package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.controller.Tag;

/**
 * Unit test suite for Cart class
 * @Author Angela Ngo 
 */
@Tag("Model-tier")
public class CartTest {
    
    @Test
    public void testGetEntireCart(){
       HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
       Cart cart = new Cart(cartTable); 
       assertEquals(cart.getEntireCart(), cartTable); 
    }

    @Test
    public void testGetTotalCost(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 

        assertEquals(cart.getTotalCost(), 0.00); 
    }
}
