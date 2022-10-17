package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Item;
import com.estore.api.estoreapi.model.Jersey;

/**
 * Implements the functionality for JSON file-based peristance for Cart
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Angela Ngo
 */
public class CartFileDAO implements CartDAO {

	private static final Logger LOG  = Logger.getLogger(JerseyFileDAO.class.getName());
    private Cart cart; 

    private ObjectMapper objectMapper;  // Provides conversion between jersey
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new jersey
    private String filename;    // Filename to read from and write to@Override
	

    public CartFileDAO(@Value("${cart.file}") String filename, ObjectMapper objectMapper, Cart cart) throws IOException{
        this.filename = filename; 
        this.objectMapper = objectMapper; 
        this.cart = cart; 
        load(); 
    }

    // private synchronized static int nextId(){
    //     int id = nextId; 
    //     nextId++; 
    //     return id;
    // }

    private boolean load() throws IOException{
        File file = new File(filename); 
        Item [] itemArray = objectMapper.readValue(file, Item[].class);
        
        for(Item item: itemArray){
            cart.getEntireCart().put(item.getJersey(), item.getQuantity()); 
        }
        return true; 
    }

    private boolean save() throws IOException {
        Item[] itemArray = getEntireCartForJsonItems(); 


        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),itemArray);
        return true;
    }

    public HashMap<Jersey, Integer> getEntireCart() throws IOException {
		return cart.getEntireCart(); 
	}

    
    public Item[] getEntireCartForJsonItems() throws IOException{
        HashMap<Jersey, Integer> cartMap = cart.getEntireCart(); 
        Item [] itemArray = new Item[cartMap.keySet().size()]; 
        int count = 0; 
        for(Jersey jersey : cartMap.keySet()){
            itemArray[count] = new Item(jersey, cartMap.get(jersey)); 
        }
        return itemArray; 
    }

	@Override
	public boolean decrementJerseyTypeAmount(Jersey jersey) throws IOException {
		boolean isDecremented;
        synchronized(cart){
            isDecremented = cart.decrementJerseyTypeFromCart(jersey);
        }
        save(); 
        return isDecremented;
	}

	@Override
	public boolean addJerseyToCart(Jersey jersey) throws IOException {
		boolean isAdded;
        synchronized(cart){
            isAdded = cart.addJerseyToCart(jersey); 
        }
        save();
        return isAdded;
	}

	@Override
	public boolean deleteEntireJerseyFromCart(Jersey jersey) throws IOException {
		boolean isDeletedEntireJersey; 
        synchronized(cart){
            isDeletedEntireJersey = cart.deleteJerseyType(jersey); 
        }
        save(); 
        return isDeletedEntireJersey; 
	}

	@Override
	public boolean deleteEntireCart() throws IOException {
		boolean isDeleted; 
        synchronized(cart){
            isDeleted = cart.deleteEntireCart();
        }
        save(); 
        return isDeleted; 
	}
    
}