package com.estore.api.estoreapi.persistence;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;


import com.estore.api.estoreapi.model.Cart;

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
    private HashMap<Integer, Cart> allCarts; 

    private ObjectMapper objectMapper;  // Provides conversion between jersey
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new jersey
    private String filename;    // Filename to read from and write to@Override
	

    public CartFileDAO(@Value("${cart.file}") String filename, ObjectMapper objectMapper) throws IOException{
        this.filename = filename; 
        this.objectMapper = objectMapper; 
        load(); 
    }

    private synchronized static int nextId(){
        int id = nextId; 
        nextId++; 
        return id;
    }

    private boolean load() throws IOException{
        File file = new File(filename); 
        nextId = 0; 
        Cart [] cartArray = objectMapper.readValue(file, Cart[].class);
        allCarts = new HashMap<>(); 
        for(Cart cart : cartArray){
            allCarts.put(cart.getId(), cart);
            if(cart.getId() > nextId){
                nextId = cart.getId(); 
            }
        }
        ++nextId; 
        return true; 

    }

    private boolean save() throws IOException {
        Cart [] cartArray = getCartsArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),cartArray);
        return true;
    }

    /**
     * returns an array of the current carts 
     * @return cartArray
     */
    public Cart[] getCartsArray(){
        ArrayList<Cart> cartArrayList = new ArrayList<>();
        for(Cart cart : allCarts.values()){
            cartArrayList.add(cart); 
        }
        Cart[] cartArray = new Cart[cartArrayList.size()];
        cartArrayList.toArray(cartArray);
        return cartArray; 
    }

    /**
     * gets the cart hashMap not the object
     * @param cartId - id of the cart 
     */
    @Override
    public HashMap<Jersey, Integer> getEntireCart(int cartId) throws IOException {
        return allCarts.get(cartId).getEntireCart(); 
    }

    /**
     * Returns the specific object cart from the 
     * @param cartId
     * @return Cart
     */
    public Cart getSpecificCart(int cartId){
        return allCarts.get(cartId); 
    }

    /**
     * This decrements the quantity of a certain jersey from a given jersey and cartId 
     * @param cartId - the id of the cart wanted to select 
     * @param jersey - the jersey wanted to decrement from the quantity
     */
    @Override
    public boolean decrementJerseyTypeAmount(int cartId, Jersey jersey) throws IOException {
        Cart cart = getSpecificCart(cartId); 
        boolean isDecremented; 
        synchronized(cart){
            isDecremented = cart.decrementJerseyTypeFromCart(jersey);
            save();
        }
        return isDecremented; 
    }

    @Override
    public boolean addJerseyToCart(int cartId, Jersey jersey) throws IOException {
        Cart cart = getSpecificCart(cartId); 
        boolean isAdded; 
        synchronized(cart){
            isAdded = cart.addJerseyToCart(jersey); 
            save();
        }
        return isAdded; 
    }

    @Override
    public boolean deleteEntireJerseyFromCart(int cartId, Jersey jersey) throws IOException {
        Cart cart = getSpecificCart(cartId);
        boolean isDeleted;
        synchronized(cart){
            isDeleted = cart.deleteJerseyType(jersey);
            save();
        }
        return isDeleted;
    }

    @Override
    public boolean deleteEntireCart(int cartId) throws IOException {
        Cart cart = getSpecificCart(cartId);
        boolean isDeleted; 
        
        synchronized(allCarts){
            isDeleted = cart.deleteEntireCart();
            save(); 
        }
        return isDeleted;
    }

    @Override
    public Cart createNewCart(Cart cart) throws IOException {
        synchronized(cart){
            Cart newCart = new Cart(cart.getEntireCart(), nextId());
            allCarts.put(newCart.getId(), newCart);
            save(); 
            return newCart;
        }
    }

    



}