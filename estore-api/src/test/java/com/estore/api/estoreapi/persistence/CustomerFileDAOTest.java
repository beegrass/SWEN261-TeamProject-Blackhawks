package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;
import com.fasterxml.jackson.databind.ObjectMapper;

@Tag("Persistence-tier")
public class CustomerFileDAOTest {
    CustomerFileDAO customerFileDAO;
    Customer[] testCustomers;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupCustomerFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testCustomers = new Customer[4];
        testCustomers[0] = new Customer(99, "Tony Audi");
        testCustomers[1] = new Customer(100, "Bobby");
        testCustomers[2] = new Customer(101, "Bruce Herring");
        testCustomers[3] = new Customer(102, "Z");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Customer[].class))
                .thenReturn(testCustomers);
                customerFileDAO = new CustomerFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testGetCustomers() throws IOException {
        // Invoke
        Customer[] customers = customerFileDAO.getCustomers();

        // Analyze
        assertEquals(customers.length,testCustomers.length);
        for (int i = 0; i < testCustomers.length;i++){
            assertEquals(customers[i],testCustomers[i]);
        }
    }

    @Test
    public void testGetCustomer() throws IOException {
        // Invoke
        Customer customer = customerFileDAO.getCustomer(99);

        // Analzye
        assertEquals(customer,testCustomers[0]);
    }

    @Test
    public void testGetCustomerNotFound() throws IOException {
        // Invoke
        Customer customer = customerFileDAO.getCustomer(98);

        // Analyze
        assertEquals(customer,null);
    }

    @Test
    public void testGetCustomerUserName() throws IOException {
        // Invoke
        Customer[] customers = customerFileDAO.findCustomers("Tony", null);

        // Analyze
        assertEquals(customers.length,1);
        assertEquals(customers[0],testCustomers[0]);
    }

    @Test
    public void testGetCustomerCart() throws IOException {
        // Setup
        Jersey jersey = new Jersey(2, "Name", 50, 129.99, "Red", "S", "image.png");
        testCustomers[0].addToCart(jersey);
        System.out.println(testCustomers[0].getCart());
        
        // Invoke
        ArrayList<Jersey> cart = new ArrayList<>();
        cart.add(jersey);
        Customer[] customers = customerFileDAO.findCustomers(null, cart);

        // Analyze
        assertEquals(1, customers.length);
        assertEquals(customers[0],testCustomers[0]);
    }

    @Test
    public void testFindCustomersAll() throws IOException {
        // Invoke
        Customer[] customers = customerFileDAO.findCustomers(null, null);

        // Analyze
        assertEquals(customers.length,4);
        assertEquals(customers[0],testCustomers[0]);
        assertEquals(customers[1],testCustomers[1]);
        assertEquals(customers[2],testCustomers[2]);
        assertEquals(customers[3],testCustomers[3]);
    }

    @Test
    public void testCreateCustomer() throws IOException {
        // Setup
        Customer customer = new Customer(103,"Wonder-Person");

        // Invoke
        Customer result = assertDoesNotThrow(() -> customerFileDAO.createCustomer(customer),
                                "Unexpected exception thrown");
        

        // Analyze
        assertNotNull(result);
        Customer actual = customerFileDAO.getCustomer(customer.getId());
        assertEquals(customer.getId(),actual.getId());
        assertEquals(customer.getUserName(),actual.getUserName());
    }

    @Test
    public void testAddToCart() throws IOException {
        // Setup
        Jersey jersey = new Jersey(1, "Name", 99, 129.99, "Red", "S", "images.png");
        ArrayList<Jersey> expected = new ArrayList<>();
        expected.add(jersey);

        // Invoke
        customerFileDAO.addJerseyToCart(testCustomers[0], jersey);
        ArrayList<Jersey> actual = testCustomers[0].getCart();

        // Analysis
        assertEquals(expected, actual);
    }

    @Test
    public void testAddToCartNotFound() throws IOException {
        // Setup
        Customer customer = new Customer(10, "Beans");
        Jersey jersey = new Jersey(1, "Name", 99, 129.99, "Red", "S", "images.png");
        ArrayList<Jersey> expected = null;

        // Invoke
        Customer actual = customerFileDAO.addJerseyToCart(customer, jersey);

        // Analysis
        assertEquals(expected, actual.getCart());

    }

    @Test
    public void testRemoveFromCart() throws IOException {
        // Setup
        Jersey jersey = new Jersey(1, "Name", 99, 129.99, "Red", "S", "images.png");
        ArrayList<Jersey> expected = new ArrayList<>();
        expected.add(jersey);
        
        // Invoke
        customerFileDAO.addJerseyToCart(testCustomers[0], jersey);
        customerFileDAO.addJerseyToCart(testCustomers[0], jersey);
        customerFileDAO.removeFromCart(testCustomers[0], jersey);
        ArrayList<Jersey> actual = testCustomers[0].getCart();

        // Analysis
        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveFromEmpty() throws IOException {
        // Setup
        Jersey jersey = new Jersey(1, "Name", 99, 129.99, "Red", "S", "images.png");
        ArrayList<Jersey> expected = new ArrayList<>();
        
        // Invoke
        Customer actual = customerFileDAO.removeFromCart(testCustomers[0], jersey);

        // Analysis
        assertEquals(expected, actual.getCart());
    }

    @Test
    public void testRemoveFromCartNotFound() throws IOException {
        // Setup
        Customer customer = new Customer(10, "Beans");
        Jersey jersey = new Jersey(1, "Name", 99, 129.99, "Red", "S", "images.png");
        ArrayList<Jersey> expected = null;

        // Invoke
        Customer actual = customerFileDAO.removeFromCart(customer, jersey);

        // Analysis
        assertEquals(expected, actual.getCart());
    }

    @Test
    public void testEmptyCart() throws IOException {
        // Setup
        Jersey jersey = new Jersey(1, "Name", 99, 129.99, "Red", "S", "images.png");
        ArrayList<Jersey> expected = new ArrayList<>();
        
        // Invoke
        customerFileDAO.addJerseyToCart(testCustomers[0], jersey);
        customerFileDAO.addJerseyToCart(testCustomers[0], jersey);
        customerFileDAO.addJerseyToCart(testCustomers[0], jersey);
        customerFileDAO.addJerseyToCart(testCustomers[0], jersey);
        Customer actual = customerFileDAO.emptyCart(testCustomers[0]);

        // Analysis
        assertEquals(expected, actual.getCart());
    }

    @Test
    public void testEmptyCartEmpty() throws IOException {
        // Setup
        ArrayList<Jersey> expected = new ArrayList<>();
        
        // Invoke
        Customer actual = customerFileDAO.emptyCart(testCustomers[0]);

        // Analysis
        assertEquals(expected, actual.getCart());
    }

    @Test
    public void testEmptyCartNotFound() throws IOException {
        // Setup
        Customer customer = new Customer(10, "Beans");
        ArrayList<Jersey> expected = null;
        
        // Invoke
        Customer actual = customerFileDAO.emptyCart(customer);

        // Analysis
        assertEquals(expected, actual.getCart());
    }

    @Test
    public void testGetTotalCost() throws IOException {
        // Setup
        Jersey jersey = new Jersey(1, "Name", 99, 10, "Red", "S", "images.png");
        double expected = 40;
        
        // Invoke
        testCustomers[0].addToCart(jersey);
        testCustomers[0].addToCart(jersey);
        testCustomers[0].addToCart(jersey);
        testCustomers[0].addToCart(jersey);
        double actual = customerFileDAO.getTotalCost(testCustomers[0]);

        // Analysis
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTotalCostEmpty() throws IOException {
        // Setup
        double expected = 0;
        
        // Invoke
        double actual = customerFileDAO.getTotalCost(testCustomers[0]);

        // Analysis
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTotalCostNotFound() throws IOException {
        // Setup
        Customer customer = new Customer(10, "Beans");
        double expected = 0.0;

        // Invoke
        double actual = customerFileDAO.getTotalCost(customer);

        // Analysis
        assertEquals(expected, actual);
    }

}
