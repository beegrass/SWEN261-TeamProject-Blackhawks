package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Item;
import com.estore.api.estoreapi.model.Jersey;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test the Cart File DAO class
 * 
 * @author Angela Ngo 
 */

public class CartFileDAOTest {
    CartFileDAO cartFileDAO;
    Cart[] testCarts; 
    ObjectMapper mockObjectMapper;
    Jersey [] testJersey; 

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupCartFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testCarts = new Cart[2]; 
    
        testCarts[0] = new Cart(new HashMap<Jersey, Integer>(),1); 
        testCarts[1] =new Cart(new HashMap<Jersey, Integer>(), 2); 

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
        
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Cart[].class))
                .thenReturn(testCarts);
                cartFileDAO = new CartFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testGetEntireCart() throws IOException{
        HashMap<Jersey, Integer> actual = cartFileDAO.getEntireCart(1); 
        assertEquals(2, actual.size());
    }

    @Test
    public void testGetCartsArray(){
        Cart [] actual = cartFileDAO.getCartsArray();
        assertEquals(2, actual.length);
    }

    @Test
    public void testDecrementJerseyTypeAmount() throws IOException{
        int cartId = 2;
        Jersey jersey = testJersey[0];
        boolean actual = cartFileDAO.decrementJerseyTypeAmount(cartId, jersey);
        assertEquals(true, actual);
        assertEquals(2, testCarts[1].totalJerseysInCart()); 
    }

    @Test
    public void testGetSpecifcCart(){
        int cartId = 1; 
        Cart actual = cartFileDAO.getSpecificCart(cartId); 

        assertEquals(testCarts[0], actual);
        assertEquals(testCarts[0].getId(), actual.getId());
    }

    @Test
    public void addJerseyToCart() throws IOException{
        int cartId = 1;
        Jersey jersey = testJersey[0];

        boolean actual = cartFileDAO.addJerseyToCart(cartId, jersey);
        Cart cart = cartFileDAO.getSpecificCart(cartId);
        assertEquals(true, actual);
        assertEquals(3, cart.totalJerseysInCart());
    }

    @Test
    public void testAddNewJerseyToCart() throws IOException{
        // this is just seeing if i can insert a entirely different jersey in
        int cartId  = 1; 
        Jersey jersey =  testJersey[2];
        boolean actual = cartFileDAO.addJerseyToCart(cartId, jersey);

        Cart cart = cartFileDAO.getSpecificCart(cartId);
        assertEquals(true, actual);
        assertEquals(3, cart.totalJerseysInCart());
    }

    @Test
    public void testDeleteEntireJerseyFromCart() throws IOException{
        int cartId = 1; 
        Jersey jersey = testJersey[0];

        boolean actual = cartFileDAO.deleteEntireJerseyFromCart(cartId, jersey);
        Cart cart = cartFileDAO.getSpecificCart(cartId); 
        assertEquals(true, actual);
        assertEquals(1, cart.totalJerseysInCart());
    }

    @Test  void testCreateNewCart() throws IOException{
        Cart newCart = new Cart(new HashMap<Jersey, Integer>(),3 );
        Cart actual = cartFileDAO.createNewCart(newCart);
        
        assertEquals(newCart.totalJerseysInCart(), actual.totalJerseysInCart());
        assertEquals(newCart.getTotalCost(), actual.getTotalCost());
        //SassertEquals(newCart, actual);
    }
    @Test
    public void testDeleteEntireCart() throws IOException{
        int cartId = 1;
        boolean actual = cartFileDAO.deleteEntireCart(cartId); 
        assertEquals(true, actual);
        assertEquals(2, cartFileDAO.getCartsArray().length);
        assertEquals(0, cartFileDAO.getSpecificCart(cartId).totalJerseysInCart());
        
    }


}
