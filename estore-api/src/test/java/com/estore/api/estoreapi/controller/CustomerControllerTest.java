package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.IIOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.persistence.CustomerDAO;
import com.estore.api.estoreapi.persistence.JerseyDAO;

/**
 * Ths tests the Customer controller test
 * @Author Angela Ngo 
 */
@Tag("Controller-tier")
public class CustomerControllerTest {
    private CustomerController customerController;
    private CustomerDAO mockCustomerDAO;
    private JerseyDAO mockJerseyDAO;
    
    @BeforeEach
    public void setUpCustomerController(){
        mockCustomerDAO = mock(CustomerDAO.class);
        mockJerseyDAO = mock(JerseyDAO.class);
        customerController = new CustomerController(mockCustomerDAO, mockJerseyDAO);
    }

    @Test
    public void testGetCart() throws IOException{
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), 1);
        cart.addJerseyToCart(j1);
        cart.addJerseyToCart(j2);
        cart.addJerseyToCart(j1);
    
        Customer customer = new Customer("meow man", false, cart,  1);
        when(mockCustomerDAO.getCart(1)).thenReturn(customer.getUsersCart()); 
        
        ResponseEntity<Cart> response = customerController.getCart(1);
        assertEquals(cart, response.getBody()); 
        assertEquals(HttpStatus.OK, response.getStatusCode());  

    }

    @Test
    public void testGetCartNotFound()throws IOException{
        when(mockCustomerDAO.getCart(2)).thenReturn(null); 
        ResponseEntity<Cart> response = customerController.getCart(2);
        assertEquals(null, response.getBody() );
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); 
    }

    @Test
    public void testGetSpecifcCustomer() throws IOException{
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), 1);
        cart.addJerseyToCart(j1);
        cart.addJerseyToCart(j2);
        cart.addJerseyToCart(j1);
    
        Customer customer = new Customer("meow man", false, cart,  1);
        when(mockCustomerDAO.getSpecificCustomer("meow man")).thenReturn(customer); 
        ResponseEntity<Customer> response = customerController.getSpecificCustomer("meow man");
        assertEquals(customer, response.getBody()); 
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetSpecifcCustomerFail() throws IOException{
        when(mockCustomerDAO.getSpecificCustomer("rofneo")).thenReturn(null);
        ResponseEntity<Customer> response = customerController.getSpecificCustomer("rofneo"); 
        assertEquals(null, response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testCreateNewCustomer() throws IOException{
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), 1);
        cart.addJerseyToCart(j1);
        cart.addJerseyToCart(j2);
        cart.addJerseyToCart(j1);
    
        Customer customer = new Customer("meow man", false, cart,  1);
        when(mockCustomerDAO.createNewCustomer(customer)).thenReturn(customer); 
        ResponseEntity<Customer> response = customerController.createNewCustomer(customer);

        assertEquals(customer, response.getBody()); 
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testCreateNewCustomerFail() throws IOException{
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), 1);
        cart.addJerseyToCart(j1);
        cart.addJerseyToCart(j2);
        cart.addJerseyToCart(j1);
    
        Customer customer = new Customer("meow man", false, cart,  1);
        when(mockCustomerDAO.createNewCustomer(customer)).thenReturn(null); 
        ResponseEntity<Customer> response = customerController.createNewCustomer(customer);

        assertEquals(null, response.getBody()); 
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    // @Test
    // public void testAddToCart() throws IOException{
    //     Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
    //     Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
    //     Cart cart = new Cart(new ArrayList<Jersey>(), 1);
    //     cart.addJerseyToCart(j1);
    //     cart.addJerseyToCart(j2);
    //     cart.addJerseyToCart(j1);
    
    //     Customer customer = new Customer("meow man", false, cart,  1);
    //     customer.getUsersCart().addJerseyToCart(j2); 
    //     when(mockJerseyDAO.getJersey(29)).thenReturn(j2); 
    //     when(mockCustomerDAO.addToCart(1, j2)).thenReturn(customer);
        
    //     ResponseEntity<Customer> result = customerController.addToCart(1, 29);
    //     assertEquals(4, result.getBody().getUsersCart().getEntireCart().length);
    //     assertEquals(HttpStatus.OK, result.getStatusCode());
    // }

    // @Test
    // public void testAddToCartFail() throws IOException{
    //     when(mockJerseyDAO.getJersey(29)).thenReturn(null); 
    //     when(mockCustomerDAO.addToCart(1, null)).thenReturn(null);
        
    //     ResponseEntity<Customer> result = customerController.addToCart(1, 29);
    //     assertEquals(null, result.getBody());
    //     assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    // }

    // @Test
    // public void testDecrementJerseyFromCart() throws IOException{
    //     Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
    //     Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
    //     Cart cart = new Cart(new ArrayList<Jersey>(), 1);
    //     cart.addJerseyToCart(j1);
    //     cart.addJerseyToCart(j2);
    //     cart.addJerseyToCart(j1);
    
    //     Customer customer = new Customer("meow man", false, cart,  1);
    //     customer.getUsersCart().decrementJerseyTypeFromCart(29); 
    //     when(mockJerseyDAO.getJersey(29)).thenReturn(j2); 
    //     when(mockCustomerDAO.decrementJerseyTypeAmount(1, j2)).thenReturn(customer);

    //     ResponseEntity<Customer> result = customerController.decrementJerseyFromCart(1, 29);

    //     assertEquals(customer, result.getBody());
    //     assertEquals(customer.getUsersCart().getEntireCart().length, result.getBody().getUsersCart().getEntireCart().length);
    //     assertEquals(HttpStatus.OK, result.getStatusCode());
    // }

    // @Test
    // public void testDecrementJerseyFromCartFail() throws IOException{
    //     Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
    //     Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
    //     Cart cart = new Cart(new ArrayList<Jersey>(), 1);
    //     cart.addJerseyToCart(j1);
    //     cart.addJerseyToCart(j2);
    //     cart.addJerseyToCart(j1);
    
    //     //Customer customer = new Customer("meow man", false, cart,  1);
    //     //customer.getUsersCart().decrementJerseyTypeFromCart(29); 
    //     when(mockJerseyDAO.getJersey(13)).thenReturn(null); 
    //     when(mockCustomerDAO.decrementJerseyTypeAmount(1, null)).thenReturn(null);

    //     ResponseEntity<Customer> result = customerController.decrementJerseyFromCart(1, 29);

    //     assertEquals(null, result.getBody());
    //     assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    // }

    // @Test
    // public void testDeleteTypeJersey()throws IOException{
    //     Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
    //     Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
    //     Cart cart = new Cart(new ArrayList<Jersey>(), 1);
    //     cart.addJerseyToCart(j1);
    //     cart.addJerseyToCart(j2);
    //     cart.addJerseyToCart(j1);
    
    //     Customer customer = new Customer("meow man", false, cart,  1);
    //     customer.getUsersCart().deleteJerseyType(j1); 
     
    //     when(mockJerseyDAO.getJersey(99)).thenReturn(j1); 
    //     when(mockCustomerDAO.deleteEntireJerseyFromCart(1, j1)).thenReturn(customer);

    //     ResponseEntity<Customer> result = customerController.deleteTypeJersey(1, 99);

    //     assertEquals(1, result.getBody().getUsersCart().getEntireCart().length);
    //     assertEquals(HttpStatus.OK, result.getStatusCode());
    // }

    // @Test
    // public void testDeleteTypeJerseyFail()throws IOException{
    //     Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
    //     Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
    //     Cart cart = new Cart(new ArrayList<Jersey>(), 1);
    //     cart.addJerseyToCart(j1);
    //     cart.addJerseyToCart(j2);
    //     cart.addJerseyToCart(j1);
    
    //     // Customer customer = new Customer("meow man", false, cart,  1);
    //     // customer.getUsersCart().deleteJerseyType(j1); 
     
    //     when(mockJerseyDAO.getJersey(999)).thenReturn(null); 
    //     when(mockCustomerDAO.decrementJerseyTypeAmount(1, null)).thenReturn(null);

    //     ResponseEntity<Customer> result = customerController.decrementJerseyFromCart(1, 999);

    //     assertEquals(null, result.getBody());
    //     assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    // }

    @Test
    public void testDeleteEntireCart()throws IOException{
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), 1);
        cart.addJerseyToCart(j1);
        cart.addJerseyToCart(j2);
        cart.addJerseyToCart(j1);
    
        Customer customer = new Customer("meow man", false, cart,  1);
        customer.getUsersCart().deleteEntireCart(); 

        when(mockCustomerDAO.deleteEntireCart(1)).thenReturn(customer); 
        ResponseEntity<Customer> result = customerController.deleteCart(1);

        assertEquals(customer, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testDeleteEntireCartFail()throws IOException{
        Jersey j1 = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        Jersey j2 = new Jersey(29,"Todd", 29, 199.22, "Red", "Small", "image1.png");
        Cart cart = new Cart(new ArrayList<Jersey>(), 1);
        cart.addJerseyToCart(j1);
        cart.addJerseyToCart(j2);
        cart.addJerseyToCart(j1);
    
        Customer customer = new Customer("meow man", false, cart,  1);
        customer.getUsersCart().deleteEntireCart(); 

        when(mockCustomerDAO.deleteEntireCart(2)).thenReturn(null); 
        ResponseEntity<Customer> result = customerController.deleteCart(2);

        assertEquals(null, result.getBody());
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }



    


}
