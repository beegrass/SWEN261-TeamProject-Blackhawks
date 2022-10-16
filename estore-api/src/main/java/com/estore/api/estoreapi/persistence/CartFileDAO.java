// package com.estore.api.estoreapi.persistence;

// import java.io.File;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.TreeMap;
// import java.util.logging.Logger;

// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import com.estore.api.estoreapi.model.Cart;
// import com.estore.api.estoreapi.model.Jersey;

// /**
//  * Implements the functionality for JSON file-based peristance for Cart
//  * 
//  * {@literal @}Component Spring annotation instantiates a single instance of this
//  * class and injects the instance into other classes as needed
//  * 
//  * @author Angela Ngo
//  */
// public class CartFileDAO implements CartDAO {

//     private static final Logger LOG  = Logger.getLogger(JerseyFileDAO.class.getName());
//     Map<Integer,Cart> carts;   // Provides a local cache of the jersey objects
//                                 // so that we don't need to read from the file
//                                 // each time
//     private ObjectMapper objectMapper;  // Provides conversion between cart
//                                         // objects and JSON text format written
//                                         // to the file
//     private static int nextId;  // The next Id to assign to a new cart
//     private String filename;    // Filename to read from and write to

//     public CartFileDAO(@Value("${carts.file}") String filename,ObjectMapper objectMapper) throws IOException {
//         this.filename = filename;
//         this.objectMapper = objectMapper;
//         load();
//     }

//     // /**
//     //  * Saves the {@linkplain Cart cart} from the map into the file as an array of JSON objects
//     //  * 
//     //  * @return true if the {@link Cart carts} were written successfully
//     //  * 
//     //  * @throws IOException when file cannot be accessed or written to
//     //  */
//     // private boolean save() throws IOException {
//     //     Jersey[] jerseyArray = getJerseysArray();

//     //     // Serializes the Java Objects to JSON objects into the file
//     //     // writeValue will thrown an IOException if there is an issue
//     //     // with the file or reading from the file
//     //     objectMapper.writeValue(new File(filename),jerseyArray);
//     //     return true;
//     // }

//     // /**
//     //  * Loads {@linkplain Cart cart} from the JSON file into the map
//     //  * <br>
//     //  * Also sets next id to one more than the greatest id found in the file
//     //  * 
//     //  * @return true if the file was read successfully
//     //  * 
//     //  * @throws IOException when file cannot be accessed or read from
//     //  */
//     // private boolean load() throws IOException {
//     //     carts = new TreeMap<>();
//     //     nextId = 0;

//     //     // Deserializes the JSON objects from the file into an array of jerseyes
//     //     // readValue will throw an IOException if there's an issue with the file
//     //     // or reading from the file
//     //     Jersey[] jerseyArray = objectMapper.readValue(new File(filename),Jersey[].class);

//     //     // Add each jersey to the tree map and keep track of the greatest id
//     //     for (Jersey jersey : jerseyArray) {
//     //         jerseys.put(jersey.getId(),jersey);
//     //         if (jersey.getId() > nextId)
//     //             nextId = jersey.getId();
//     //     }
//     //     // Make the next id one greater than the maximum from the file
//     //     ++nextId;
//     //     return true;
//     // }

//     @Override
//     public HashMap<Jersey, Integer> getJerseysInCart() throws IOException {
//         return cart.getEntireCart(); 
//     }

//     @Override
//     public boolean addJerseyToCart(Jersey jersey) {
//        boolean is_added = cart.addJerseyToCart(jersey); 
//        return is_added; 
//     }

//     @Override
//     public boolean decrementJerseyTypeAmount(Jersey jersey) {
//         boolean is_decremented = cart.decrementJerseyTypeFromCart(jersey);
//         return is_decremented;  
//     }

//     @Override
//     public boolean deleteEntireJerseyFromCart(Jersey jersey) {
//         boolean is_deleted = cart.deleteJerseyType(jersey); 
//         return is_deleted; 
//     }

//     @Override
//     public boolean deleteEntireCart() throws IOException {
//        boolean cart_empty = cart.deleteEntireCart(); 
//        return cart_empty; 
//     }

// }