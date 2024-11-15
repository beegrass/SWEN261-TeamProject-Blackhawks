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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void testUpdateJerseyFailed() throws java.io.IOException { 
        // setup
        Jersey jersey = new Jersey(372891, "GA", 100, 9.99, "red", "XL", "Image.png");
        when(mockJerseyDAO.updateJersey(jersey)).thenReturn(null);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.updateJersey(jersey);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testUpdateJerseyHandleException() throws java.io.IOException {
        Jersey jersey = new Jersey(5, "handle", 100, 9.99, "red", "XL", "Image.png");
        
        doThrow(new IOException()).when(mockJerseyDAO).updateJersey(jersey);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.updateJersey(jersey);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testGetJerseys() throws java.io.IOException {
        // Setup
        Jersey[] jerseys = new Jersey[2];
        jerseys[0] = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        jerseys[1] = new Jersey(100,"Patrick Kane", 88, 129.99, "Red", "Large", "image1.png");
        // When getHeroes is called return the heroes created above
        when(mockJerseyDAO.getJerseys()).thenReturn(jerseys);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.getJerseys();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(jerseys,response.getBody());
    }

    @Test
    public void testGetJerseysNotFound() throws java.io.IOException {
        // Setup
        // When getHeroes is called on the Mock Hero DAO, throw an IOException
        when(mockJerseyDAO.getJerseys()).thenReturn(null);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.getJerseys();

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
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

    @Test
    public void testSearchJerseysName()throws java.io.IOException{
        // Setup
        String searchString = "Pat";
        Jersey[] jerseys = new Jersey[2];
        jerseys[0] = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        jerseys[1] = new Jersey(100,"Patrick Kane", 88, 129.99, "Red", "Large", "image1.png");
        // When findHeroes is called with the search string, return the two
        /// heroes above
        when(mockJerseyDAO.findJerseys(searchString, 0, 0.0, null, null)).thenReturn(jerseys);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseyName(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(jerseys,response.getBody());
    }

    @Test
    public void testSearchJerseysNameHandleException() throws IOException { // findJerseys may throw IOException
        // Setup
        String searchString = "an";
        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockJerseyDAO).findJerseys(searchString, 0, 0.0, null, null);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseyName(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchJerseysNumber()throws java.io.IOException{
        // Setup
        int searchNum = 88;
        Jersey[] jerseys = new Jersey[2];
        jerseys[0] = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        jerseys[1] = new Jersey(100,"Patrick Kane", 88, 129.99, "Red", "Large", "image1.png");
        // When findHeroes is called with the search string, return the two
        /// heroes above
        when(mockJerseyDAO.findJerseys(null, searchNum, 0.0, null, null)).thenReturn(jerseys);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseyNumber(searchNum);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(jerseys,response.getBody());
    }

    @Test
    public void testSearchJerseysNumberHandleException() throws IOException { // findJerseys may throw IOException
        // Setup
        int searchNum = 88;
        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockJerseyDAO).findJerseys(null, searchNum, 0.0, null, null);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseyNumber(searchNum);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchJerseysPrice()throws java.io.IOException{
        // Setup
        double searchDouble = 129.99;
        Jersey[] jerseys = new Jersey[2];
        jerseys[0] = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        jerseys[1] = new Jersey(100,"Patrick Kane", 88, 129.99, "Red", "Large", "image1.png");
        // When findHeroes is called with the search string, return the two
        /// heroes above
        when(mockJerseyDAO.findJerseys(null, 0, searchDouble, null, null)).thenReturn(jerseys);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseyPrice(searchDouble);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(jerseys,response.getBody());
    }

    @Test
    public void testSearchJerseysPriceHandleException() throws IOException { // findJerseys may throw IOException
        // Setup
        double searchDouble = 88;
        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockJerseyDAO).findJerseys(null, 0, searchDouble, null, null);
        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseyPrice(searchDouble);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchJerseysColor()throws java.io.IOException{
        // Setup
        String searchString = "Red";
        Jersey[] jerseys = new Jersey[2];
        jerseys[0] = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        jerseys[1] = new Jersey(100,"Patrick Kane", 88, 129.99, "Red", "Large", "image1.png");
        // When findHeroes is called with the search string, return the two
        /// heroes above
        when(mockJerseyDAO.findJerseys(null, 0, 0.0, searchString, null)).thenReturn(jerseys);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseyColor(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(jerseys,response.getBody());
    }

    @Test
    public void testSearchJerseysColorHandleException() throws IOException { // findJerseys may throw IOException
        // Setup
        String searchString = "an";
        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockJerseyDAO).findJerseys(null, 0, 0.0, searchString, null);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseyColor(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testSearchJerseysSize()throws java.io.IOException{
        // Setup
        String searchString = "Medium";
        Jersey[] jerseys = new Jersey[2];
        jerseys[0] = new Jersey(99,"Marc-Andre Fleury", 29, 129.99, "Red", "Large", "image1.png");
        jerseys[1] = new Jersey(100,"Patrick Kane", 88, 129.99, "Red", "Large", "image1.png");
        // When findHeroes is called with the search string, return the two
        /// heroes above
        when(mockJerseyDAO.findJerseys(null, 0, 0.0, null, searchString)).thenReturn(jerseys);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseySize(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(jerseys,response.getBody());
    }

    @Test
    public void testSearchJerseysSizeHandleException() throws IOException { // findJerseys may throw IOException
        // Setup
        String searchString = "an";
        // When createHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockJerseyDAO).findJerseys(null, 0, 0.0, null, searchString);

        // Invoke
        ResponseEntity<Jersey[]> response = jerseyController.searchJerseySize(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    @Test
    public void testDeleteJerseys() throws java.io.IOException { 
        // Setup
        int jerseyId = 99;
        // when deleteJersey is called return true, simulating successful deletion
        when(mockJerseyDAO.deleteJersey(jerseyId)).thenReturn(true);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.deleteJersey(jerseyId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void testDeleteJerseyNotFound() throws IOException { // deleteHero may throw IOException
        // Setup
        int jerseyId = 99;
        // when deleteHero is called return false, simulating failed deletion
        when(mockJerseyDAO.deleteJersey(jerseyId)).thenReturn(false);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.deleteJersey(jerseyId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testDeleteJerseyHandleException() throws java.io.IOException { // Setup
        int jerseyId = 99;
        // When deleteHero is called on the Mock Hero DAO, throw an IOException
        doThrow(new IOException()).when(mockJerseyDAO).deleteJersey(jerseyId);

        // Invoke
        ResponseEntity<Jersey> response = jerseyController.deleteJersey(jerseyId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());}
}
