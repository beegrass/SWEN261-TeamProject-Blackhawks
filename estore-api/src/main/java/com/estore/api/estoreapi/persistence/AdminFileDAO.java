package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.estore.api.estoreapi.model.Admin;
import com.estore.api.estoreapi.model.Jersey;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implements the functionality for JSON file-based peristance for Customer 
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Angela Ngo
 */
@Component
public class AdminFileDAO implements AdminDAO {
    private Admin admin; // there will only be one admin 
    private ObjectMapper objectMapper;  
    private static int nextId; 
    private String filename ;
    private JerseyFileDAO jerseyFileDAO; 

    public AdminFileDAO(@Value("${admin.file}") String filename, ObjectMapper objectMapper, JerseyFileDAO jerseyFileDAO) throws IOException{
        this.filename = filename;
        this.objectMapper = objectMapper;
        this.jerseyFileDAO = jerseyFileDAO; 
        load(); 
    }

  
    private boolean load() throws IOException{
        Admin adminObj = objectMapper.readValue(new File(filename), Admin.class);
        admin = adminObj; 
        return true; 

    }

    // private boolean save() throws IOException {
    //     Customer [] customerArray = getCustomerArray();

    //     // Serializes the Java Objects to JSON objects into the file
    //     // writeValue will thrown an IOException if there is an issue
    //     // with the file or reading from the file
    //     objectMapper.writeValue(new File(filename),customerArray);
    //     return true;
    // }

    @Override
    public Jersey[] findJerseysInInventory(String name, int number, double price, String color, String size) throws IOException {
       Jersey[] jerseys = jerseyFileDAO.findJerseys(name, number, price, color, size);
       if(jerseys.length == 0){
        return null; 
       }
       return jerseys;
    }

    @Override
    public Jersey getJerseyFromInventory(int id) throws IOException {
       Jersey jersey = jerseyFileDAO.getJersey(id);
       if(jersey == null){
        return null; 
       } 
       return jersey; 
    }

    @Override 
    public Jersey createJerseyInInventory(Jersey jersey) throws IOException {
        Jersey newJersey = jerseyFileDAO.createJersey(jersey);
        return newJersey; 
    }

    public Jersey updateJerseyInInventory(Jersey jersey) throws IOException{
        Jersey updatedJersey = jerseyFileDAO.updateJersey(jersey);
        return updatedJersey;
    }

    public boolean deleteJerseyFromInventory(int id) throws IOException{
        boolean ifRemove = jerseyFileDAO.deleteJersey(id); 
        return ifRemove; 
    }


}
