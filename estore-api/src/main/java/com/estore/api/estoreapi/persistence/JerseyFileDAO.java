package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Jersey;

/**
 * Implements the functionality for JSON file-based peristance for Jerseys
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Ethan Abbate, Hayden Cabral, Angela Ngo, Vincent Schwartz
 */

@Component
public class JerseyFileDAO implements JerseyDAO {
    private static final Logger LOG  = Logger.getLogger(JerseyFileDAO.class.getName());
    Map<Integer,Jersey> jerseys;   // Provides a local cache of the jersey objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between jersey
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new jersey
    private String filename;    // Filename to read from and write to

    public JerseyFileDAO(@Value("${jerseys.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();
    }

    /**
     * Generates the next id for a new {@linkplain Jersey jersey}
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of {@linkplain Jersey jerseys} from the tree map
     * 
     * @return  The array of {@link Jersey jerseys}, may be empty
     */
    private Jersey[] getJerseysArray() {
        return getJerseysArray(null, 0, null, null);
    }

    /**
     * @Author Angela Ngo 
     * Generates an array of {@linkplain Jersey jerseys} from the tree map for any
     * {@linkplain Jersey jerseys} that contains the text specified by information 
     * <br>
     * If containsText is null, the array contains all of the {@linkplain Jersey jersey}
     * in the tree map
     * 
     * Searches based on one query information (can be a string version of a number or double as well)
     * @return  The array of {@link Jersey jerseys}, may be empty
     */
    private Jersey[] getJerseysArray(String name, int number, String color, String size) { // if containsText == null, no filter
        ArrayList<Jersey> jerseyArrayList = new ArrayList<>();

        for (Jersey jersey : jerseys.values()) {

            if ((name == null || jersey.getName().contains(name)) &&
                (number == 0 || jersey.getNumber() == number) &&  
                (color == null || jersey.getColor().contains(color)) &&
                (size == null || jersey.getSize().contains(size)))
                    jerseyArrayList.add(jersey);
        }

        Jersey[] jerseyArray = new Jersey[jerseyArrayList.size()];
        jerseyArrayList.toArray(jerseyArray);
        return jerseyArray;
    }

    /**
     * Saves the {@linkplain Jersey jersey} from the map into the file as an array of JSON objects
     * 
     * @return true if the {@link Jersey jerseys} were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Jersey[] jerseyArray = getJerseysArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),jerseyArray);
        return true;
    }

    /**
     * Loads {@linkplain Jersey jersey} from the JSON file into the map
     * <br>
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        jerseys = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of jerseyes
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Jersey[] jerseyArray = objectMapper.readValue(new File(filename),Jersey[].class);

        // Add each jersey to the tree map and keep track of the greatest id
        for (Jersey jersey : jerseyArray) {
            jerseys.put(jersey.getId(),jersey);
            if (jersey.getId() > nextId)
                nextId = jersey.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    
    /**
    ** {@inheritDoc}
     */
    @Override
    public Jersey[] getJerseys() {
        synchronized(jerseys) {
            return getJerseysArray();
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Jersey[] findJerseys(String name, int number, String color, String size) throws IOException {
        synchronized(jerseys) {
            return getJerseysArray(name, number, color, size);
        }
    }

    /**
    ** {@inheritDoc}
     */
    @Override
    public Jersey getJersey(int id) throws IOException {
        synchronized(jerseys) {
            if (jerseys.containsKey(id))
                return jerseys.get(id);
            else
                return null;
        }
    }

    /**
     * @param jersey this is the given jersey that we want to add to the map of jerseys
     * @return newJersey this returns the jersey that was created 
     */
    @Override
    public Jersey createJersey(Jersey jersey) throws IOException {
        synchronized(jerseys) {
            // We create a new hero object because the id field is immutable
            // and we need to assign the next unique id
            Jersey newJersey = new Jersey(nextId(), jersey.getName(), jersey.getNumber(), jersey.getPrice(), 
                jersey.getColor(), jersey.getSize(), jersey.getImage());
            jerseys.put(newJersey.getId(), newJersey);
            save(); // may throw an IOException
            return newJersey;
        }
    }
    
    /**
     * @param jersey takes in a jersey to put into jerseys map 
     * @return jersey this returns the jersey that was updated
     */
    @Override
    public Jersey updateJersey(Jersey jersey) throws IOException {
        synchronized(jerseys) {
            if (jerseys.containsKey(jersey.getId()) == false)
                return null;  // hero does not exist

            jerseys.put(jersey.getId(),jersey);
            save(); // may throw an IOException
            return jersey;
        }
    }


    @Override
    public boolean deleteJersey(int id) throws IOException {
        synchronized(jerseys) {
            if (jerseys.containsKey(id)) {
                jerseys.remove(id);
                return save();
            }
            else
                return false;
        }
    }

    /**
     * This returns an array of jerseys with the given name 
     * @param name
     * @return jerseyArray
     */
    // private Jersey[] getJerseysName(String name) {
    //     ArrayList<Jersey> jerseyArrayList = new ArrayList<>();

    //     for (Jersey jersey : jerseys.values()) {

    //         if (jersey.getName().contains(name)) {
                    
    //             jerseyArrayList.add(jersey);
    //         }
    //     }

    //     Jersey[] jerseyArray = new Jersey[jerseyArrayList.size()];
    //     jerseyArrayList.toArray(jerseyArray);
    //     return jerseyArray;
    // }
    

    //  /**
    //   * this takes from the getJerseyName
    // ** {@inheritDoc}
    //  */
    // @Override
    // public Jersey[] findJerseysName(String name) throws IOException {
    //     synchronized(jerseys) {
    //         return getJerseysName(name);
    //     }
    // }

    /**
     * This returns an array of jerseys with the given number
     * @param number
     * @return jerseyArray
     */
    // private Jersey[] getJerseysNumber(int number) {
    //     ArrayList<Jersey> jerseyArrayList = new ArrayList<>();

    //     for (Jersey jersey : jerseys.values()) {

    //         if (jersey.getNumber() == number) {
                    
    //             jerseyArrayList.add(jersey);
    //         }
    //     }

    //     Jersey[] jerseyArray = new Jersey[jerseyArrayList.size()];
    //     jerseyArrayList.toArray(jerseyArray);
    //     return jerseyArray;
    // }
    

    // @Override
    // public Jersey[] findJerseysNumber(int number) throws IOException {
    //     synchronized(jerseys) {
    //         return getJerseysNumber(number);
    //     }
    // }

     /**
     * This returns an array of jerseys with the given number
     * @param price
     * @return jerseyArray
     */
    private Jersey[] getJerseysPrice(double price) {
        ArrayList<Jersey> jerseyArrayList = new ArrayList<>();

        for (Jersey jersey : jerseys.values()) {

            if (jersey.getPrice() == price) {
                    
                jerseyArrayList.add(jersey);
            }
        }

        Jersey[] jerseyArray = new Jersey[jerseyArrayList.size()];
        jerseyArrayList.toArray(jerseyArray);
        return jerseyArray;
    }
    


    @Override
    public Jersey[] findJerseysPrice(double price) throws IOException {
        synchronized(jerseys) {
            return getJerseysPrice(price);
        }
    }


    
     /**
     * This returns an array of jerseys with the given color string
     * @param Color
     * @return jerseyArray
     */
    // private Jersey[] getJerseysColor(String color) {
    //     ArrayList<Jersey> jerseyArrayList = new ArrayList<>();

    //     for (Jersey jersey : jerseys.values()) {

    //         if (jersey.getColor().contains(color)) {
                    
    //             jerseyArrayList.add(jersey);
    //         }
    //     }

    //     Jersey[] jerseyArray = new Jersey[jerseyArrayList.size()];
    //     jerseyArrayList.toArray(jerseyArray);
    //     return jerseyArray;
    // }


    // @Override
    // public Jersey[] findJerseysColor(String color) throws IOException {
    //     synchronized(jerseys) {
    //         return getJerseysColor(color);
    //     }
    // }

    /**
     * This returns an array of jerseys with the given size
     * @param size
     * @return jerseyArray
     */
    // private Jersey[] getJerseysSize(String size) {
    //     ArrayList<Jersey> jerseyArrayList = new ArrayList<>();

    //         for (Jersey jersey : jerseys.values()) {
    
    //             if (size == null || jersey.getSize().contains(size)) {
                        
    //                 jerseyArrayList.add(jersey);
    //             }
    //         }
    
    //         Jersey[] jerseyArray = new Jersey[jerseyArrayList.size()];
    //         jerseyArrayList.toArray(jerseyArray);
    //         return jerseyArray;
    // }

    // @Override
    // public Jersey[] findJerseysSize(String size) throws IOException {
    //     synchronized(jerseys) {
    //         return getJerseysSize(size);
    //     }
    // }

}
