// package com.estore.api.estoreapi.controller;

// import java.io.IOException;
// import java.util.logging.Level;
// import java.util.logging.Logger;


// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.estore.api.estoreapi.model.Admin;
// import com.estore.api.estoreapi.model.Jersey;
// import com.estore.api.estoreapi.persistence.AdminDAO;
// import com.estore.api.estoreapi.persistence.JerseyDAO;

// /**
//  * Controller for the admin class 
//  * @Author Angela Ngo 
//  */
// @RestController
// @RequestMapping("admin")
// public class AdminController {
//     private static final Logger LOG = Logger.getLogger(CartController.class.getName());
//     // both of these will be taking in a JerseyDAO in the main method  
//     private AdminDAO adminDAO; 
    
//     public AdminController(AdminDAO adminDAO){
//         this.adminDAO = adminDAO;
    
//     }

//     @GetMapping("/searchInventoryColor/")
//     public ResponseEntity<Jersey[]> findJerseysInInventoryColor(String color){
//         LOG.info("GET /admin/searchInventoryColor/?color="+color);
//         try{
//             Jersey[] response = adminDAO.findJerseysInInventory(null, 0, 0.0,color, null);
//             if(response.length == 0){
//                 return new ResponseEntity<Jersey[]>(response, HttpStatus.NOT_FOUND); 
//             }else{
//                 return new ResponseEntity<Jersey[]>(response, HttpStatus.OK); 
//             }
            
//         }catch (IOException e){
//             LOG.log(Level.SEVERE,e.getLocalizedMessage());
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
    
//     }
    
//     @GetMapping("/searchInventoryName/")
//     public ResponseEntity<Jersey[]> findJerseysInInventoryName(String name){
//         LOG.info("GET /admin/searchInventoryName/?name="+name); 
//         try{
//             Jersey [] response = adminDAO.findJerseysInInventory(name, 0, 0, null, null);
//             if(response.length == 0){
//                 return new ResponseEntity<Jersey[]>(response, HttpStatus.NOT_FOUND); 
//             }else{
//                 return new ResponseEntity<Jersey[]>(response, HttpStatus.OK); 
//             }
//         }catch (IOException e){
//             LOG.log(Level.SEVERE,e.getLocalizedMessage());
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }

//     @GetMapping("/searchInventoryPrice/")
//     public ResponseEntity<Jersey[]> findJerseyInInventoryPrice(double price){
//         LOG.info("GET /admin/searchInventoryPrice/?price="+price);
//         try{
//             Jersey [] response = adminDAO.findJerseysInInventory(null, 0, price, null, null);
//             if(response.length == 0){
//                 return new ResponseEntity<Jersey[]>(response, HttpStatus.NOT_FOUND); 
//             }else{
//                 return new ResponseEntity<Jersey[]>(response, HttpStatus.OK); 
//             }
//         }catch (IOException e){
//             LOG.log(Level.SEVERE,e.getLocalizedMessage());
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }

//     @GetMapping("/searchInventorySize/")
//     public ResponseEntity<Jersey[]> findJerseyInInventorySize(String size){
//         LOG.info("GET /admin/searchInventorySize/?size="+size);
//         try{
//             Jersey [] response = adminDAO.findJerseysInInventory(null, 0, 0.0, null, size);
//             if(response.length == 0){
//                 return new ResponseEntity<Jersey[]>(response, HttpStatus.NOT_FOUND); 
//             }else{
//                 return new ResponseEntity<Jersey[]>(response, HttpStatus.OK); 
//             }
//         }catch (IOException e){
//             LOG.log(Level.SEVERE,e.getLocalizedMessage());
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }

//     @GetMapping("/searchInventoryNumber/")
//     public ResponseEntity<Jersey[]> findJerseyInInventoryNumber(int number){
//         LOG.info("GET /admin/searchInventoryNumber/?number="+number);
//         try{
//             Jersey [] response = adminDAO.findJerseysInInventory(null, number, 0.0, null, null);
//             if(response.length == 0){
//                 return new ResponseEntity<Jersey[]>(response, HttpStatus.NOT_FOUND); 
//             }else{
//                 return new ResponseEntity<Jersey[]>(response, HttpStatus.OK); 
//             }
//         }catch (IOException e){
//             LOG.log(Level.SEVERE,e.getLocalizedMessage());
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }

//     @PostMapping("/createJersey/")
//     public ResponseEntity<Jerse
   
// }
