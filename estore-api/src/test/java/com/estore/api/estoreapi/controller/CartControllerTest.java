package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.MalformedLinkException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.persistence.CartDAO;
import com.estore.api.estoreapi.persistence.JerseyDAO;

/**
 * This tests the Cart Controller Test 
 * @Author Angela Ngo
 */
@Tag("Controller-tier")
public class CartControllerTest {
    private CartController cartController; 
    private CartDAO mockCartDAO; 
    private JerseyDAO mockJerseyDAO; 

    @BeforeEach
    public void setupCartController(){
        mockCartDAO = mock(CartDAO.class);
        mockJerseyDAO = mock(JerseyDAO.class);
        cartController = new CartController(mockCartDAO, mockJerseyDAO);
    }

    @Test
    public void testDecrementJerseyTypeAmount() throws IOException{
        int cartId = 1; 
        
        int jerseyId = 99;
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), cartId);
        cart.addJerseyToCart(j1);
        cart.addJerseyToCart(j2);
        cart.addJerseyToCart(j1);

        cart.decrementJerseyTypeFromCart(j1.getId()); 

        when(mockJerseyDAO.getJersey(jerseyId)).thenReturn(j1);
        when(mockCartDAO.decrementJerseyTypeAmount(cartId, j1.getId())).thenReturn(cart);
        
        ResponseEntity<Cart> response = cartController.decrementJerseyTypeAmount(cartId, jerseyId); 
        
        assertEquals(cart, response.getBody());
        //assertEquals(2, response.getBody().totalJerseysInCart());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // its detecting the jersey as null 
    }

    @Test
    public void testDecrementJerseyTypeAmountFail()throws IOException{
        int cartId = 1;
        int jerseyId = 8; // this is not in the map: fails when not contained 
        Jersey jersey = mockJerseyDAO.getJersey(jerseyId);
    
        when(mockCartDAO.decrementJerseyTypeAmount(cartId, jerseyId)).thenReturn(null);

        ResponseEntity<Cart> response = cartController.decrementJerseyTypeAmount(cartId, jerseyId);
        //Cart actualCart = mockCartDAO.getSpecificCart(cartId);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void testDecrementJerseyTypeAmountHandleException()throws IOException{
        int cartId = 1;
        int jerseyId = 26; // this is not in the map: fails when not contained 
        Jersey jersey = mockJerseyDAO.getJersey(jerseyId);
        doThrow(new IOException()).when(mockCartDAO).decrementJerseyTypeAmount(cartId, jerseyId);

        //when(mockCartDAO.decrementJerseyTypeAmount(cartId, jersey)).thenReturn(false);

        ResponseEntity<Cart> response = cartController.decrementJerseyTypeAmount(cartId, jerseyId);
        //Cart actualCart = mockCartDAO.getSpecificCart(cartId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testAddJerseyToCart()throws IOException{
        int cartId = 1; 
        
        int jerseyId = 99;
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), cartId);
        cart.addJerseyToCart(j1);
        cart.addJerseyToCart(j2);
        
        when(mockJerseyDAO.getJersey(jerseyId)).thenReturn(j1);
        when(mockCartDAO.addJerseyToCart(cartId, j1)).thenReturn(cart);
        
        ResponseEntity<Cart> response = cartController.addJerseyToCart(cartId, jerseyId); 
        
        assertEquals(cart, response.getBody());
        //assertEquals(2, response.getBody().totalJerseysInCart());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddJerseyToCartFail()throws IOException{
        int cartId = 2;
        int jerseyId = 99;
        Jersey jersey = mockJerseyDAO.getJersey(jerseyId);
        when(mockCartDAO.addJerseyToCart(cartId, jersey)).thenReturn(null);

        ResponseEntity<Cart> response = cartController.addJerseyToCart(cartId, jerseyId);
        Cart actualCart = mockCartDAO.getSpecificCart(cartId);
        assertEquals(actualCart, response.getBody());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    // @Test
    // public void testAddJerseyToCartHandleException() throws IOException{
    //     int cartId = 2;
    //     int jerseyId = 99;
    //     Jersey jersey = mockJerseyDAO.getJersey(jerseyId);
    //     doThrow(new IOException()).when(mockCartDAO).decrementJerseyTypeAmount(cartId, jersey);

    //     ResponseEntity<Cart> response = cartController.addJerseyToCart(cartId, jerseyId);
    //     //Cart actualCart = mockCartDAO.getSpecificCart(cartId);
    //     //assertEquals(actualCart, response.getBody());
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    // }

    @Test
    public void testDeleteJerseyType() throws IOException{
        int cartId = 1; 
        
        int jerseyId = 99;
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), cartId);
        cart.addJerseyToCart(j1);
        cart.addJerseyToCart(j2);
        cart.addJerseyToCart(j1);

        cart.deleteJerseyType(j1); 

        when(mockJerseyDAO.getJersey(jerseyId)).thenReturn(j1);
        when(mockCartDAO.deleteEntireJerseyFromCart(cartId, j1)).thenReturn(cart);
        
        ResponseEntity<Cart> response = cartController.deleteJerseyType(cartId, jerseyId); 
        
        assertEquals(cart, response.getBody());
        //assertEquals(2, response.getBody().totalJerseysInCart());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    
    }

    @Test
    public void testDeleteJerseyTypeFail() throws IOException{
        int cartId = 1; 
        int jerseyId = 9;
        Jersey jersey = mockJerseyDAO.getJersey(jerseyId);
        when(mockCartDAO.deleteEntireJerseyFromCart(cartId, jersey)).thenReturn(null);

        ResponseEntity<Cart> response = cartController.deleteJerseyType(cartId, jerseyId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

  

    @Test
    public void testDeleteEntireCart() throws IOException{
        int cartId = 1; 
        
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), cartId);
        cart.addJerseyToCart(j1);
        cart.addJerseyToCart(j2);
        cart.addJerseyToCart(j1);

        cart.deleteEntireCart();
        when(mockCartDAO.deleteEntireCart(cartId)).thenReturn(cart);

        ResponseEntity<Cart> response = cartController.deleteEntireCart(cartId);
        assertEquals(HttpStatus.OK, response.getStatusCode()); 
    }

    @Test
    public void testDeleteEntireCartFail() throws IOException{
        int cartId = 1;
        Cart cart = mockCartDAO.getSpecificCart(cartId);
        when(mockCartDAO.deleteEntireCart(cartId)).thenReturn(null);

        ResponseEntity<Cart> response = cartController.deleteEntireCart(cartId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
    }

    @Test
    public void testGetSpecifcCart() throws IOException{
        int cartId = 1; 
        
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), cartId);
        cart.addJerseyToCart(j1);

        when(mockCartDAO.getSpecificCart(cartId)).thenReturn(cart);
        ResponseEntity<Cart> response= cartController.getSpecificCart(cartId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cart, response.getBody());

    }

    @Test
    public void testGetSpecifcCartFail() throws IOException{
        int cartId = 3;
        
        when(mockCartDAO.getSpecificCart(cartId)).thenReturn(null);

        ResponseEntity<Cart> response= cartController.getSpecificCart(cartId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
}
