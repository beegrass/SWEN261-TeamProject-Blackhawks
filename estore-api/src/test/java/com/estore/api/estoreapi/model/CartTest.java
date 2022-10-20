package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit test suite for Cart class
 * @Author Angela Ngo and Vincent Schwartz
 */
@Tag("Model-tier")
public class CartTest {
    
    @Test
    public void testGetEntireCart(){
       ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
       Cart cart = new Cart(jerseyArray,1); 
       assertEquals(cart.getEntireCart().length, 0); 
    }

    @Test
    public void testGetTotalCost(){
        ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
        Cart cart = new Cart(jerseyArray,1); 
        assertEquals(cart.getTotalCost(), 0);  
    }

    @Test
    public void testGetEntireCartP1(){
        ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
        Cart cart = new Cart(jerseyArray,1); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addJerseyToCart(jersey); 
        Jersey [] actual = cart.getEntireCart(); 
        assertEquals(actual.length, 1);


    }

    @Test
    public void testGetQuantity(){
        ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
        Cart cart = new Cart(jerseyArray,1); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addJerseyToCart(jersey); 
        int actual = cart.getQuantity(jersey);
        assertEquals(1, actual);
    }

    @Test
    public void testGetTotalCostFill(){
        ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
        Cart cart = new Cart(jerseyArray,1); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        Jersey jersey1 = new Jersey(2,"pail",23,123.55,"Black", "Medium", "img.png");
    
        cart.addJerseyToCart(jersey); 
        cart.addJerseyToCart(jersey1); 
        double actual  = cart.getTotalCost();
        assertEquals(247.54, actual);
    }

    @Test 
    public void testAddNewJerseyTypeFromCart(){
        ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
        Cart cart = new Cart(jerseyArray,1); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
       
        cart.addJerseyToCart(jersey); 
        int actual = cart.getEntireCart().length;
        assertEquals(1, actual);
        
    }

    @Test
    public void testDecrementJerseyTypeFromCartTrue(){
        ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
        Cart cart = new Cart(jerseyArray,1); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        
        cart.addJerseyToCart(jersey);
        cart.addJerseyToCart(jersey);  
        cart.decrementJerseyTypeFromCart(jersey.getId());
        int actual = cart.getEntireCart().length;
        assertEquals(1, actual);

    }

    @Test
    public void testDecrementJerseyTypeFromCartFalse(){
        ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
        Cart cart = new Cart(jerseyArray,1); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addJerseyToCart(jersey); 
        Jersey jersey1 = new Jersey(2,"pail",23,123.55,"Black", "Medium", "img.png");
    
        boolean actual = cart.decrementJerseyTypeFromCart(jersey1.getId());
        assertEquals(actual, false);
        assertEquals(cart.getEntireCart().length, 1);
    }

  
    


    @Test 
    public void testDeleteJerseyTypeTrue(){
        ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
        Cart cart = new Cart(jerseyArray,1); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addJerseyToCart(jersey);
        cart.addJerseyToCart(jersey); 

        Jersey jersey1 = new Jersey(2,"pail",23,123.55,"Black", "Medium", "img.png");
        cart.addJerseyToCart(jersey1);
        
        boolean actual = cart.deleteJerseyType(jersey);
        assertEquals(true, actual);
        assertEquals(1,cart.totalJerseysInCart());
        
    }

    @Test
    public void testDeleteEntireCart(){
        ArrayList<Jersey> jerseyArray = new ArrayList<Jersey>(); 
        Cart cart = new Cart(jerseyArray,1); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addJerseyToCart(jersey);
        cart.addJerseyToCart(jersey); 

        Jersey jersey1 = new Jersey(2,"pail",23,123.55,"Black", "Medium", "img.png");
        cart.addJerseyToCart(jersey1);
        
        boolean actual = cart.deleteEntireCart();
        assertEquals(true, actual);
        assertEquals(0, cart.totalJerseysInCart());
    }


}
