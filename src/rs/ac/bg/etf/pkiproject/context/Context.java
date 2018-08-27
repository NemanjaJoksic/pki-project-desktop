/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rs.ac.bg.etf.pkiproject.model.Item;

/**
 *
 * @author Nemanja
 */
public class Context {
    
    public static final String USERNAME_ATTR = "username";
    public static final String RESTAURANT_ATTR = "restaurantName";
    public static final String FOOD_ATTR = "foodName";
    public static final String SHOPPING_CART_ATTR = "shoppingCart";
    
    private static Session session = null;
    private static Stage primaryStage = null;
    
    public static Session getSession() {
        if(session == null) {
            session = new Session();
            /*
            *   Add empty shopping cart in session
            */
            List<Item> shoppingCart = new ArrayList<>();
            session.addAttribute(SHOPPING_CART_ATTR, shoppingCart);
        }
        return session;
    }
    
    public static void destroySession() {
        session = null;
    }
    
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setResizable(false);
    }
    
}
