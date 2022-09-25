package com.estore.api.estoreapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.estore.api.estoreapi.model.Jersey;
import com.estore.api.estoreapi.persistence.JerseyDAO;
import com.estore.api.estoreapi.persistence.JerseyFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@org.junit.jupiter.api.Tag("Controller-tier")
public class JerseyControllerTest {
    private JerseyController jerseycontroller;
    private JerseyFileDAO mockJerseyFileDAO;
    Jersey[] testJerseys;
    ObjectMapper mockObjectMapper;
    
    public JerseyControllerTest() { /* compiled code */ }

    @org.junit.jupiter.api.BeforeEach
    public void setupJerseyController() throws IOException { 
        mockObjectMapper = mock(ObjectMapper.class);
        testJerseys = new Jersey[3];
        testJerseys[0] = new Jersey(99, "Jack Hughes", 5, 99.99, "Black", "Medium","Image.png");
        testJerseys[1] = new Jersey(100, "Poopy someone", 1, 50.99, "Red", "Size","Image.png");
        testJerseys[2] = new Jersey(101, "Patrick Kane", 88, 129.99, "Red", "Large","Image.png");

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the hero array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Jersey[].class))
                .thenReturn(testJerseys);
                mockJerseyFileDAO = new JerseyFileDAO("doesnt_matter.txt",mockObjectMapper);
     }

    @org.junit.jupiter.api.Test
    public void testGetJersey() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testGetJerseyNotFound() throws java.lang.Exception { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testGetJerseyHandleException() throws java.lang.Exception { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testCreateJersey() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testCreateJerseyFailed() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testCreateJerseyHandleException() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testUpdateJersey() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testUpdateJerseyFailed() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testUpdateJerseyHandleException() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testGetJerseys() throws java.io.IOException {}


    @org.junit.jupiter.api.Test
    public void testGetJerseysHandleException() throws java.io.IOException { /* compiled code */ }

    // @org.junit.jupiter.api.Test
    // public void testSearchJerseys() throws java.io.IOException { /* compiled code */ }
    @org.junit.jupiter.api.Test
    public void testSearchName()throws java.io.IOException{
        ResponseEntity<Jersey[]> a = jerseycontroller.searchJerseyName("Patrick Kane");
        Jersey[] jersey= a.getBody();
        assertEquals("Patrick Kane", jersey[0].getName());
        assertEquals(1, jersey.length);
    }

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

    // @org.junit.jupiter.api.Test
    // public void testDeleteJerseys() throws java.io.IOException { 
    //     ResponseEntity<Jersey[]> a = jerseycontroller.searchJerseySize("Large");
    //     Jersey[] jersey= a.getBody();
    //     assertEquals("Patrick Kane", jersey[0].getName());
    //     assertEquals(1, jersey.length);
    //     assertEquals(88, jersey[0].getNumber());
    // }

    @org.junit.jupiter.api.Test
    public void testDeleteJerseyNotFound() throws java.io.IOException { /* compiled code */ }

    @org.junit.jupiter.api.Test
    public void testDeleteJerseyHandleException() throws java.io.IOException { /* compiled code */ }
}
