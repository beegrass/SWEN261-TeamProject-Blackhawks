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
    Cart testCart;
    Item[] testItems; 
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupCartFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testCart = new Cart(new HashMap<>()); 
        testItems = new Item[3];
        testItems[0] = new Item(new Jersey(99, "Jack Hughes", 5, 99.99, "Black", "Medium","Image.png"), 1);
        testItems[1] = new Item(new Jersey(100, "Poopy someone", 1, 50.99, "Red", "Small","Image.png"), 1);
        testItems[2] = new Item(new Jersey(101, "Patrick Kane", 88, 129.99, "Red", "Large","Image.png"),1);
        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Item[].class))
                .thenReturn(testItems);
                cartFileDAO = new CartFileDAO("doesnt_matter.txt",mockObjectMapper, testCart);
    }

    @Test
    public void testDecrementJerseyTypeAmount() throws IOException{
        Jersey jersey = testItems[2].getJersey();
        boolean isDecremented = cartFileDAO.decrementJerseyTypeAmount(jersey);
        assertEquals(true, isDecremented);
        assertEquals(false, testCart.getEntireCart().containsKey(jersey));
    }

    @Test 
    public void testAddJerseyToCart() throws IOException{
        Jersey jersey = testItems[1].getJersey();
        boolean isAdded = cartFileDAO.addJerseyToCart(jersey);
        assertEquals(true, isAdded);
        assertEquals(2, testCart.getEntireCart().get(jersey));
    } 

    @Test 
    public void testDeleteEntireJerseyFromCart() throws IOException{
        Jersey jersey = testItems[1].getJersey(); 
        boolean isDeleted = cartFileDAO.deleteEntireJerseyFromCart(jersey);
        assertEquals(true, isDeleted);
        assertEquals(false, testCart.getEntireCart().containsKey(jersey));

    }

    @Test
    public void testDeleteEntireCart() throws IOException{
        boolean isDeleted = cartFileDAO.deleteEntireCart();
        assertEquals(true, isDeleted);

        assertEquals(0.00, testCart.getTotalCost());
    }




}
