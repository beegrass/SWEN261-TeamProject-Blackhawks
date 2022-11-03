package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.persistence.CartDAO;
import com.estore.api.estoreapi.persistence.CustomerDAO;
import com.estore.api.estoreapi.persistence.JerseyDAO;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private static final Logger LOG = Logger.getLogger(CartController.class.getName());
    private JerseyDAO jerseyDAO; 
    private CustomerDAO customerDAO;
    
    public CustomerController(CustomerDAO customerDAO, JerseyDAO jerseyDAO){
        this.customerDAO = customerDAO; 
        this.jerseyDAO = jerseyDAO;  
    }

    @GetMapping("/cart/")
    public ResponseEntity<Cart> getCart(@RequestParam int userId){
        LOG.info("GET /customer/cart/?userId=" + userId);
        Cart cart = customerDAO.getCart(userId);
        if(cart == null){
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND); 
        }else{
            return new ResponseEntity<Cart>(cart, HttpStatus.OK);
        }
    }

    // @GetMapping("/{userId}")
    // public ResponseEntity<Customer> getSpecificCustomer(@PathVariable int userId){
    //     LOG.info("GET /customer/ "+userId);
    //     try{
    //         Customer customer = customerDAO.getSpecificCustomer(userId);
    //         if(customer == null){
    //             return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    //         }else{
    //             return new ResponseEntity<Customer>(customer,HttpStatus.OK);
    //         }
    //     }catch(IOException e){
    //         LOG.log(Level.SEVERE,e.getLocalizedMessage());
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @GetMapping("/{username}")
    public ResponseEntity<Customer> getSpecificCustomer(@PathVariable String username){
        LOG.info("GET /customer/ "+username);
        try{
            Customer customer = customerDAO.getSpecificCustomer(username);
            if(customer == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }else{
                return new ResponseEntity<Customer>(customer,HttpStatus.OK);
            }
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        LOG.info("POST /customer " + customer);
        try{
            Customer result = customerDAO.createNewCustomer(customer);
            if(result == null){
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }else{
                return new ResponseEntity<Customer>(result, HttpStatus.CREATED); 
            }
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/add/")
    public ResponseEntity<Customer> addToCart(@RequestParam int userId, @RequestParam int jerseyId){
        LOG.info("PUT /customer/add/?userId= " + userId + "&jerseyId=" + jerseyId);
        try{
            Jersey jersey = jerseyDAO.getJersey(jerseyId);
            Customer result = customerDAO.addToCart(userId, jersey); // there is an issue with how its saving the data here 
            if(result == null || jersey == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<Customer>(result, HttpStatus.OK); 
            }
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/decrement/")
    public ResponseEntity<Customer> decrementJerseyFromCart(@RequestParam int userId, @RequestParam int jerseyId){
        LOG.info("PUT /customer/decrement/?userId= " + userId + "&jerseyId=" + jerseyId);
        try{
            Jersey jersey = jerseyDAO.getJersey(jerseyId);
            if(jersey == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Customer result = customerDAO.decrementJerseyTypeAmount(userId, jersey); 
            if(result == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<Customer>(result, HttpStatus.OK); 
            }
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deleteJerseyType/")
    public ResponseEntity<Customer> deleteTypeJersey(@RequestParam int userId, @RequestParam int jerseyId){
        LOG.info("PUT /customer/deleteJerseyType/?userId= " + userId + "&jerseyId=" + jerseyId);
        try{
            Jersey jersey = jerseyDAO.getJersey(jerseyId);
            if(jersey == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Customer result = customerDAO.deleteEntireJerseyFromCart(userId, jersey);
            if(result == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<Customer>(result, HttpStatus.OK); 
            }
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deleteCart/")
    public ResponseEntity<Customer> deleteCart(@RequestParam int userId){
        LOG.info("PUT /customer/deleteCart/?userId=" + userId); 
        try{
            Customer result = customerDAO.deleteEntireCart(userId);
            if(result == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<Customer>(result, HttpStatus.OK); 
            }
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
