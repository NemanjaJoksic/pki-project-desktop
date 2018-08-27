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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static rs.ac.bg.etf.pkiproject.dao.util.Fields.*;
import static rs.ac.bg.etf.pkiproject.dao.util.Queries.GET_ORDERS_FOR_USER;
import rs.ac.bg.etf.pkiproject.db.DB;
import rs.ac.bg.etf.pkiproject.model.Food;
import rs.ac.bg.etf.pkiproject.model.Item;
import rs.ac.bg.etf.pkiproject.model.Order;

/**
 *
 * @author Nemanja
 */
public class HistoryDao {
    
    public List<Order> getOrdersForUser(String username) {
        try {
            Connection connection = DB.getInstance().getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_ORDERS_FOR_USER);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return extractOrdersFromRS(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    private List<Order> extractOrdersFromRS(ResultSet rs) throws SQLException {
        Map<String, Order> ordersMap = new HashMap<>();
        while(rs.next()) {
            String orderId = rs.getString(ID_SQL);
            if(!ordersMap.containsKey(orderId)) {
                Order order = new Order();
                order.setId(orderId);
                order.setStatus(rs.getString(STATUS_SQL));
                order.setRestaurantName(rs.getString(RESTAURANT_NAME_SQL));
                order.setTotalPrice(rs.getDouble(TOTAL_PRICE_SQL));
                ordersMap.put(orderId, order);
            }
            Item item = new Item();
            item.setPrice(rs.getDouble(PRICE_SQL));
            item.setQuantity(rs.getInt(QUANTITY_SQL));
            item.setFood(new Food(rs.getString(FOOD_NAME_SQL), rs.getString(DESC_SQL)));
            Order order = ordersMap.get(orderId);
            order.getItems().add(item);
        }
        return new ArrayList<>(ordersMap.values());
    }
    
}
