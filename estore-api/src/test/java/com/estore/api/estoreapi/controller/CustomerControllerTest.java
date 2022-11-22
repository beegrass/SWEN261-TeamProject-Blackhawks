package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.CustomerDAO;
import com.estore.api.estoreapi.persistence.JerseyDAO;
import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Tag("Controller-tier")
public class CustomerControllerTest {
    
    private CustomerController customerController;
    private CustomerDAO mockCustomerDAO;
    private JerseyDAO mockJerseyDAO;

    /**
     * Before each test, create a new JerseyController object and inject
     * a mock Jersey DAO
     */
    @BeforeEach
    public void setupCustomerController() { 
        mockCustomerDAO = mock(CustomerDAO.class);
        mockJerseyDAO = mock(JerseyDAO.class);
        customerController = new CustomerController(mockCustomerDAO, mockJerseyDAO);
    }

    // ------------ TESTING GET CUSTOMER ------------

    @Test
    public void testGetCustomer() throws java.io.IOException {// getJersey may throw IOException
        // Setup
        Customer customer = new Customer(99,"NewGuy");
        // When the same id is passed in, our mock Hero DAO will return the Hero object
        when(mockCustomerDAO.getCustomer(customer.getId())).thenReturn(customer);

        // Invoke
        ResponseEntity<Customer> response = customerController.getCustomer(customer.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(customer,response.getBody());
    }

    @Test
    public void testGetCustomerNotFound() throws java.lang.Exception { 
        int customerId = 99;

        when(mockCustomerDAO.getCustomer(customerId)).thenReturn(null);

        // Invoke
        ResponseEntity<Customer> response = customerController.getCustomer(customerId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetCustomerHandleException() throws java.lang.Exception { 
        // Setup
        int customerId = 99;

        // When getCustomer is called on the Mock Customer DAO, throw an IOException
        doThrow(new IOException()).when(mockCustomerDAO).getCustomer(customerId);

        // Invoke
        ResponseEntity<Customer> response = customerController.getCustomer(customerId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    // ------------ TESTING GET CUSTOMERS ------------

    @Test
    public void testGetCustomers() throws java.io.IOException {
        // Setup
        Customer[] customers = new Customer[2];
        customers[0] = new Customer(99,"Marc-Andre Fleury");
        customers[1] = new Customer(100,"Patrick Kane");
        // When getHeroes is called return the heroes created above
        when(mockCustomerDAO.getCustomers()).thenReturn(customers);

        // Invoke
        ResponseEntity<Customer[]> response = customerController.getCustomers();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(customers,response.getBody());
    }

    @Test
    public void testGetCustomersNotFound() throws java.io.IOException {
        // Setup
        // When getHeroes is called on the Mock Hero DAO, throw an IOException
        when(mockCustomerDAO.getCustomers()).thenReturn(null);

        // Invoke
        ResponseEntity<Customer[]> response = customerController.getCustomers();

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetCustomersHandleException() throws java.io.IOException { 
        // Setup
        // When getHeroes is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockCustomerDAO).getCustomers();

        // Invoke
        ResponseEntity<Customer[]> response = customerController.getCustomers();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    // ------------ TESTING GET CUSTOMERS ------------

    @Test
    public void testGetCart() throws java.io.IOException {// getJersey may throw IOException
        // Setup
        Customer customer = new Customer(99,"NewGuy");
        Jersey[] cart = new Jersey[0];
        // When the same id is passed in, our mock Hero DAO will return the Hero object
        when(mockCustomerDAO.getCart(customer.getId())).thenReturn(customer.getCart().toArray(new Jersey[0]));

        // Invoke
        ResponseEntity<Jersey[]> response = customerController.getCart(customer.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertArrayEquals(cart,response.getBody());
    }

    @Test
    public void testGetCartNotFound() throws java.lang.Exception { 
        int customerId = 99;

        when(mockCustomerDAO.getCart(customerId)).thenReturn(null);

        // Invoke
        ResponseEntity<Jersey[]> response = customerController.getCart(customerId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }



    @Test
    public void testGetCartHandleException() throws java.lang.Exception { 
        // Setup
        int customerId = 99;

        // When getCustomer is called on the Mock Customer DAO, throw an IOException
        doThrow(new IOException()).when(mockCustomerDAO).getCart(customerId);

        // Invoke
        ResponseEntity<Jersey[]> response = customerController.getCart(customerId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    // ------------ TESTING CREATE CUSTOMER ------------

    @Test
    public void testCreateCustomer() throws java.io.IOException { 
        // Setup
        Customer customer = new Customer(99,"Marc-Andre Fleury");
        // when createHero is called, return true simulating successful
        // creation and save
        when(mockCustomerDAO.createCustomer(customer)).thenReturn(customer);

        // Invoke
        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(customer,response.getBody());
    }

    @Test
    public void testCreateCustomerFailed() throws java.io.IOException { 
        // Setup
        Customer customer = new Customer(99,"Marc-Andre Fleury");
        // when createHero is called, return false simulating failed
        // creation and save
        when(mockCustomerDAO.createCustomer(customer)).thenReturn(null);

        // Invoke
        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @Test
    public void testCreateJerseyHandleException() throws java.io.IOException { 
        // Setup
        Customer customer = new Customer(99,"Marc-Andre Fleury");

        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockCustomerDAO).createCustomer(customer);

        // Invoke
        ResponseEntity<Customer> response = customerController.createCustomer(customer);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    
    // ------------ TESTING ADD JERSEY TO CART ------------

    // @Test
    // public void testAddJerseyToCart() throws java.io.IOException {
    //     // Setup
    //     Customer customer = new Customer(100, "NewUser");
    //     Jersey jersey = new Jersey(1, "GA", 100, 9.99, "red", "XK", "Image.png");
    //     when(mockCustomerDAO.addJerseyToCart(customer,jersey)).thenReturn(customer);
        

    //     // Invoke
    //     ResponseEntity<Customer> response = customerController.addJerseyToCart(customer.getId(),jersey.getId());
    //     // response = jerseyController.updateJersey(jersey);

    //     // Analyze
    //     assertEquals(HttpStatus.OK,response.getStatusCode());
    //     assertEquals(customer,response.getBody());
    // }

    @Test
    public void testAddJerseyToCartFailed() throws java.io.IOException { 
        // setup
        Customer customer = new Customer(100, "NewUser");
        Jersey jersey = new Jersey(372891, "GA", 100, 9.99, "red", "XL", "Image.png");
        when(mockCustomerDAO.addJerseyToCart(customer,jersey)).thenReturn(null);

        // Invoke
        ResponseEntity<Customer> response = customerController.addJerseyToCart(customer.getId(), jersey.getId());

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    // @Test
    // public void testAddJerseyToCartException() throws java.io.IOException {
    //     Customer customer = new Customer(100, "NewUser");
    //     Jersey jersey = new Jersey(5, "handle", 100, 9.99, "red", "XL", "Image.png");
        
    //     doThrow(new IOException()).when(mockCustomerDAO).addJerseyToCart(customer, jersey);

    //     // Invoke
    //     ResponseEntity<Customer> response = customerController.addJerseyToCart(customer.getId(),jersey.getId());

    //     // Analyze
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    // }

    // ------------ TESTING REMOVE JERSEY FROM CART ------------

    // @Test
    // public void testRemoveJerseyFromCart() throws java.io.IOException {
    //     // Setup
    //     Customer customer = new Customer(100, "NewUser");
    //     Jersey jersey = new Jersey(1, "GA", 100, 9.99, "red", "XK", "Image.png");
    //     when(mockCustomerDAO.removeFromCart(customer,jersey)).thenReturn(customer);
        

    //     // Invoke
    //     ResponseEntity<Customer> response = customerController.removeJerseyFromCart(customer.getId(),jersey.getId());
    //     // response = jerseyController.updateJersey(jersey);

    //     // Analyze
    //     assertEquals(HttpStatus.OK,response.getStatusCode());
    //     assertEquals(customer,response.getBody());
    // }

    @Test
    public void testRemoveJerseyFromCartFailed() throws java.io.IOException { 
        // setup
        Customer customer = new Customer(100, "NewUser");
        Jersey jersey = new Jersey(372891, "GA", 100, 9.99, "red", "XL", "Image.png");
        when(mockCustomerDAO.removeFromCart(customer,jersey)).thenReturn(null);

        // Invoke
        ResponseEntity<Customer> response = customerController.removeJerseyFromCart(customer.getId(), jersey.getId());

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    // @Test
    // public void testRemoveJerseyFromCartException() throws java.io.IOException {
    //     Customer customer = new Customer(100, "NewUser");
    //     Jersey jersey = new Jersey(5, "handle", 100, 9.99, "red", "XL", "Image.png");
        
    //     doThrow(new IOException()).when(mockCustomerDAO).removeFromCart(customer, jersey);

    //     // Invoke
    //     ResponseEntity<Customer> response = customerController.removeJerseyFromCart(customer.getId(),jersey.getId());

    //     // Analyze
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    // }

    // ------------ TESTING EMPTY CART ------------

    // @Test
    // public void testEmptyCart() throws java.io.IOException {
    //     // Setup
    //     Customer customer = new Customer(1, "NewUser");
    //     when(mockCustomerDAO.emptyCart(customer)).thenReturn(customer);
        

    //     // Invoke
    //     ResponseEntity<Customer> response = customerController.emptyCart(customer.getId());
    //     // response = jerseyController.updateJersey(jersey);

    //     // Analyze
    //     assertEquals(HttpStatus.OK,response.getStatusCode());
    //     assertEquals(customer,response.getBody());
    // }

    @Test
    public void testEmptyCartFailed() throws java.io.IOException { 
        // setup
        Customer customer = new Customer(1, "NewUser");
        when(mockCustomerDAO.emptyCart(customer)).thenReturn(null);

        // Invoke
        ResponseEntity<Customer> response = customerController.emptyCart(customer.getId());

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    // @Test
    // public void testEmptyCartException() throws java.io.IOException {
    //     Customer customer = new Customer(1, "NewUser");
        
    //     doThrow(new IOException()).when(mockCustomerDAO).emptyCart(customer);

    //     // Invoke
    //     ResponseEntity<Customer> response = customerController.emptyCart(customer.getId());

    //     // Analyze
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    // }

    // ------------ TESTING GET TOTAL COST ------------

    // @Test
    // public void testGetTotalCost() throws java.io.IOException {// getJersey may throw IOException
    //     // Setup
    //     Customer customer = new Customer(99,"NewGuy");
        
    //     when(mockCustomerDAO.getTotalCost(customer)).thenReturn(customer.getTotalCost());

    //     // Invoke
    //     ResponseEntity<Double> response = customerController.getTotalCost(customer.getId());

    //     // Analyze
    //     assertEquals(HttpStatus.OK,response.getStatusCode());
    //     assertEquals(customer,response.getBody());
    // }

    @Test
    public void testGetTotalCostNotFound() throws java.lang.Exception { 
        Customer customer = new Customer(99,"NewGuy");

        when(mockCustomerDAO.getTotalCost(customer)).thenReturn(0.0);

        // Invoke
        ResponseEntity<Double> response = customerController.getTotalCost(customer.getId());

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    // @Test
    // public void testGetTotalCostHandleException() throws java.lang.Exception { 
    //     // Setup
    //     Customer customer = new Customer(99,"NewGuy");
    //     int customerId = 99;

    //     // When getCustomer is called on the Mock Customer DAO, throw an IOException
    //     doThrow(new IOException()).when(mockCustomerDAO).getTotalCost(customer);

    //     // Invoke
    //     ResponseEntity<Double> response = customerController.getTotalCost(customerId);

    //     // Analyze
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    // }
}
