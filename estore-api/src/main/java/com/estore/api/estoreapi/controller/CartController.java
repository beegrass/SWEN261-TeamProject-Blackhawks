package com.estore.api.estoreapi.controller;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.persistence.CartDAO;

@RestController
@RequestMapping("cart")
public class CartController {
    private static final Logger LOG = Logger.getLogger(JerseyController.class.getName());
    private CartDAO cartDAO;
    private JerseyController jerseyController; 
    public CartController(CartDAO cartDAO, JerseyController jerseyController){
        this.cartDAO = cartDAO; 
        this.jerseyController = jerseyController; 
    }

    @GetMapping("")
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart, Jersey jersey){
        LOG.info("GET /cart " + jersey );

        try{
            ResponseEntity<Jersey> getJerseyStatusCode = jerseyController.getJersey(jersey.getId()); 
            /*
             * TO DO: use the response code to see if youre going to be able to put the jersey into the cart 
             * not sure of when to return some sort of error code; should talk to vince about that formatting 
             */
        }


        ResponseEntity<Jersey> statusCode = jerseyController.getJersey(jersey.getId()); 
        if(statusCode.getStatusCode() == HttpStatus.NOT_FOUND ||statusCode.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR){
            return new ResponseEntity<>(Ht)
        }
    }
}
