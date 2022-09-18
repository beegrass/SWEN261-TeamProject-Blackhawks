package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;

import com.estore.api.estoreapi.model.Jersey;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    }
    @Override
    public Jersey[] getJersey() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Jersey[] findJerseys(String containsText) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Jersey getJersey(int id) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Jersey createJersey(Jersey jersey) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Jersey updateJersey(Jersey jersey) throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

}
