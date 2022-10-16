package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Set;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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

    @Test
    public void testGetJerseys(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey); 

        Set<Jersey> actual = cart.getJerseys();
        assertEquals(actual.size(),1 );

    }

    @Test 
    public void testGetEntireCartFill(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey); 
        Jersey j2 = new Jersey(2,"pail",23,123.55,"Black", "Medium", "img.png");
        cart.addNewJerseyToCart(j2);

        HashMap<Jersey,Integer> actual = cart.getEntireCart(); 
        assertEquals(actual.get(jersey), 1);
        assertEquals(actual.get(j2), 1);
        
    }

    @Test
    public void testGetTotalCostFill(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey); 
        Jersey j2 = new Jersey(2,"pail",23,123.55,"Black", "Medium", "img.png");
        cart.addNewJerseyToCart(j2);

        double actual = cart.getTotalCost(); 
        assertEquals(actual, 247.54 );
        
    }

    @Test 
    public void testAddNewJerseyTypeFromCart(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
       
        boolean actual =  cart.addNewJerseyToCart(jersey); 
    
        assertEquals(actual, true);
    }

    @Test
    public void testDecrementJerseyTypeFromCartFalse(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        
        boolean actual = cart.decrementJerseyTypeFromCart(jersey);
        assertEquals(actual, false);

    }

    @Test
    public void testDecrementJerseyTypeFromCartTrue1(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey); 

        boolean actual = cart.decrementJerseyTypeFromCart(jersey);
        assertEquals(actual, true);
        assertEquals(cart.getJerseys().size(), 0);
    }

    @Test
    public void testDecrementJerseyTypeFromCartTrue2(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey);
        cart.incrementJerseyTypeFromCart(jersey); 
        // adds 2 jerseys

        boolean actual = cart.decrementJerseyTypeFromCart(jersey);
        assertEquals(actual, true);
        assertEquals(cart.getJerseys().size(), 1);
    }

    @Test 
    public void testIncrementJerseyTypeFromCartTrue(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey);
        boolean actual = cart.incrementJerseyTypeFromCart(jersey);
        assertEquals(actual, true);
        assertEquals(cart.getEntireCart().get(jersey), 2);
        
    }

    
    @Test 
    public void testIncrementJerseyTypeFromCartFalse(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey);
        Jersey j2 = new Jersey(3, "meow man", 36, 2536.33, "Red", "Large", "img.png");
        boolean actual = cart.incrementJerseyTypeFromCart(j2);
        assertEquals(actual, false);
        assertEquals(cart.getEntireCart().get(jersey), 1);
        
    }


    @Test 
    public void testDeleteJerseyTypeTrue(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey);
        
        boolean actual = cart.deleteJerseyType(jersey);
        assertEquals(true, actual);
        
    }


    @Test 
    public void testDeleteJerseyTypeFalse(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey);
        Jersey j2 = new Jersey(3, "meow man", 36, 2536.33, "Red", "Large", "img.png");
        
        boolean actual = cart.deleteJerseyType(j2);
        assertEquals(false, actual);
        
    }

    @Test
    public void testDeleteEntireCart(){
        HashMap<Jersey, Integer> cartTable = new HashMap<>(); 
        Cart cart = new Cart(cartTable); 
        Jersey jersey = new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        cart.addNewJerseyToCart(jersey); 
        boolean actual = cart.deleteEntireCart();
        
      
        assertEquals(true, actual);
        assertEquals(cart.getTotalCost(), 0.00);
    }
    
}
