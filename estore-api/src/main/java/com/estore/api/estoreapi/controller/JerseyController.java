package com.estore.api.estoreapi.controller;

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
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.client.HttpClientErrorException.NotFound;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.JerseyDAO;
import com.estore.api.estoreapi.model.Jersey;

/**
 * Handles the REST API requests for the Jersey resource
 * <p>
 * {@literal @}RestController Spring annotation identifies this class as a REST API
 * method handler to the Spring framework
 * 
 * @author Hayden Cabral
 */

@RestController
@RequestMapping("jerseys")
public class JerseyController {
    private static final Logger LOG = Logger.getLogger(JerseyController.class.getName());
    private JerseyDAO jerseyDao;

    /**
     * Creates a REST API controller to reponds to requests
     * 
     * @param jerseyDao The {@link JerseyDao Jersey Data Access Object} to perform CRUD operations
     * <br>
     * This dependency is injected by the Spring Framework
     */
    public JerseyController(JerseyDAO jerseyDao) {
        this.jerseyDao = jerseyDao;
    }

        
        /**
     * Responds to the GET request for all {@linkplain Jersey jerseys} whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the {@link Jersey jerseys}
     * 
     * @return ResponseEntity with array of {@link Jersey jersey} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all Jersey that contain the text "ma"
     * GET http://localhost:8080/heroes/?name=ma
     * 
     * @Author Angela Ngo
     */
    @GetMapping("/")
    public ResponseEntity<Jersey[]> searchJerseyName(@RequestParam String name) {
        LOG.info("GET /jerseys/?name="+name);

        // Replace below with your implementation
        try {
            Jersey[] jerseys = jerseyDao.findJerseys(name);
            return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK); 
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    
    @GetMapping("/")
    public ResponseEntity<Jersey[]> searchJerseyNumber(@RequestParam int number) {
        LOG.info("GET /jerseys/?number="+number);

        // Replace below with your implementation
        String numString = String.valueOf(number);
        try {
            Jersey[] jerseys = jerseyDao.findJerseys(numString);
            return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK); 
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping("/")
    public ResponseEntity<Jersey[]> searchJerseyColor(@RequestParam String color) {
        LOG.info("GET /jerseys/?color="+color);

        try {
            Jersey[] jerseys = jerseyDao.findJerseys(color);
            return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK); 
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }


    @GetMapping("/")
    public ResponseEntity<Jersey[]> searchJerseyPrice(@RequestParam Double price) {
        LOG.info("GET /jerseys/?price="+price);
        String numString = String.valueOf(price);
        try {
            Jersey[] jerseys = jerseyDao.findJerseys(numString);
            return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK); 
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @GetMapping("/")
    public ResponseEntity<Jersey[]> searchJerseySize(@RequestParam String size) {
        LOG.info("GET /jerseys/?size="+ size);
      
        try {
            Jersey[] jerseys = jerseyDao.findJerseys(size);
            return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK); 
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }


    
}

