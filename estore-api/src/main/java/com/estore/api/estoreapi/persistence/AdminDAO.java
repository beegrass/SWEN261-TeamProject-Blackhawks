// package com.estore.api.estoreapi.persistence;

// import java.io.IOException;

// import com.estore.api.estoreapi.model.Jersey;

// public interface AdminDAO {
//      /**
//      * Finds the jerseys in inventory according to criteria 
//      * @param name - name of jersey 
//      * @param number - number of jersey 
//      * @param price - the price of jersey 
//      * @param color - color of the jersey
//      * @param size - size of the jersey 
//      * @return jerseys - this returns if there are jerseys found 
//      */
//     Jersey[] findJerseysInInventory(String name, int number, double price, String color, String size) throws IOException;
//     /**
//      * @param id - id of the specific jersey 
//      * @return jersey if found null if not s
//      */
//     Jersey getJerseyFromInventory(int id) throws IOException;
    
//     /**
//      * creates a new jersey in the inventory 
//      * @param jersey - jersey that is wanted to be created 
//      * @return newJersey - the jersey that is created based on the given one
//      */
//     Jersey createJerseyInInventory(Jersey jersey) throws IOException ;
//     /**
//      * Updates the jersey 
//      * @param jersey - jersey to be updated 
//      * @return updatedJersey - the updated jersey after changes 
//      */
//     Jersey updateJerseyInInventory(Jersey jersey) throws IOException;
//      /**
//      * 
//      * @param id
//      * @return ifRemove - true if removed false if not
//      * @throws IOException - thrown if theres an issue with save()
//      */
//     boolean deleteJerseyFromInventory(int id) throws IOException; 
// }
