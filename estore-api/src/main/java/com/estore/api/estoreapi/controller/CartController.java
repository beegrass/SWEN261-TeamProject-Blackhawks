package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public ResponseEntity<Cart> addNewJerseyToCart(@RequestBody Cart cart, Jersey jersey){
        LOG.info("GET /cart " + jersey );

        ResponseEntity<Jersey> getJerseyStatusCode = jerseyController.getJersey(jersey.getId());
        if(getJerseyStatusCode.getStatusCode() == HttpStatus.OK){
            cartDAO.addJerseyToCart(jersey);
            return new ResponseEntity<Cart>(cart, HttpStatus.OK);

        }else if(getJerseyStatusCode.getStatusCode() == HttpStatus.NOT_FOUND){
            return new ResponseEntity<Cart>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Cart> (HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    
    }   

    @DeleteMapping("")
    public ResponseEntity<Cart> deleteTypeJerseyFromCart(@RequestBody Cart cart, Jersey jersey){
        LOG.info("DELETE /cart " + jersey); 

        boolean is_valid = cartDAO.deleteEntireJerseyFromCart(jersey);
        if(is_valid){
            return new ResponseEntity<Cart>(cart, HttpStatus.OK); 
        }else{
            return new ResponseEntity<Cart>( HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Cart> decrementJerseyTypeCart(@RequestBody Cart cart, Jersey jersey){
        boolean is_valid = cartDAO.decrementJerseyTypeAmount(jersey); 
        if(is_valid){
            return new ResponseEntity<Cart>(cart, HttpStatus.OK);
        }else{
            return new ResponseEntity<Cart>( HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Cart> incrementJerseyTypeCart(@RequestBody Cart cart, Jersey jersey){
        
        boolean is_valid;
        return new ResponseEntity<Cart>(cart, HttpStatus.OK); 
       
    }

    public ResponseEntity<Cart> deleteEntireCart(@RequestBody Cart cart){
        try{
            boolean is_valid = cartDAO.deleteEntireCart(); 
            if(is_valid){
                return new ResponseEntity<Cart>(cart, HttpStatus.OK);
            }else{
                return new ResponseEntity<Cart>( HttpStatus.NOT_FOUND);
            }
        }catch (IOException e){
            return new ResponseEntity<Cart> (HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
}


