package com.estore.api.estoreapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.client.HttpClientErrorException.NotFound;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.CustomerDAO;
import com.estore.api.estoreapi.persistence.JerseyDAO;
import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private static final Logger LOG = Logger.getLogger(CustomerController.class.getName());
    private CustomerDAO customerDao;
    private JerseyDAO jerseyDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param customerDao The {@link CustomerDao Customer Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public CustomerController(CustomerDAO customerDao, JerseyDAO jerseyDao) {
        this.customerDao = customerDao;
        this.jerseyDao = jerseyDao;
    }

    /**
     * Responds to the GET request for a {@linkplain Customer customer} for the given id
     * 
     * @param id The id used to locate the {@link Customer customer}
     * 
     * @return ResponseEntity with {@link Customer customer} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        LOG.info("GET /customers/" + id);
        try {
            Customer customer = customerDao.getCustomer(id);
            if (customer != null)
                return new ResponseEntity<Customer>(customer,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Customer customer}
     * 
     * @return ResponseEntity with array of {@link Customer customer} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Customer[]> getCustomers() {
        LOG.info("GET /customers");
        try {
            Customer[] customers = customerDao.getCustomers();
            if(customers != null) {
                return new ResponseEntity<Customer[]>(customers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(customers, HttpStatus.NOT_FOUND);
            }
        } catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Jersey[]> getCart(@PathVariable int id) {
        LOG.info("GET /customers/cart/" + id);
        try {
            Jersey[] cart = customerDao.getCart(id);
            if (cart != null)
                return new ResponseEntity<Jersey[]>(cart,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a {@linkplain Customer customer} with the provided customer object
     * 
     * @param customer - The {@link Customer customer} to create
     * 
     * @return ResponseEntity with created {@link Customer customer} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Customer customer} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        LOG.info("POST /customers " + customer);
        try {
            //check if a customer already exists with the given customer's name
            Customer result = customerDao.createCustomer(customer);
            if(result != null)
            {
                Customer newCustomer = customerDao.createCustomer(customer);
                return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the {@linkplain Customer customer} cart with the provided {@linkplain Jersey jersey} object, if it exists
     * 
     * @param custId The {@link Customer customer} who's cart will be added to
     * @param jerseyId The {@link Jersey jersey} to add to the cart
     * 
     * @return ResponseEntity with updated {@link <ArrayList<Jersey>> jersey} object and HTTP status of OK if added<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("/add/{custId}/{jerseyId}")
    public ResponseEntity<Customer> addJerseyToCart(@PathVariable int custId, @PathVariable int jerseyId) {
        LOG.info("PUT /customers/add/" + custId + "/" + jerseyId);
        try {
            Customer customer = customerDao.getCustomer(custId);
            Jersey jersey = jerseyDao.getJersey(jerseyId);
            if (customerDao.addJerseyToCart(customer, jersey) != null) {
                // ArrayList<Jersey> cart = customerDao.addJerseyToCart(customer, jersey);
                return new ResponseEntity<Customer>(customer,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the {@linkplain Customer customer} cart with the provided {@linkplain Jersey jersey} object, if it exists
     * 
     * @param custId The {@link Customer customer} who's cart will be added to
     * @param jerseyId The {@link Jersey jersey} to add to the cart
     * 
     * @return ResponseEntity with updated {@link <ArrayList<Jersey>> jersey} object and HTTP status of OK if added<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("/remove/{custId}/{jerseyId}")
    public ResponseEntity<Customer> removeJerseyFromCart(@PathVariable int custId, @PathVariable int jerseyId) {
        LOG.info("PUT /customers/remove/" + custId + "/" + jerseyId);
        try {
            Customer customer = customerDao.getCustomer(custId);
            Jersey jersey = jerseyDao.getJersey(jerseyId);
            if (customerDao.removeFromCart(customer, jersey) != null) {
                // ArrayList<Jersey> cart = customerDao.addJerseyToCart(customer, jersey);
                return new ResponseEntity<Customer>(customer,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cost/{id}")
    public ResponseEntity<Double> getTotalCost(@PathVariable int id) {
        LOG.info("GET /customers/cost/" + id);
        try {
            Customer customer = customerDao.getCustomer(id);
            double totalCost = customerDao.getTotalCost(customer);
            if (customer != null)
                return new ResponseEntity<Double>(totalCost,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Empties the {@linkplain Customer customer} cart if it exists
     * 
     * @param custId The {@link Customer customer} who's cart will be emptied
     * 
     * @return ResponseEntity with updated {@link <ArrayList<Jersey>> jersey} object and HTTP status of OK if added<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> emptyCart(@PathVariable int id) {
        LOG.info("PUT /customers/" + id);
        try {
            Customer customer = customerDao.getCustomer(id);
            Customer updated = customerDao.emptyCart(customer);
            if (updated != null) {
                customerDao.emptyCart(customer);
                return new ResponseEntity<Customer>(customer,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
