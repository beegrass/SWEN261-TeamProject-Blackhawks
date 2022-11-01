package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Customer;

/**
 * Defines the interface for Customers object persistance
 * @author Angela Ngo 
 */

 public interface CustomerDAO{
    //get cart ? I think thats it

    Cart getCart(int userId);
    Customer getSpecificCustomer(int userId) throws IOException; 
    Customer createNewCustomer(Customer customer)throws IOException; 
    Customer updateCustomerCart(int userId, Cart cart); 
}