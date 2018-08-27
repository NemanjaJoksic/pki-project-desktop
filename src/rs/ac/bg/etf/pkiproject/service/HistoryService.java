/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import rs.ac.bg.etf.pkiproject.dao.HistoryDao;
import rs.ac.bg.etf.pkiproject.model.Food;
import rs.ac.bg.etf.pkiproject.model.Item;
import rs.ac.bg.etf.pkiproject.model.Order;

/**
 *
 * @author Nemanja
 */
public class HistoryService {
    
    private final HistoryDao historyDao = new HistoryDao();
    
    public List<Order> getOrdersForUser(String username) {
//        List<Order> orders = new ArrayList<>();
//        
//        Order order = new Order();
//        order.setRestaurantName("Terminal");
//        order.setStatus("Antivna");
//        order.setTotalPrice(1500);
//        Food food = new Food(UUID.randomUUID().toString(), "Sarma", "Opis", 1000);
//        Item item = new Item(UUID.randomUUID().toString(), food, 2, 1200);
//        order.getItems().add(item);
//        food = new Food(UUID.randomUUID().toString(), "Pohovana piletina", "Opis", 1000);
//        item = new Item(UUID.randomUUID().toString(), food, 1, 1200);
//        order.getItems().add(item);
//        orders.add(order);
//        
//        order = new Order();
//        order.setRestaurantName("Slatkoteka");
//        order.setStatus("Antivna");
//        order.setTotalPrice(3500);
//        food = new Food(UUID.randomUUID().toString(), "Pohovana piletina", "Opis", 1000);
//        item = new Item(UUID.randomUUID().toString(), food, 1, 1200);
//        order.getItems().add(item);
//        food = new Food(UUID.randomUUID().toString(), "Pohovana sarma", "Opis", 1000);
//        item = new Item(UUID.randomUUID().toString(), food, 4, 1200);
//        order.getItems().add(item);
//        orders.add(order);
//        
//        order = new Order();
//        order.setRestaurantName("Stara zuta greda");
//        order.setStatus("Isporucena");
//        order.setTotalPrice(1500);
//        food = new Food(UUID.randomUUID().toString(), "Pohovana piletina", "Opis", 1000);
//        item = new Item(UUID.randomUUID().toString(), food, 1, 1200);
//        order.getItems().add(item);
//        food = new Food(UUID.randomUUID().toString(), "Pohovana sarma", "Opis", 1000);
//        item = new Item(UUID.randomUUID().toString(), food, 4, 1200);
//        order.getItems().add(item);
//        orders.add(order);
//        
//        order = new Order();
//        order.setRestaurantName("Hotel Slavija");
//        order.setStatus("Antivna");
//        order.setTotalPrice(1500);
//        food = new Food(UUID.randomUUID().toString(), "Pohovana piletina", "Opis", 1000);
//        item = new Item(UUID.randomUUID().toString(), food, 1, 1200);
//        order.getItems().add(item);
//        food = new Food(UUID.randomUUID().toString(), "Pohovana sarma", "Opis", 1000);
//        item = new Item(UUID.randomUUID().toString(), food, 4, 1200);
//        order.getItems().add(item);
//        orders.add(order);
//        
//        return orders;
        return historyDao.getOrdersForUser(username);
    }
    
}
