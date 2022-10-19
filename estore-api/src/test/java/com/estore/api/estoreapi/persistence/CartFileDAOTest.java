package com.estore.api.estoreapi.persistence;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.junit.jupiter.api.BeforeEach;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.Cart;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test the Cart File DAO class
 * 
 * @author Vincent Schwartz
 */

public class CartFileDAOTest {
    //CartFileDAO cartFileDAO;
    HashMap<Jersey, Integer> testCart;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupJerseyFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testCart = new HashMap<Jersey, Integer>();
        Jersey jersey1 = new Jersey(99, "Jack Hughes", 5, 99.99, "Black", "Medium","Image.png");
        Jersey jersey2 = new Jersey(100, "Poopy someone", 1, 50.99, "Red", "Small","Image.png");
        Jersey jersey3 = new Jersey(101, "Patrick Kane", 88, 129.99, "Red", "Large","Image.png");

        testCart.put(jersey1, 1);
        testCart.put(jersey2, 5);
        testCart.put(jersey3, 3);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        // when(mockObjectMapper
        //     .readValue(new File("doesnt_matter.txt"),Jersey[].class))
        //         .thenReturn(testCart);
        //         cartFileDAO = new CartFileDAO("doesnt_matter.txt",mockObjectMapper);
    }


}
