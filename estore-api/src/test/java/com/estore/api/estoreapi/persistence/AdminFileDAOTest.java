package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import java.io.IOException;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.estore.api.estoreapi.model.Admin;
import com.estore.api.estoreapi.model.Jersey;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author Angela Ngo 
 * This is the unit testing for all the admin methods for controlling
 *  the inventory
 */
@Tag("Persistence-tier")
public class AdminFileDAOTest {
    AdminFileDAO adminFileDAO;
    ObjectMapper mockObjectMapper; 
    JerseyFileDAO jerseyFileDAO; 
    Jersey [] testJerseys; 

    @BeforeEach
    public void setupAdminFileDAO() throws IOException{
        mockObjectMapper = mock(ObjectMapper.class);
        Admin admin = new Admin(); 
        testJerseys = new Jersey[3];

        testJerseys[0] = new Jersey(99, "Jack Hughes", 5, 99.99, "Black", "Medium","Image.png");
        testJerseys[1] = new Jersey(100, "Poopy someone", 1, 50.99, "Red", "Small","Image.png");
        testJerseys[2] = new Jersey(101, "Patrick Kane", 88, 129.99, "Red", "Large","Image.png");
        // mocking the jerseyFileDAo
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Jersey[].class))
                .thenReturn(testJerseys);
                jerseyFileDAO = new JerseyFileDAO("doesnt_matter.txt",mockObjectMapper);

        when(mockObjectMapper
        .readValue(new File("doesnt_matter.txt"),Admin.class))
            .thenReturn(admin);
            adminFileDAO = new AdminFileDAO("doesnt_matter.txt",mockObjectMapper, jerseyFileDAO);

    }

    @Test
    public void testFindJerseysInInventoryByName() throws IOException{
        String name = "Jack Hughes";
        Jersey jersey = testJerseys[0]; 

        Jersey[] result = adminFileDAO.findJerseysInInventory(name, 0, 0.00, null, null);
        assertEquals(jersey, result[0]);
    }

    @Test
    public void testFindJerseyNameFail() throws IOException{
        String name = "meow"; 
        Jersey jersey = null; 

        Jersey[] result = adminFileDAO.findJerseysInInventory(name, 0, 0, null, null);
        assertEquals(jersey, result);
    }

    @Test
    public void testFindJerseysInInventoryByColor() throws IOException{
        String color = "Red"; 
        Jersey [] expected = new Jersey[2];
        expected[0] = testJerseys[1];
        expected[1] = testJerseys[2]; 

        Jersey[] result = adminFileDAO.findJerseysInInventory(null, 0, 0, color, null);
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }

    @Test
    public void testFindJerseysInInventoryByPrice() throws IOException{
        double price = 99.99;
        Jersey expected = testJerseys[0]; 
        Jersey [] result = adminFileDAO.findJerseysInInventory(null, 0, price, null, null);

        assertEquals(expected, result[0]);
    }

    @Test
    public void testFindJerseysInInventoryFailByPrice() throws IOException{
        double price = 2000.00;
        Jersey expected = null; 
        Jersey [] result = adminFileDAO.findJerseysInInventory(null, 0, price, null, null);

        assertEquals(expected, result);
    }

    @Test
    public void testFindJerseysInInventoryByNumber()throws IOException{
        int number = 5;
        Jersey expected = testJerseys[0];
        Jersey [] result = adminFileDAO.findJerseysInInventory(null, number, 0.0, null, null);
        assertEquals(expected, result[0]);
    }

    @Test
    public void testFindJerseysInInventoryByFailNumber()throws IOException{
        int number = 500;
        Jersey expected = null;
        Jersey [] result = adminFileDAO.findJerseysInInventory(null, number, 0.0, null, null);
        assertEquals(expected, result);
    }

    @Test
    public void testFindJerseysInInventoryBySize() throws IOException{
        String size = "Medium"; 
        Jersey expected = testJerseys[0];
        Jersey [] result = adminFileDAO.findJerseysInInventory(null, 0, 0.0, null, size);
        assertEquals(expected, result[0]);
    }

    @Test
    public void testFindJerseysInInventoryByFailSize() throws IOException{
        String size = "meow"; 
        Jersey expected = null;
        Jersey [] result = adminFileDAO.findJerseysInInventory(null, 0, 0.0, null, size);
        assertEquals(expected, result);
    }
    
    @Test 
    public void testGetJerseyFromInventory() throws IOException{
        int id = 99;
        Jersey expected = testJerseys[0]; 
        Jersey result = adminFileDAO.getJerseyFromInventory(id); 
        assertEquals(expected, result);

    }

    @Test
    public void testGetJerseyFromInventoryFail() throws IOException{
        int id = 200; 
        Jersey expected = null;
        Jersey result = adminFileDAO.getJerseyFromInventory(id);
        assertEquals(expected, result);
    }

    @Test 
    public void testCreateJersey()throws IOException{
        String name = "Guy in football";
        int number = 36;
        double price = 200.23;
        String color = "Red";
        String image = "img.png";
        int id = 102; 
        String size = "Medium";
        Jersey jersey = new Jersey(id, name, number, price, color, size, image);

        Jersey result = adminFileDAO.createJerseyInInventory(jersey); 
        assertEquals(jersey.getColor(), result.getColor());
        assertEquals(jersey.getName(), result.getName());
        assertEquals(jersey.getNumber(), result.getNumber());
        assertEquals(jersey.getPrice(), result.getPrice());
        assertEquals(jersey.getId(), result.getId());
    }

    @Test
    public void testUpdateJerseyInInventory() throws IOException{
        testJerseys[0].setColor("Red");
        Jersey result = adminFileDAO.updateJerseyInInventory(testJerseys[0]); 
        assertEquals("Red", result.getColor());
    }

    @Test
    public void testUpdateJerseyInInventoryFail() throws IOException{
        String name = "Guy in football";
        int number = 36;
        double price = 200.23;
        String color = "Red";
        String image = "img.png";
        int id = 102; 
        String size = "Medium";
        Jersey jersey = new Jersey(id, name, number, price, color, size, image);

        jersey.setColor("Black");
        Jersey result = adminFileDAO.updateJerseyInInventory(jersey);

        assertEquals(null, result);
    }

    @Test 
    public void testDeleteJerseyInventory() throws IOException{
        int id = 99; 
        boolean expected = true;
        boolean actual = adminFileDAO.deleteJerseyFromInventory(id); 
        Jersey jersey = adminFileDAO.getJerseyFromInventory(id);
        assertEquals(null, jersey);
        assertEquals(expected, actual);

    }

    @Test
    public void testDeleteJerseyInventoryFail() throws IOException{
        int id = 200; 
        boolean expected = false;
        boolean actual = adminFileDAO.deleteJerseyFromInventory(id); 
        Jersey jersey = adminFileDAO.getJerseyFromInventory(id);
        assertEquals(null, jersey);
        assertEquals(expected, actual);
    }
}
