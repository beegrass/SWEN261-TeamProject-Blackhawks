package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Jersey;

/**
 * Defines the interface for Jersey object persistence
 * 
 * @author Angela Ngo
 */

public interface JerseyDAO {
    /**
     * Retrieves all {@linkplain Jersey jersey's}
     * 
     * @return An array of {@link Jersey jerseys} objects, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Jersey[] getJerseys() throws IOException;

    /**
     * Finds all {@linkplain Jersey jerseys} whose name contains the given text
     * 
     * @param name The name to match against
     * @param number The number to match against
     * @param color The color to match against
     * @param size The size to match against
     * 
     * @return An array of {@link Jersey jerseys} whose names contains the given text, may be empty
     * 
     * @throws IOException if an issue with underlying storage
     */
    Jersey[] findJerseys(String name, int number, String color, String size) throws IOException;

    /**
     * Retrieves a {@linkplain Jersey jersey} with the given id
     * 
     * @param id The id of the {@link Jersey jersey} to get
     * 
     * @return a {@link Jersey jersey} object with the matching id
     * <br>
     * null if no {@link Jersey jersey} with a matching id is found
     * 
     * @throws IOException if an issue with underlying storage
     */
    Jersey getJersey(int id) throws IOException;
    
  
     /**
     * Creates and saves a {@linkplain Jersey jersey}
     * 
     * @param jersey {@linkplain Jersey jersey} object to be created and saved
     * <br>
     * The id of the jersey object is ignored and a new uniqe id is assigned
     *
     * @return new {@link Jersey jersey} if successful, false otherwise 
     * 
     * @throws IOException if an issue with underlying storage
     */
    Jersey createJersey(Jersey jersey) throws IOException;

     /**
     * Updates and saves a {@linkplain Jersey jersey}
     * 
     * @param {@link Jersey jersey} object to be updated and saved
     * 
     * @return updated {@link Jersey jersey} if successful, null if
     * {@link Jersey jersey} could not be found
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    Jersey updateJersey(Jersey jersey) throws IOException;

       /**
     * Deletes a {@linkplain Jersey jersey} with the given id
     * 
     * @param id The id of the {@link Jersey jersey}
     * 
     * @return true if the {@link Jersey jersey} was deleted
     * <br>
     * false if jersey with the given id does not exist
     * 
     * @throws IOException if underlying storage cannot be accessed
     */
    boolean deleteJersey(int id) throws IOException;

    // Jersey[] findJerseysName(String name) throws IOException;
    // Jersey[] findJerseysNumber(int number) throws IOException;
    Jersey[] findJerseysPrice(double price) throws IOException;
    // Jersey[] findJerseysColor(String color) throws IOException;
    // Jersey[] findJerseysSize(String size)throws IOException;

}
