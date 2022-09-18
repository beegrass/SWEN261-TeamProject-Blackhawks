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
 * @author Ethan Abbate, Hayden Cabral, Angela Ngo, Vincent Schwartz
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
     * Creates a {@linkplain Jersey hero} with the provided hero object
     * 
     * @param hero - The {@link Jersey hero} to create
     * 
     * @return ResponseEntity with created {@link Jersey hero} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Jersey hero} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Jersey> createHero(@RequestBody Jersey jersey) {
        LOG.info("POST /heroes " + jersey);

        try {

            //check if a hero already exists with the given hero's name
            Jersey[] givenJersey = jerseyDao.findJerseys(jersey.getName(), jersey.getNumber(), jersey.getColor(), jersey.getImage());
            if(givenJersey.length == 0 || givenJersey == null)
            {
                Jersey newJersey = jerseyDao.createJersey(jersey);
                return new ResponseEntity<Jersey>(newJersey, HttpStatus.CREATED);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        
}
