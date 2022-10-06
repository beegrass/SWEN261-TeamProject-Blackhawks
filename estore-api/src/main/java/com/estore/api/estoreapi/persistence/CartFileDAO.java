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

import com.estore.api.estoreapi.model.Cart;

/**
 * Implements the functionality for JSON file-based peristance for Cart
 * 
 * {@literal @}Component Spring annotation instantiates a single instance of this
 * class and injects the instance into other classes as needed
 * 
 * @author Vincent Schwartz
 */
public class CartFileDAO {
    
}
