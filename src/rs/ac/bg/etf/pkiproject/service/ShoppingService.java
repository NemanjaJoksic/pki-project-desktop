/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import rs.ac.bg.etf.pkiproject.context.Context;
import static rs.ac.bg.etf.pkiproject.context.Context.SHOPPING_CART_ATTR;
import static rs.ac.bg.etf.pkiproject.context.Context.USERNAME_ATTR;
import rs.ac.bg.etf.pkiproject.context.Session;
import rs.ac.bg.etf.pkiproject.dao.HistoryDao;
import rs.ac.bg.etf.pkiproject.model.Food;
import rs.ac.bg.etf.pkiproject.model.Item;

/**
 *
 * @author Nemanja
 */
public class ShoppingService {
    
    private final HistoryDao historyDao = new HistoryDao();
    
    public void addFoodToCart(Food food) {
        List<Item> shoppingCart = (List<Item>) Context.getSession().getAttribute(SHOPPING_CART_ATTR);
        for(Item order : shoppingCart) {
            if(order.getFood().getName().equals(food.getName())) {
                /*
                *   Food has already been added to cart, increment quantity
                */
                order.setQuantity(order.getQuantity() + 1);
                return;
            }
        }
        /*
        *   Food is not in cart, add it to cart
        */
        Item newOrder = new Item();
        newOrder.setId(UUID.randomUUID().toString());
        newOrder.setFood(food);
        newOrder.setQuantity(1);
        newOrder.setPrice(food.getPrice());
        shoppingCart.add(newOrder);
    }
    
    public void changeQuantityForFood(Food food, int quantity) {
        List<Item> shoppingCart = (List<Item>) Context.getSession().getAttribute(SHOPPING_CART_ATTR);
        for(Item order : shoppingCart) {
            if(order.getFood().getName().equals(food.getName())) {
                order.setQuantity(quantity);
                return;
            }
        }
    }
    
    public void removeFoodFromCart(Food food) {
        List<Item> shoppingCart = (List<Item>) Context.getSession().getAttribute(SHOPPING_CART_ATTR);
        for(int i = 0; i < shoppingCart.size(); i++) {
            Item order = shoppingCart.get(i);
            if(order.getFood().getName().equals(food.getName())) {
                shoppingCart.remove(i);
                return;
            }
        }
    }
    
    public void finishShopping() {
        try {
            Session session = Context.getSession();
            String username = (String) session.getAttribute(USERNAME_ATTR);
            List<Item> shoppingCart = (List<Item>) session.getAttribute(SHOPPING_CART_ATTR);
            String orderId = historyDao.insertOrder(username, calculateTotalPrice(shoppingCart));
            for(Item item : shoppingCart) {
                historyDao.insertItem(item.getFood().getId(), orderId, item.getQuantity());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double calculateTotalPrice(List<Item> shoppingCart) {
        double totalPrice = 0.0;
        for(Item item : shoppingCart) {
            totalPrice += item.getPrice()*item.getQuantity();
        }
        return totalPrice;
    }
    
}
