package com.estore.api.estoreapi.controller;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estore.api.estoreapi.model.Cart;
import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.persistence.CartDAO;

import com.estore.api.estoreapi.persistence.JerseyDAO;


/**
 * @author Angela Ngo
 */
@RestController
@RequestMapping("cart")
public class CartController {
    private static final Logger LOG = Logger.getLogger(CartController.class.getName());
    private CartDAO cartDAO; 
    private JerseyDAO jerseyDAO; 

    public CartController(CartDAO cartDAO, JerseyDAO jerseyDAO){
        this.cartDAO = cartDAO;
        this.jerseyDAO = jerseyDAO; 
    }

    @PostMapping("")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart){
        LOG.info("POST /cart " + cart);
        try{
            Cart createdCart = cartDAO.createNewCart(cart); 
            if(createdCart != null){
                Cart newCart = cartDAO.createNewCart(cart); 
                return new ResponseEntity<Cart>(newCart, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(HttpStatus.CONFLICT); 
            }

        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/decrement/")
    public ResponseEntity<Cart> decrementJerseyTypeAmount(@RequestParam int cartId, @RequestParam int jerseyId){
        LOG.info("PUT /cart/decrement/?cart=" + cartId + "&jerseyId=" + jerseyId); 
        try{
            Jersey jersey = jerseyDAO.getJersey(jerseyId);
            Cart result = cartDAO.decrementJerseyTypeAmount(cartId, jersey); 
            if(result!=null){
                return new ResponseEntity<Cart>(result, HttpStatus.OK); 
            }else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/increment/")
    public ResponseEntity<Cart> addJerseyToCart(@RequestParam int cartId, @RequestParam int jerseyId){
        LOG.info("PUT /cart/increment/?cart=" + cartId + "&jerseyId=" + jerseyId); 
        try{
            Jersey jersey = jerseyDAO.getJersey(jerseyId);
            Cart result = cartDAO.addJerseyToCart(cartId, jersey); 
            if( result != null){
                return new ResponseEntity<Cart>(result, HttpStatus.OK); 
            }else{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }

    @PutMapping("/deleteJerseyType/")
    public ResponseEntity<Cart> deleteJerseyType(@RequestParam int cartId, @RequestParam int jerseyId){
        LOG.info("PUT /cart/deleteJerseyType/?cart=" + cartId + "&jerseyId=" + jerseyId); 
        try{
            Jersey jersey = jerseyDAO.getJersey(jerseyId);
            Cart result =  cartDAO.deleteEntireJerseyFromCart(cartId, jersey); 
            if(result != null){
                return new ResponseEntity<Cart>(result, HttpStatus.OK); 
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }

    @PutMapping("/deleteEntireCart/")
    public ResponseEntity<Cart> deleteEntireCart(@RequestParam int cartId){
        LOG.info("PUT /cart/deleteJerseyType/?cart=" + cartId ); 
        try{
            Cart result = cartDAO.deleteEntireCart(cartId); 
            if(result != null){
                return new ResponseEntity<Cart>(result,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }
        }catch(IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getSpecificCart(@PathVariable int cartId){
        LOG.info("GET /cart/" + cartId);
        try{
            Cart cart = cartDAO.getSpecificCart(cartId); 
            if(cart != null){
                return new ResponseEntity<Cart>(cart, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
            }
        }catch(IOException e){
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}
