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
     * Updates the {@linkplain Jersey jersey} with the provided {@linkplain Jersey jersey} object, if it exists
     * 
     * @param jersey The {@link Jersey jersey} to update
     * 
     * @return ResponseEntity with updated {@link Jersey jersey} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Jersey> updateJersey(@RequestBody Jersey jersey) {
        LOG.info("PUT /jerseys " + jersey);
        try {
            Jersey updated = jerseyDao.updateJersey(jersey);
            if (updated != null) {
                jerseyDao.updateJersey(updated);
                return new ResponseEntity<Jersey>(updated,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for a {@linkplain Jersey jersey} for the given id
     * 
     * @param id The id used to locate the {@link Jersey jersey}
     * 
     * @return ResponseEntity with {@link Jersey jersey} object and HTTP status of OK if found<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<Jersey> getJersey(@PathVariable int id) {
        LOG.info("GET /jerseys/" + id);
        try {
            Jersey jersey = jerseyDao.getJersey(id);
            if (jersey != null)
                return new ResponseEntity<Jersey>(jersey,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Responds to the GET request for all {@linkplain Jersey jersey}
     * 
     * @return ResponseEntity with array of {@link Jersey jersey} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("")
    public ResponseEntity<Jersey[]> getJerseys() {
        LOG.info("GET /jerseys");

        try {
            Jersey[] jerseys = jerseyDao.getJerseys();
            if(jerseys != null) {
                return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(jerseys, HttpStatus.NOT_FOUND);
            }
        } catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Creates a {@linkplain Jersey jersey} with the provided jersey object
     * 
     * @param jersey - The {@link Jersey jersey} to create
     * 
     * @return ResponseEntity with created {@link Jersey jersey} object and HTTP status of CREATED<br>
     * ResponseEntity with HTTP status of CONFLICT if {@link Jersey jersey} object already exists<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Jersey> createJersey(@RequestBody Jersey jersey) {
        LOG.info("POST /jerseys " + jersey);
        try {
            Jersey result = jerseyDao.createJersey(jersey);
            if(result != null)
            {
                return new ResponseEntity<Jersey>(jersey, HttpStatus.CREATED);
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
     * Example: Find all heroes that contain the text "ma"
     * GET http://localhost:8080/heroes/?name=ma
     * @Author Angela Ngo
     */
    @GetMapping("/searchByName/")
    public ResponseEntity<Jersey[]> searchJerseyName(@RequestParam String name) {
        LOG.info("GET /jerseys/searchByName/?name="+name);

        try {
            Jersey [] jerseys = jerseyDao.findJerseys(name, 0, 0.0, null, null);
            return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK); 
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    /**
     * Responds to the GET request for all {@linkplain Jersey jerseys} whose jerseys number corresponds with the given number
     * 
     * @param number The number parameter which contains the text used to find the {@link Jersey jerseys}
     * 
     * @return ResponseEntity with array of {@link Jersey jersey} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @GetMapping("/searchByNumber/")
    //@RequestMapping(value="/jerseys/?color={color}",method = RequestMethod.GET)
    public ResponseEntity<Jersey[]> searchJerseyNumber(@RequestParam int number) {
        LOG.info("GET /jerseys/searchByNumber/?number="+number);

        try {
            Jersey [] jerseys = jerseyDao.findJerseys(null, number, 0.0, null, null);
            return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK); 
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    /**
     * Responds to the GET request for all {@linkplain Jersey jerseys} whose name contains
     * the text in name
     * 
     * @param price The name parameter which contains the text used to find the {@link Jersey jerseys}
     * 
     * @return ResponseEntity with array of {@link Jersey jersey} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all heroes that contain the text "ma"
     * GET http://localhost:8080/heroes/?name=ma
     * @Author Angela Ngo
     */
    @GetMapping("/searchByPrice/")
    //@RequestMapping(value="/jerseys/?color={color}",method = RequestMethod.GET)
    public ResponseEntity<Jersey[]> searchJerseyPrice(@RequestParam double price) {
        LOG.info("GET /jerseys/searchByPrice/?price="+price);

        try {
            Jersey [] jerseys = jerseyDao.findJerseys(null, 0, price, null, null);
            return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK); 
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
   

    /**
     * Responds to the GET request for all {@linkplain Jersey jerseys} whose color contains
     * the text in color
     * 
     * @param color The color parameter which contains the text used to find the {@link Jersey jerseys}
     * 
     * @return ResponseEntity with array of {@link Jersey jersey} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all jerseys that contain the text "bl"
     * GET http://localhost:8080/jerseys/?name=ma
     * @Author Angela Ngo 
     */
    @GetMapping("/searchByColor/")
    //@RequestMapping(value="/jerseys/?color={color}",method = RequestMethod.GET)
    public ResponseEntity<Jersey[]> searchJerseyColor(@RequestParam String color) {
        LOG.info("GET /jerseys/searchByColor/?color="+color);

        try {
            Jersey [] jerseys = jerseyDao.findJerseys(null, 0, 0.0, color, null);
            return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK); 
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }


    /**
     * Responds to the GET request for all {@linkplain Jersey jerseys} whose size contains
     * the text in size
     * 
     * @param size The size parameter which contains the text used to find the {@link Jersey jerseys}
     * 
     * @return ResponseEntity with array of {@link Jersey jersey} objects (may be empty) and
     * HTTP status of OK<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * <p>
     * Example: Find all jerseys that contain the text "me"
     * GET http://localhost:8080/jerseys/?name=ma
     * 
     * @Author Angela Ngo 
     */
    @GetMapping("/searchBySize/")
    //@RequestMapping(value="/jerseys/?color={color}",method = RequestMethod.GET)
    public ResponseEntity<Jersey[]> searchJerseySize(@RequestParam String size) {
        LOG.info("GET /jerseys/searchBySize/?size="+size);

        try {
            Jersey [] jerseys = jerseyDao.findJerseys(null, 0, 0.0, null, size);
            return new ResponseEntity<Jersey[]>(jerseys, HttpStatus.OK); 
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }


    /**
     * Deletes a {@linkplain Jersey jersey} with the given id
     * 
     * @param id The id of the {@link Jersey jersey} to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * @Author Angela Ngo
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Jersey> deleteJersey(@PathVariable int id) {
        LOG.info("DELETE /jerseys/" + id);
        try {
            if(jerseyDao.deleteJersey(id)){
                return new ResponseEntity<Jersey>(jerseyDao.getJersey(id), HttpStatus.OK); 
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    


}