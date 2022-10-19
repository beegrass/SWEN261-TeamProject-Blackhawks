package com.estore.api.estoreapi.model;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Ethan Abbate
 * A class that represents a User in the Estore system
 */
public class User {

    private static final Logger LOG = Logger.getLogger(Jersey.class.getName());
    
    @JsonProperty("userCart") private Cart cart;
    @JsonProperty("username") private String username;
    @JsonProperty("typeUser") private boolean type;

    /***
     * Constructor for a User in the estore
     * @param cart - The cart that is associated with the user
     * @param username - The username of the user
     * @param type - whether the user is an admin (true) or customer (false)
     */
    public User(@JsonProperty("userCart") Cart cart, @JsonProperty("username") String username, @JsonProperty("typeUser")  boolean type) {
        this.cart = cart;
        this.username = username;
        this.type = type;
    }
}
