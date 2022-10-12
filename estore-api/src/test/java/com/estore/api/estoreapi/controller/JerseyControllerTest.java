package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.JerseyDAO;
import com.estore.api.estoreapi.model.Jersey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Jersey Controller class
 * 
 * @author Ethan Abbate, Hayden Cabral, Angela Ngo, Vincent Schwartz
 */

@Tag("Controller-tier")
public class JerseyControllerTest {
    private JerseyController jerseyController;
    private JerseyDAO mockJerseyDAO;

    /**
     * Before each test, create a new JerseyController object and inject
     * a mock Jersey DAO
     */
    @BeforeEach
    public void setupJerseyController() { 
        mockJerseyDAO = mock(JerseyDAO.class);
        jerseyController = new JerseyController(mockJerseyDAO);
     }

    @Test
    public void testGetJersey() throws java.io.IOException {// getJersey may throw IOException
        // Setup
        Jersey jersey = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        // When the same id is passed in, our mock Hero DAO will return the Hero object
        when(mockJerseyDAO.getJersey(jersey.getId())).thenReturn(jersey);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.getJersey(jersey.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(jersey,response.getBody());}

    @org.junit.jupiter.api.Test
    public void testGetJerseyNotFound() throws java.lang.Exception { 
        int jerseyId = 99;

        when(mockJerseyDAO.getJersey(jerseyId)).thenReturn(null);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.getJersey(jerseyId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testGetJerseyHandleException() throws java.lang.Exception { 
        // Setup
        int jerseyId = 99;
        // When getHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockJerseyDAO).getJersey(jerseyId);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.getJersey(jerseyId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @org.junit.jupiter.api.Test
    public void testCreateJersey() throws java.io.IOException { 
        // Setup
        Jersey jersey = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        // when createHero is called, return true simulating successful
        // creation and save
        when(mockJerseyDAO.createJersey(jersey)).thenReturn(jersey);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.createJersey(jersey);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(jersey,response.getBody());
    }

    @org.junit.jupiter.api.Test
    public void testCreateJerseyFailed() throws java.io.IOException { 
        // Setup
        Jersey jersey = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        // when createHero is called, return false simulating failed
        // creation and save
        when(mockJerseyDAO.createJersey(jersey)).thenReturn(null);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.createJersey(jersey);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }

    @org.junit.jupiter.api.Test
    public void testCreateJerseyHandleException() throws java.io.IOException { 
        // Setup
        Jersey jersey = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");

        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockJerseyDAO).createJersey(jersey);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.createJersey(jersey);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @org.junit.jupiter.api.Test
    public void testUpdateJersey() throws java.io.IOException {
        // Setup
        Jersey jersey = new Jersey(1, "GA", 100, 9.99, "red", "XK", "Image.png");
        when(mockJerseyDAO.updateJersey(jersey)).thenReturn(jersey);
        ResponseEntity<Jersey> response = jerseyController.updateJersey(jersey);
        jersey.setColor("blue");

        // Invoke
        response = jerseyController.updateJersey(jersey);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(jersey,response.getBody());
    }

    @org.junit.jupiter.api.Test
    public void testUpdateJerseyFailed() throws java.io.IOException { 
        // setup
        Jersey jersey = new Jersey(372891, "GA", 100, 9.99, "red", "XL", "Image.png");
        when(mockJerseyDAO.updateJersey(jersey)).thenReturn(null);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.updateJersey(jersey);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @org.junit.jupiter.api.Test
    public void testUpdateJerseyHandleException() throws java.io.IOException {
        Jersey jersey = new Jersey(5, "handle", 100, 9.99, "red", "XL", "Image.png");
        
        doThrow(new IOException()).when(mockJerseyDAO).updateJersey(jersey);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.updateJersey(jersey);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @org.junit.jupiter.api.Test
    public void testGetJerseys() throws java.io.IOException {
        // Setup
        Jersey[] heroes = new Jersey[2];
        heroes[0] = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        heroes[1] = new Jersey(100,"Patrick Kane", 88, 129.99, "Red", "Large", "image1.png");
        // When getHeroes is called return the heroes created above
        when(mockJerseyDAO.getJerseys()).thenReturn(heroes);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.getJerseys();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(heroes,response.getBody());
    }

    @Test
    public void testGetJerseysHandleException() throws java.io.IOException { 
        // Setup
        // When getHeroes is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockJerseyDAO).getJerseys();

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.getJerseys();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    // @org.junit.jupiter.api.Test
    // public void testSearchJerseys() throws java.io.IOException { /* compiled code */ }
    // @org.junit.jupiter.api.Test
    // public void testSearchName()throws java.io.IOException{
    //     ResponseEntity<Jersey[]> a = jerseycontroller.searchJerseyName("Patrick Kane");
    //     Jersey[] jersey= a.getBody();
    //     assertEquals("Patrick Kane", jersey[0].getName());
    //     assertEquals(1, jersey.length);
    // }

    // @org.junit.jupiter.api.Test
    // public void testSearchColor()throws java.io.IOException{
    //     ResponseEntity<Jersey[]> a = jerseycontroller.searchJerseyColor("Red");
    //     Jersey[] jersey= a.getBody();
    //     assertEquals(2, jersey.length); // in test file there should only be two red shirts
    //     assertEquals("Red", jersey[0].getColor());
    // }

    // @org.junit.jupiter.api.Test
    // public void testSearchNumber()throws java.io.IOException{
    //     ResponseEntity<Jersey[]> a = jerseycontroller.searchJerseyNumber(88);
    //     Jersey[] jersey= a.getBody();
    //     assertEquals("Patrick Kane", jersey[0].getName());
    //     assertEquals(1, jersey.length);
    //     assertEquals(5, jersey[0].getNumber());

    // }

    // @org.junit.jupiter.api.Test
    // public void testSearchPrice()throws java.io.IOException{
    //     ResponseEntity<Jersey[]> a = jerseycontroller.searchJerseyPrice(129.99);
    //     Jersey[] jersey= a.getBody();
    //     assertEquals("Patrick Kane", jersey[0].getName());
    //     assertEquals(1, jersey.length);
    //     assertEquals(88, jersey[0].getNumber());

    // }

    // @org.junit.jupiter.api.Test
    // public void testSearchNumber()throws java.io.IOException{
    //     ResponseEntity<Jersey[]> a = jerseycontroller.searchJerseySize("Large");
    //     Jersey[] jersey= a.getBody();
    //     assertEquals("Patrick Kane", jersey[0].getName());
    //     assertEquals(1, jersey.length);
    //     assertEquals(88, jersey[0].getNumber());

    // }



    // @org.junit.jupiter.api.Test
    // public void testSearchJerseysHandleException() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testDeleteJerseys() throws java.io.IOException { }

    @org.junit.jupiter.api.Test
    public void testDeleteJerseyNotFound() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testDeleteJerseyHandleException() throws java.io.IOException { /* compiled code */ }
}
