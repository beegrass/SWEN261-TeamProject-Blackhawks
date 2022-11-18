package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import com.estore.api.estoreapi.model.Customer;
import com.estore.api.estoreapi.model.Jersey;

@Component
public class CustomerFileDAO implements CustomerDAO {
    private static final Logger LOG  = Logger.getLogger(CustomerFileDAO.class.getName());
    Map<Integer,Customer> customers;   // Provides a local cache of the customer objects
                                    // so that we don't need to read from the file
                                    // each time
    private ObjectMapper objectMapper;  // Provides conversion between customer
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new customer
    private String filename;    // Filename to read from and write to

    public CustomerFileDAO(@Value("${customers.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Generates the next id for a new {@linkplain Customer customer}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Customer customers} from the tree map
     * 
     * @return  The array of {@link Customer customers}, may be empty
     */
    private Customer[] getCustomersArray() {
        return getCustomersArray(null, null);
    }

    /**
     * Generates an array of {@linkplain Customer customers} from the tree map for any
     * {@linkplain Customer customers} that contains the text specified by information 
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Customer customers}
     * in the tree map
     * 
     * Searches based on one query information (can be a string version of a number or double as well)
     * @return  The array of {@link Customer customers}, may be empty
     */
    private Customer[] getCustomersArray(String username, ArrayList<Jersey> cart) { // if containsText == null, no filter
        ArrayList<Customer> customerArrayList = new ArrayList<>();

        for (Customer customer : customers.values()) {

            if ((username == null || customer.getUserName().contains(username)) &&
                (cart == null || customer.getCart().equals(cart)))
                customerArrayList.add(customer);
        }

        Customer[] customerArray = new Customer[customerArrayList.size()];
        for(int i = 0; i < customerArrayList.size(); i++){
            customerArray[i] = customerArrayList.get(i);
        }
        // customerArrayList.toArray(customerArray);
        return customerArray;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Customer[] findCustomers(String username, ArrayList<Jersey> cart) throws IOException {
        synchronized(customers) {
            return getCustomersArray(username, cart);
        }
    }

    /**
     * Saves the {@linkplain Customer customers} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Customer customers} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Customer[] customerArray = getCustomersArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),customerArray);
        return true;
    }

    /**
     * Loads {@linkplain Customer customer} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        customers = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of customers
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Customer[] customerArray = objectMapper.readValue(new File(filename),Customer[].class);

        // Add each jersey to the tree map and keep track of the greatest id
        for (Customer customer : customerArray) {
            customers.put(customer.getId(),customer);
            if (customer.getId() > nextId)
                nextId = customer.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    @Override
    public Customer[] getCustomers() throws IOException {
        synchronized(customers) {
            return getCustomersArray();
        }
    }

    @Override
    public Customer getCustomer(int id) throws IOException {
        synchronized(customers) {
            if (customers.containsKey(id))
                return customers.get(id);
            else
                return null;
        }
    }

    @Override
    public Jersey[] getCart(int id) throws IOException {
        synchronized(customers) {
            if (customers.containsKey(id)){
                Customer customer = customers.get(id);
                ArrayList<Jersey> cart = customer.getCart();
                Jersey[] arrCart = new Jersey[cart.size()];
                arrCart = cart.toArray(arrCart);
                return arrCart;
            }
            else
                return null;
        }
    }

    @Override
    public Customer createCustomer(Customer customer) throws IOException {
        load();
        synchronized(customers) {
            for(Customer cust : getCustomersArray())
            {
                if(cust.getUserName().toLowerCase().equals(customer.getUserName().toLowerCase()))
                    return null;
            }
            // We create a new jersey object because the id field is immutable
            // and we need to assign the next unique id
            Customer newCustomer = new Customer(nextId(), customer.getUserName());
            customers.put(newCustomer.getId(), newCustomer);
            save(); // may throw an IOException
            return newCustomer;
        }
    }

    @Override
    public Customer addJerseyToCart(Customer customer, Jersey jersey) throws IOException {
        load();
        synchronized(customers) { 
            if (customers.containsKey(customer.getId()) == false)
                return null;  // customer does not exist

                customer.addToCart(jersey);
                customers.put(customer.getId(),customer);
            save(); // may throw an IOException
            return customer;
        }
    }

    @Override
    public Customer removeFromCart(Customer customer, Jersey jersey) throws IOException {
        load();
        synchronized(customers) { 
            if (customers.containsKey(customer.getId()) == false)
                return null;  // customer does not exist

                customer.removeFromCart(jersey);
                customers.put(customer.getId(),customer);
            save(); // may throw an IOException
            return customer;
        }
    }

    @Override
    public Customer emptyCart(Customer customer) throws IOException {
        load(); 
        synchronized(customers) {
            if (customers.containsKey(customer.getId()) == false)
                return null;  // customer does not exist

                customer.emptyCart();
                customers.put(customer.getId(),customer);
            save(); // may throw an IOException
            return customer;
        }
    }

    @Override
    public double getTotalCost(Customer customer) throws IOException {
        load();
        synchronized(customers) { 
            double totalCost = customer.getTotalCost(); 
            return totalCost;
        }
    }

    
    
}
