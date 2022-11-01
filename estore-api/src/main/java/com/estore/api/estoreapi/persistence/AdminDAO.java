package com.estore.api.estoreapi.persistence;

import java.io.IOException;

import com.estore.api.estoreapi.model.Jersey;

public interface AdminDAO {
    Jersey[] findJerseysInInventory(String name, int number, double price, String color, String size) throws IOException;
    Jersey getJerseyFromInventory(int id) throws IOException;
    Jersey createJerseyInInventory(Jersey jersey) throws IOException ;
    Jersey updateJerseyInInventory(Jersey jersey) throws IOException;
}
