package com.estore.api.estoreapi.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Customer class 
 * @Author Angela Ngo 
 */
@Tag("Model-tier")
public class CustomerTest {
    @Test
    public void testCustomerCreate(){
        ArrayList<Jersey> testJersey = new ArrayList<>(); 
        testJersey.add(new Jersey(1,"ethan abbatte", 5, 123.33, "blue", "medium", "img.png"));
        Cart cart = new Cart(testJersey,1); 
    
        String name = "peta";
        boolean type = false; 
        int id = 1; 
        Customer customer = new Customer("peta", false, cart, 1); 
    
        assertEquals(name, customer.getUsername());
        assertEquals(type, customer.getUserType());
        assertEquals(id, customer.getUserId());
        assertEquals(cart.getEntireCart().length, customer.getUsersCart().getEntireCart().length);
        
    }
}
