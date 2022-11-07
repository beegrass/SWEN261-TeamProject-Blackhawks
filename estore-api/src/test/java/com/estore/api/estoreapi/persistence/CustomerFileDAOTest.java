package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.propertyeditors.CustomMapEditor;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;
import com.fasterxml.jackson.databind.ObjectMapper;

@Tag("Persistence-tier")
public class CustomerFileDAOTest {
    CustomerFileDAO customerFileDAO; 
    Customer [] testCustomers = new Customer[2];
    Cart [] testCarts; 
    ObjectMapper mockObjectMapper; 
    Jersey [] testJersey; 
    CartFileDAO cartFileDAO; 
    
    @BeforeEach
    public void setupCustomerFileDAO() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        
        testCarts = new Cart[2]; 
        List<Jersey> list1 = new ArrayList<>();
        List<Jersey> list2 = new ArrayList<>();

        testCarts[0] = new Cart(list1 , 1); 
        testCarts[1] =new Cart(list2, 2); 

        testJersey = new Jersey[3]; 
        testJersey[0] =  new Jersey(1,"colin guy", 25, 123.99, "Red", "Medium", "img.png");
        testJersey[1] = new Jersey(2, "patrick kane", 10, 250.30, "Red", "Small", "img.png");
        testJersey[2] = new Jersey(3, "meow man", 36, 2536.33, "Red", "Large", "img.png");
        
        // two jerseys 
        testCarts[0].addJerseyToCart(testJersey[0]);
        testCarts[0].addJerseyToCart(testJersey[1]);

        //3 jersey
        testCarts[1].addJerseyToCart(testJersey[2]);
        testCarts[1].addJerseyToCart(testJersey[0]);
        testCarts[1].addJerseyToCart(testJersey[0]);


        //create the two customers 
        testCustomers[0] = new Customer("Bobby", false, testCarts[0], 1);
        testCustomers[1] = new Customer("Trollo", false, testCarts[1],2 );

        // set up the mocks 
        when(mockObjectMapper
        .readValue(new File("doesnt_matter.txt"),Cart[].class))
            .thenReturn(testCarts);
            cartFileDAO = new CartFileDAO("doesnt_matter.txt", mockObjectMapper);
       
        when(mockObjectMapper
        .readValue(new File("doesnt_matter.txt"),Customer[].class))
            .thenReturn(testCustomers);
            customerFileDAO = new CustomerFileDAO("doesnt_matter.txt",mockObjectMapper, cartFileDAO);
    
    }

    @Test
    public void testGetSpecificCustomer() throws IOException{
        Customer result = customerFileDAO.getSpecificCustomer(1); 
        assertEquals(result.getUserId(), testCustomers[0].getUserId());
        assertEquals(result.getUserType(), testCustomers[0].getUserType());
        assertEquals(result.getUsername(), testCustomers[0].getUsername()); 
    }

    @Test
    public void testGetSpecificCustomerFail() throws IOException{
        Customer result = customerFileDAO.getSpecificCustomer(23);
        assertEquals(result, null);
    }

    @Test
    public void testGetCart(){
        Cart result = customerFileDAO.getCart(1); 
        assertEquals(result.getId(), testCarts[0].getId());
        assertEquals(result.getEntireCart().length, testCarts[0].getEntireCart().length); 
    }

     
    @Test
    public void testGetCartFail(){
        Cart result = customerFileDAO.getCart(27); 
        assertEquals(result, null);
      
    }

    @Test
    public void testCreateNewCustomer() throws IOException{
        List<Jersey> list3 = new ArrayList<>();
        Cart cart = new Cart(list3, 3);
        cart.addJerseyToCart(testJersey[0]);
        Customer cust = new Customer("meow guy", false, cart, 3);
        Customer result = customerFileDAO.createNewCustomer(cust);

        assertEquals(cust.getUserId(), result.getUserId());
        assertEquals(cust.getUserType(), result.getUserType());
        assertEquals(cust.getUsername(), result.getUsername());
        assertEquals(cust.getUsersCart().getEntireCart().length, result.getUsersCart().getEntireCart().length);
    }

    @Test
    public void testAddToCart()throws IOException{
        Jersey jersey = new Jersey(23, "poop guy",  56, 129.99, "Red", "Medium", "img.png"); 
        Customer result = customerFileDAO.addToCart(2, jersey); 

        assertEquals(result.getUsersCart().getEntireCart().length, 4);
    }

    @Test
    public void testAddToCartFail()throws IOException{
        Jersey jersey = new Jersey(23, "poop guy",  56, 129.99, "Red", "Medium", "img.png"); 
        Customer result = customerFileDAO.addToCart(27, jersey); 

        assertEquals(result, null);
    }


    @Test
    public void testDeleteEntireJerseyFromCart() throws IOException{
        int id = 2; 
        Jersey jersey = testJersey[0];
        Customer result = customerFileDAO.deleteEntireJerseyFromCart(id, jersey);
        assertEquals(result.getUsersCart().getEntireCart().length, 1);
    }

    
    @Test
    public void testDeleteEntireJerseyFromCartFail() throws IOException{
        int id = 27; 
        Jersey jersey = testJersey[0];
        Customer result = customerFileDAO.deleteEntireJerseyFromCart(id, jersey);
        assertEquals(result, null);
    }

    @Test
    public void testDecrementJerseyTypeAmount()throws IOException{
        int id = 2; 
        Jersey jersey = testJersey[0];
        Customer result = customerFileDAO.decrementJerseyTypeAmount(id, jersey);
        assertEquals(result.getUsersCart().getEntireCart().length ,2);
    }

    
    @Test
    public void testDecrementJerseyTypeAmountFail() throws IOException{
        int id = 27; 
        Jersey jersey = testJersey[0];
        Customer result = customerFileDAO.decrementJerseyTypeAmount(id, jersey);
        assertEquals(result, null);
    }

    @Test 
    public void testDeleteEntireCart() throws IOException{
        int id = 1; 
        Customer result = customerFileDAO.deleteEntireCart(id);
        assertEquals(result.getUsersCart().getEntireCart().length, 0);

    }

    @Test
    public void testDeleteEntireCartNull() throws IOException{
        int id = 27;
        Customer result = customerFileDAO.deleteEntireCart(id);
        assertEquals(result, null); 
    }
 


}
