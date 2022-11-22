package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Customer class
 * 
 * @author Vincent Schwartz
 */
@Tag("Model-tier")
public class CustomerTest {
    @Test
    public void testControllerWithCart() {
        // Setup
        int expected_id = 99;
        String expected_username = "Tony Audi";
        ArrayList<Jersey> expected_cart = new ArrayList<>();

        // Invoke
        Customer customer = new Customer(expected_id, expected_username);

        // Analyze
        assertEquals(expected_id,customer.getId());
        assertEquals(expected_username,customer.getUserName());
        assertEquals(expected_cart,customer.getCart());
    }

    @Test
    public void testAddToCart() {
        // Setup
        Jersey jersey = new Jersey(13, "Teehee", 69, 420.99, "Red", "S", "assets/tony.jpg");
        int expected_id = 99;
        String expected_username = "Tony Audi";
        ArrayList<Jersey> expected_cart = new ArrayList<>();
        expected_cart.add(jersey);
        Customer customer = new Customer(expected_id, expected_username);

        // Invoke
        customer.addToCart(jersey);
        ArrayList<Jersey> actual_cart = customer.getCart();

        // Analysis
        assertEquals(expected_cart, actual_cart);
        
    }

    // @Test
    // public void testRemoveFromCart() {
    //     // Setup
    //     Jersey jersey1 = new Jersey(13, "Teehee", 69, 420.99, "Red", "S", "assets/tony.jpg");
    //     Jersey jersey2 = new Jersey(13, "Teehee", 69, 420.99, "Red", "S", "assets/tony.jpg");
    //     int expected_id = 99;
    //     String expected_username = "Tony Audi";
    //     ArrayList<Jersey> expected_cart = new ArrayList<>();
    //     expected_cart.add(jersey1);

    //     Customer customer = new Customer(expected_id, expected_username);
    //     customer.addToCart(jersey1);
    //     customer.addToCart(jersey2);

    //     // Invoke
    //     customer.removeFromCart(jersey2);
    //     ArrayList<Jersey> actual_cart = customer.getCart();

    //     // Analysis
    //     assertTrue(expected_cart.equals(actual_cart));
    // }

    @Test
    public void testEmptyCart() {
        // Setup
        Jersey jersey1 = new Jersey(13, "Teehee", 69, 420.99, "Red", "S", "assets/tony.jpg");
        Jersey jersey2 = new Jersey(13, "Teehee", 69, 420.99, "Red", "S", "assets/tony.jpg");
        int expected_id = 99;
        String expected_username = "Tony Audi";
        ArrayList<Jersey> expected_cart = new ArrayList<>();

        Customer customer = new Customer(expected_id, expected_username);
        customer.addToCart(jersey1);
        customer.addToCart(jersey2);

        // Invoke
        customer.emptyCart();
        ArrayList<Jersey> actual_cart = customer.getCart();

        // Analysis
        assertEquals(expected_cart, actual_cart);
    }
}
