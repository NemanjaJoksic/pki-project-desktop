/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.service;

import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.etf.pkiproject.dao.CommentDao;
import rs.ac.bg.etf.pkiproject.dao.RestaurantDao;
import rs.ac.bg.etf.pkiproject.model.Comment;
import rs.ac.bg.etf.pkiproject.model.Restaurant;

/**
 *
 * @author Nemanja
 */
public class RestaurantService {
    
    private final RestaurantDao restaurantDao = new RestaurantDao();
    private final CommentDao commentDao = new CommentDao();
    
    public List<Restaurant> getAllRestaurants() {
        return restaurantDao.getAllRestaurants();
    }
    
    public List<Restaurant> searchRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("1", "Test Restoran 1", 8.54, "Opis"));
        restaurants.add(new Restaurant("3", "Test 3", 8.57, "Opis"));
        return restaurants;
    }
    
    public List<Restaurant> searchRestaurants(String namePatter, String location,
                    String kitchenType, String minRaitingStr, String maxRaitingStr) {
        
        double minRaiting = minRaitingStr.equals("") ? 0 : Double.parseDouble(minRaitingStr);
        double maxRaiting = maxRaitingStr.equals("") ? 10 : Double.parseDouble(maxRaitingStr);
        return restaurantDao.searchRestaurant(namePatter, location, kitchenType, minRaiting, maxRaiting);
    }
    
    public List<Comment> getCommentsForRestaurant(String restaurantId) {
        return commentDao.getCommentsForRestaurant(restaurantId);
    }
    
    public boolean sendComment(String username, String restaurantId, String message) {
        return commentDao.insertCommentForRestaurant(username, restaurantId, message);
    }
    
}
