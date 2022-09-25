package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estore.api.estoreapi.model.Jersey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the Jersey File DAO class
 * 
 * @author SWEN Faculty
 */
@Tag("Persistence-tier")
public class JerseyFileDAOTest {
    JerseyFileDAO jerseyFileDAO;
    Jersey[] testJerseys;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupJerseyFileDAO() throws IOException {
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
                jerseyFileDAO = new JerseyFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

    @Test
    public void testGetJerseys() {
        // Invoke
        Jersey[] jerseys = jerseyFileDAO.getJerseys();

        // Analyze
        assertEquals(jerseys.length,testJerseys.length);
        for (int i = 0; i < testJerseys.length;++i)
            assertEquals(jerseys[i],testJerseys[i]);
    }

    // @Test
    // public void testFindJerseys() {
    //     // Invoke
    //     Jersey[] jerseys = jerseyFileDAO.findJerseys("Patrick Kane", 88, "Red", "Image.png");

    //     // Analyze
    //     assertEquals(jerseys.length,2);
    //     assertEquals(jerseys[0],testJerseys[1]);
    //     assertEquals(jerseys[1],testJerseys[2]);
    // }

    @Test
    public void testGetJersey() throws IOException {
        // Invoke
        Jersey jersey = jerseyFileDAO.getJersey(99);

        // Analzye
        assertEquals(jersey,testJerseys[0]);
    }

    // // @Test
    // // public void testDeleteJersey() {
    // //     // Invoke
    // //     boolean result = assertDoesNotThrow(() -> jerseyFileDAO.deleteJersey(99),
    // //                         "Unexpected exception thrown");

    // //     // Analzye
    // //     assertEquals(result,true);
    // //     // We check the internal tree map size against the length
    // //     // of the test heroes array - 1 (because of the delete)
    // //     // Because heroes attribute of HeroFileDAO is package private
    // //     // we can access it directly
    // //     assertEquals(heroFileDAO.heroes.size(),testHeroes.length-1);
    // // }

    // @Test
    // public void testCreateJersey() throws IOException {
    //     // Setup
    //     Jersey jersey = new Jersey(102,"Wonder-Person", 53, 129.99, "Red", "Medium", "Image.png");

    //     // Invoke
    //     Jersey result = assertDoesNotThrow(() -> jerseyFileDAO.createJersey(jersey),
    //                             "Unexpected exception thrown");

    //     // Analyze
    //     assertNotNull(result);
    //     Jersey actual = jerseyFileDAO.getJersey(jersey.getId());
    //     assertEquals(actual.getId(),jersey.getId());
    //     assertEquals(actual.getName(),jersey.getName());
    //     assertEquals(actual.getNumber(),jersey.getNumber());
    //     assertEquals(actual.getPrice(),jersey.getPrice());
    //     assertEquals(actual.getColor(),jersey.getColor());
    //     assertEquals(actual.getSize(),jersey.getSize());
    //     assertEquals(actual.getImage(),jersey.getImage());
    // }

    @Test
    public void testUpdateJersey() throws IOException {
        // Setup
        Jersey jersey = new Jersey(99, "GA", 30, 20.99, "red", "XL", "Image.png");

        // Invoke
        Jersey result = assertDoesNotThrow(() -> jerseyFileDAO.updateJersey(jersey),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Jersey actual = jerseyFileDAO.getJersey(jersey.getId());
        assertEquals(actual,jersey);
    }

    // @Test
    // public void testSaveException() throws IOException{
    //     doThrow(new IOException())
    //         .when(mockObjectMapper)
    //             .writeValue(any(File.class),any(Jersey[].class));

    //     Jersey jersey = new Jersey(102, "Wi-Fire", 10, 130.00, "Black", "Image.png");

    //     assertThrows(IOException.class,
    //                     () -> jerseyFileDAO.createJersey(jersey),
    //                     "IOException not thrown");
    // }

    // @Test
    // public void testGetJerseyNotFound() throws IOException {
    //     // Invoke
    //     Jersey jersey = jerseyFileDAO.getJersey(98);

    //     // Analyze
    //     assertEquals(jersey,null);
    // }

    // // @Test
    // // public void testDeleteJerseyNotFound() {
    // //     // Invoke
    // //     boolean result = assertDoesNotThrow(() -> jerseyFileDAO.deleteJersey(98),
    // //                                             "Unexpected exception thrown");

    // //     // Analyze
    // //     assertEquals(result,false);
    // //     assertEquals(jerseyFileDao.jerseys.size(),testJerseys.length);
    // // }

    // @Test
    // public void testUpdateJerseyNotFound() {
    //     // Setup
    //     Jersey jersey = new Jersey(98, "Bolt", 24, 229.99, "Yellow", "Image.png");

    //     // Invoke
    //     Jersey result = assertDoesNotThrow(() -> jerseyFileDAO.updateJersey(jersey),
    //                                             "Unexpected exception thrown");

    //     // Analyze
    //     assertNull(result);
    // }

    // @Test
    // public void testConstructorException() throws IOException {
    //     // Setup
    //     ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
    //     // We want to simulate with a Mock Object Mapper that an
    //     // exception was raised during JSON object deseerialization
    //     // into Java objects
    //     // When the Mock Object Mapper readValue method is called
    //     // from the HeroFileDAO load method, an IOException is
    //     // raised
    //     doThrow(new IOException())
    //         .when(mockObjectMapper)
    //             .readValue(new File("doesnt_matter.txt"),Jersey[].class);

    //     // Invoke & Analyze
    //     assertThrows(IOException.class,
    //                     () -> new JerseyFileDAO("doesnt_matter.txt",mockObjectMapper),
    //                     "IOException not thrown");
    // }
}
