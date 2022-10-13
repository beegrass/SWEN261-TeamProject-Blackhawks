package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Hero class
 * 
 * @author Vincent Schwartz
 */
@Tag("Model-tier")
public class JerseyTest {
    @Test
<<<<<<< HEAD
    public void testController() {
=======
    public void testCtor() {
>>>>>>> 565fc352e57c01dd83fc88ddae5acc425e900f28
        // Setup
        int expected_id = 99;
        String expected_name = "Wi-Fire";
        int expected_number = 50;
        double expected_price = 129.99;
        String expected_color = "Red";
        String expected_size = "Medium";
        String expected_image = "Image.png";

        // Invoke
        Jersey jersey = new Jersey(expected_id, expected_name, expected_number, expected_price, expected_color, 
        expected_size, expected_image);

        // Analyze
        assertEquals(expected_id,jersey.getId());
        assertEquals(expected_name,jersey.getName());
        assertEquals(expected_number,jersey.getNumber());
        assertEquals(expected_price,jersey.getPrice());
        assertEquals(expected_color,jersey.getColor());
        assertEquals(expected_size,jersey.getSize());
        assertEquals(expected_image,jersey.getImage());
    }

    @Test
    public void testName() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        int number = 50;
        double price = 129.99;
        String color = "Red";
        String size = "Medium";
        String image = "Image.png";

        Jersey jersey = new Jersey(id,name,number,price,color, size, image);

        String expected_name = "Galactic Agent";

        // Invoke
        jersey.setName(expected_name);

        // Analyze
        assertEquals(expected_name,jersey.getName());
    }

    @Test
    public void testNumber() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        int number = 50;
        double price = 129.99;
        String color = "Red";
        String size = "Medium";
        String image = "Image.png";

        Jersey jersey = new Jersey(id,name,number,price,color, size, image);

        int expected_number = 20;

        // Invoke
        jersey.setNumber(expected_number);

        // Analyze
        assertEquals(expected_number,jersey.getNumber());
    }

    @Test
    public void testPrice() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        int number = 50;
        double price = 129.99;
        String color = "Red";
        String size = "Medium";
        String image = "Image.png";

        Jersey jersey = new Jersey(id,name,number,price,color, size, image);

        double expected_price = 100.99;

        // Invoke
        jersey.setPrice(expected_price);

        // Analyze
        assertEquals(expected_price,jersey.getPrice());
    }

    @Test
    public void testColor() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        int number = 50;
        double price = 129.99;
        String color = "Red";
        String size = "Medium";
        String image = "Image.png";

        Jersey jersey = new Jersey(id,name,number,price,color, size, image);

        String expected_color = "Yellow";

        // Invoke
        jersey.setColor(expected_color);

        // Analyze
        assertEquals(expected_color,jersey.getColor());
    }

    @Test
    public void testSize() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        int number = 50;
        double price = 129.99;
        String color = "Red";
        String size = "Medium";
        String image = "Image.png";

        Jersey jersey = new Jersey(id,name,number,price,color, size, image);

        String expected_size = "Large";

        // Invoke
        jersey.setSize(expected_size);

        // Analyze
        assertEquals(expected_size,jersey.getSize());
    }

    @Test
    public void testImage() {
        // Setup
        int id = 99;
        String name = "Wi-Fire";
        int number = 50;
        double price = 129.99;
        String color = "Red";
        String size = "Medium";
        String image = "Image.png";

        Jersey jersey = new Jersey(id,name,number,price,color, size, image);

        String expected_image = "Image.jpg";

        // Invoke
        jersey.setImage(expected_image);

        // Analyze
        assertEquals(expected_image,jersey.getImage());
    }

    // @Test
    // public void testToString() {
    //     // Setup
    //     int id = 99;
    //     String name = "Wi-Fire";
    //     String expected_string = String.format(Hero.STRING_FORMAT,id,name);
    //     Hero hero = new Hero(id,name);

    //     // Invoke
    //     String actual_string = hero.toString();

    //     // Analyze
    //     assertEquals(expected_string,actual_string);
    // }
}