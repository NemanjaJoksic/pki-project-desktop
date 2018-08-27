/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static rs.ac.bg.etf.pkiproject.dao.util.Fields.*;
import static rs.ac.bg.etf.pkiproject.dao.util.Queries.*;
import rs.ac.bg.etf.pkiproject.db.DB;
import rs.ac.bg.etf.pkiproject.model.Restaurant;

/**
 *
 * @author Nemanja
 */
public class RestaurantDao {
    
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            Connection connection = DB.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_RESTAURANTS);
            while(rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getString(ID_SQL));
                restaurant.setName(rs.getString(NAME_SQL));
                restaurant.setDesc(rs.getString(DESC_SQL));
                restaurant.setRaiting(rs.getDouble(RAITING_SQL));
                restaurant.setAddress(rs.getString(ADDRESS_SQL));
                restaurant.setLocation(rs.getString(LOCATION_SQL));
                restaurants.add(restaurant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return restaurants;
    }
    
    public List<Restaurant> searchRestaurant(String namePattern, String location,
                    String kitchenType, double minRaiting, double maxRaiting) {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(SEARCH_RESTAURANTS);
            ps.setString(1, "%" + namePattern + "%");
            ps.setString(2, "%" + location + "%");
            ps.setString(3, "%" + kitchenType + "%");
            ps.setDouble(4, minRaiting);
            ps.setDouble(5, maxRaiting);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getString(ID_SQL));
                restaurant.setName(rs.getString(NAME_SQL));
                restaurant.setDesc(rs.getString(DESC_SQL));
                restaurant.setRaiting(rs.getDouble(RAITING_SQL));
                restaurant.setAddress(rs.getString(ADDRESS_SQL));
                restaurant.setLocation(rs.getString(LOCATION_SQL));
                restaurants.add(restaurant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return restaurants;
    }
    
}
