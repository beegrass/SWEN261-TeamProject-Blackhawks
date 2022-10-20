package com.estore.api.estoreapi.model;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Jersey entity
 * 
 * @author Vincent Schwartz
 */

public class Jersey {
    private static final Logger LOG = Logger.getLogger(Jersey.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Jersey [id=%d, name=%s]";

    @JsonProperty("id") private int id;
    @JsonProperty("name") private String name;
    @JsonProperty("number") private int number;
    @JsonProperty("price") private double price;
    @JsonProperty("color") private String color;
    @JsonProperty("size") private String size;
    @JsonProperty("image") private String image;

    /**
     * Create a jersey with the given name, number, price, color, and image path
     * @param id The id of the jersey
     * @param name The name that goes on the jersey
     * @param number The number that goes on the jersey
     * @param price The price of the jersey
     * @param color The color of the jersey
     * @param size The size of the jersey
     * @param image The picture of the jersey
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Jersey(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("number") int number, 
    @JsonProperty("price") double price, @JsonProperty("color") String color, @JsonProperty("size") String size,
    @JsonProperty("image") String image) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.price = price;
        this.color = color;
        this.size = size;
        this.image = image;
    }

    /**
     * Retrieves the id of the jersey
     * @return The id of the jersey
     */
    public int getId() {return id;}

    /**
     * Sets the name of the jersey - necessary for JSON object to Java object deserialization
     * @param name The name of the jersey
     */
    public void setName(String name) {this.name = name;}

    /**
     * Retrieves the name of the jersey
     * @return The name of the jersey
     */
    public String getName() {return name;}

    /**
     * Sets the number of the jersey - necessary for JSON object to Java object deserialization
     * @param name The number of the jersey
     */
    public void setNumber(int number) {this.number = number;}

    /**
     * Retrieves the name of the jersey
     * @return The name of the jersey
     */
    public int getNumber() {return number;}

    /**
     * Sets the price of the jersey - necessary for JSON object to Java object deserialization
     * @param price The price of the jersey
     */
    public void setPrice(double price) {this.price = price;}

    /**
     * Retrieves the price of the jersey
     * @return The price of the jersey
     */
    public double getPrice() {return price;}

    /**
     * Sets the color of the jersey - necessary for JSON object to Java object deserialization
     * @param color The color of the jersey
     */
    public void setColor(String color) {this.color = color;}

    /**
     * Retrieves the color of the jersey
     * @return The color of the jersey
     */
    public String getColor() {return color;}

    /**
     * Sets the size of the jersey - necessary for JSON object to Java object deserialization
     * @param size The size of the jersey
     */
    public void setSize(String size) {this.size = size;}

    /**
     * Retrieves the size of the jersey
     * @return The size of the jersey
     */
    public String getSize() {return size;}

    /**
     * Sets the image path of the jersey - necessary for JSON object to Java object deserialization
     * @param image The image path of the jersey
     */
    public void setImage(String image) {this.image = image;}

    /**
     * Retrieves the image path of the jersey
     * @return The iamge path of the jersey
     */
    public String getImage() {return image;}

    /**
     * {@inheritDoc}
     * To do: finish toString
     */
    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.price + " " + this.color + " " + this.size;
    }
    
    
  
    

}
