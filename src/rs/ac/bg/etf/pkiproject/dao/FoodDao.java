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
import java.util.ArrayList;
import java.util.List;
import static rs.ac.bg.etf.pkiproject.dao.util.Fields.*;
import static rs.ac.bg.etf.pkiproject.dao.util.Queries.*;
import rs.ac.bg.etf.pkiproject.db.DB;
import rs.ac.bg.etf.pkiproject.model.Food;

/**
 *
 * @author Nemanja
 */
public class FoodDao {
    
    public List<Food> getAllFoodForRestaurant(String restaurantId) {
        List<Food> allFood = new ArrayList<>();
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ALL_FOOD_FOR_RESTAURANT);
            ps.setString(1, restaurantId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Food food = new Food();
                food.setId(rs.getString(ID_SQL));
                food.setName(rs.getString(NAME_SQL));
                food.setDesc(rs.getString(DESC_SQL));
                food.setPrice(rs.getDouble(PRICE_SQL));
                allFood.add(food);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allFood;
    }
    
    public List<Food> searchFood(String restaurantId, String namePattern,
                String kitchenType, String ingredients, int numberOfIngredients) {
        List<Food> allFood = new ArrayList<>();
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(SEARCH_FOOD.replace("#ING", ingredients));
            ps.setString(1, restaurantId);
            ps.setString(2, "%" + namePattern + "%");
            ps.setString(3, "%" + kitchenType + "%");
            ps.setInt(4, numberOfIngredients);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Food food = new Food();
                food.setId(rs.getString(ID_SQL));
                food.setName(rs.getString(NAME_SQL));
                food.setDesc(rs.getString(DESC_SQL));
                food.setPrice(rs.getDouble(PRICE_SQL));
                allFood.add(food);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allFood;
    }
    
}
