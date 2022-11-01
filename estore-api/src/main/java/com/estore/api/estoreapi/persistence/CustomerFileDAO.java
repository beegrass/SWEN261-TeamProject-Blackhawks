package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Implements the functionality for JSON file-based peristance for Cart
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Angela Ngo
 */
@Component
public class CustomerFileDAO implements CustomerDAO{
    private TreeMap<Integer, Customer> allCustomers; // integer represents the id of the user 
    
    private ObjectMapper objectMapper;  // Provides conversion between jersey
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new jersey
    private String filename;    // Filename to read from and write to@Override
	private CartFileDAO cartFileDAO;
    public CustomerFileDAO(@Value("${customers.file}")String filename, ObjectMapper objectMapper, CartFileDAO cartFileDao) throws IOException{
        this.filename = filename; 
        this.objectMapper = objectMapper; 
        this.cartFileDAO = cartFileDao; 
        load(); 
    }
   
    /**
     *  returns the next id in json file 
     * @return id 
     */ 
    private synchronized static int nextId(){
        int id = nextId; 
        ++nextId; 
        return id;
    }

    private boolean load() throws IOException{
    
        nextId = 0; 
        Customer [] customerArray = objectMapper.readValue(new File(filename), Customer[].class);
        allCustomers = new TreeMap<>(); 
        for(Customer customer : customerArray){
            allCustomers.put(customer.getUserId(), customer);
            if(customer.getUserId() > nextId){
                nextId = customer.getUserId(); 
            }
        }
        ++nextId; 
        return true; 

    }

    private boolean save() throws IOException {
        Customer [] customerArray = getCustomerArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),customerArray);
        return true;
    }

    public Customer[] getCustomerArray(){
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        for(Customer customer : allCustomers.values()){
            customerArrayList.add(customer); 
        }
        Customer[] customerArray = new Customer[customerArrayList.size()];
        customerArrayList.toArray(customerArray);
        return customerArray; 
    }

    /**
     * returns the cart of that specific user
     * @param userId - the id of the user
     */
    @Override
    public Cart getCart(int userId) {
        if(allCustomers.containsKey(userId) == false){
            return null; 
        }else{
            return allCustomers.get(userId).getUsersCart();
        }
    }

    /**
     * returns the customer of that id 
     * @param userId - the id of the user
     */
    @Override
    public Customer getSpecificCustomer(int userId) throws IOException{
        if(allCustomers.containsKey(userId) == false){
            return null; 
        }else{
            return allCustomers.get(userId); 
        }
    }

    /**
     * makes a new customer 
     * @param customer - the new customer to be added to json 
     * @throws IOException
     */
    @Override 
    public Customer createNewCustomer(Customer customer) throws IOException{
        synchronized(allCustomers){
            String username = customer.getUsername();
            int id = nextId(); 
            boolean userType = false; 
            Cart cart = customer.getUsersCart(); 
            Customer newCustomer = new Customer(username, userType, cart, id);
            allCustomers.put(newCustomer.getUserId(), newCustomer);
            save();
            return newCustomer; 
        }
    }

    // /**
    //  * should there be an update customer cart where they can add to their cart (like seperate methods for that?)
    //  */
    // @Override 
    // public Customer updateCustomerCart(int userId, Cart cart){
    //     synchronized(allCustomers){
    //         if(allCustomers.containsKey(userId) == false){
    //             return null; 
    //         }else{
    //             Customer customer = allCustomers.get(userId);
    //             customer.updateCart(cart); // when the changes are made to the cart 
    //         }
    //     }
    // }


    public Customer addToCart(int userId, Jersey jersey) throws IOException{
        synchronized(allCustomers){
            if(allCustomers.containsKey(userId) == false){
                return null;
            }else{
                Customer customer = allCustomers.get(userId);
                Cart custCart = customer.getUsersCart();
                cartFileDAO.addJerseyToCart(custCart.getId(), jersey); // is this updating the cart object or do i have to write an update func
                Cart updated = cartFileDAO.getSpecificCart(custCart.getId());
                customer.updateCart(updated);
                allCustomers.put(userId,customer); 
                save(); 
                return customer; 
                
            }

        }
    }

    public Customer deleteEntireJerseyFromCart(int userId, Jersey jersey) throws IOException{
        synchronized(allCustomers){
            if(allCustomers.containsKey(userId) == false){
                return null;
            }else{
                Customer customer = allCustomers.get(userId); 
                Cart custCart = customer.getUsersCart();
                cartFileDAO.deleteEntireJerseyFromCart(custCart.getId(), jersey);
                Cart updated = cartFileDAO.getSpecificCart(custCart.getId());
                customer.updateCart(updated);
                allCustomers.put(userId,customer); 
                save(); 
                return customer; 
            }
        }
    }

    public Customer decrementJerseyTypeAmount(int userId, Jersey jersey) throws IOException{
        synchronized(allCustomers){
            if(allCustomers.containsKey(userId) == false){
                return null;
            }else{
                Customer customer = allCustomers.get(userId); 
                Cart custCart = customer.getUsersCart();
                cartFileDAO.decrementJerseyTypeAmount(custCart.getId(), jersey.getId());
                Cart updated = cartFileDAO.getSpecificCart(custCart.getId());
                customer.updateCart(updated);
                allCustomers.put(userId,customer); 
                save(); 
                return customer;
            }
        }
    }

    public Customer deleteEntireCart(int userId, Jersey jersey) throws IOException{
        synchronized(allCustomers){
            if(allCustomers.containsKey(userId) == false){
                return null;
            }else{
                Customer customer = allCustomers.get(userId); 
                Cart custCart = customer.getUsersCart();
                cartFileDAO.deleteEntireCart(custCart.getId());//(custCart.getId(), jersey.getId());
                Cart updated = cartFileDAO.getSpecificCart(custCart.getId());
                customer.updateCart(updated);
                allCustomers.put(userId,customer); 
                save(); 
                return customer;
            }
        }
    }
}