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
    @JsonProperty("image") private String image;

    /**
     * Create a jersey with the given name, number, price, color, and image path
     * @param id The id of the jersey
     * @param name The name that goes on the jersey
     * @param number The number that goes on the jersey
     * @param price The price of the jersey
     * @param color The color of the jersey
     * @param image The picture of the jersey
     * 
     * {@literal @}JsonProperty is used in serialization and deserialization
     * of the JSON object to the Java object in mapping the fields.  If a field
     * is not provided in the JSON object, the Java field gets the default Java
     * value, i.e. 0 for int
     */
    public Jersey(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("number") int number, @JsonProperty("price") double price,
    @JsonProperty("color") String color, @JsonProperty("image") String image) {
        this.name = name;
        this.number = number;
        this.price = price;
        this.color = color;
        this.image = image;
    }

    /**
     * Retrieves the id of the jersey
     * @return The id of the jersey
     */
    public int getId() {return id;}

    /**
     * Retrieves the name of the jersey
     * @return The name of the jersey
     */
    public String getName() {return name;}

    /**
     * Retrieves the price of the jersey
     * @return The price of the jersey
     */
    public double getPrice() {return price;}

    /**
     * Retrieves the id of the jersey
     * @return The id of the jersey
     */
    public String getColor() {return color;}

    /**
     * Retrieves the id of the jersey
     * @return The id of the jersey
     */
    public String getImage() {return image;}
    

}
