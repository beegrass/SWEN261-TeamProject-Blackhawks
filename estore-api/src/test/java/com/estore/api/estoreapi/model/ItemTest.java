package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")
public class ItemTest {
    @Test
    public void testGetQuantity(){
        int id = 99;
        String name = "Wi-Fire";
        int number = 50;
        double price = 129.99;
        String color = "Red";
        String size = "Medium";
        String image = "Image.png";

        Jersey jersey = new Jersey(id,name,number,price,color, size, image);

        Item item = new Item(jersey, 1); 
        int actual = item.getQuantity(); 
        assertEquals(1, actual);
    }

    @Test
    public void testGetJersey(){
        int id = 99;
        String name = "Wi-Fire";
        int number = 50;
        double price = 129.99;
        String color = "Red";
        String size = "Medium";
        String image = "Image.png";

        Jersey jersey = new Jersey(id,name,number,price,color, size, image);

        Item item = new Item(jersey, 1); 
        Jersey actual = item.getJersey(); 
        assertEquals(jersey, actual);
    }
}
