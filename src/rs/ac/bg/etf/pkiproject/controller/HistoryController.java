/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import rs.ac.bg.etf.pkiproject.context.Context;
import static rs.ac.bg.etf.pkiproject.context.Context.USERNAME_ATTR;
import rs.ac.bg.etf.pkiproject.model.Item;
import rs.ac.bg.etf.pkiproject.model.Order;
import rs.ac.bg.etf.pkiproject.service.HistoryService;
import static rs.ac.bg.etf.pkiproject.context.Context.RESTAURANT_PICTURE_PATH_TEMPLATE;

/**
 *
 * @author Nemanja
 */
public class HistoryController {
    
    private final HistoryService historyService = new HistoryService();
    
    @FXML
    private VBox ordersPane;
    
    @FXML
    private void initialize() {
        String username = (String) Context.getSession().getAttribute(USERNAME_ATTR);
        List<Order> orders = historyService.getOrdersForUser(username);
        for(Order order : orders) {
            ordersPane.getChildren().add(createAnchorPaneForOrder(order));
        }
    }
    
    private AnchorPane createAnchorPaneForOrder(Order order) {
        AnchorPane anchorPane = new AnchorPane();
        
        /*
        *   Add restaurant picture
        */
        ImageView restaurantPicture = new ImageView();
        restaurantPicture.setFitHeight(67);
        restaurantPicture.setFitWidth(73);
        restaurantPicture.setLayoutX(6);
        restaurantPicture.setLayoutY(7);
        restaurantPicture.setPickOnBounds(true);
        restaurantPicture.setPreserveRatio(true);
        restaurantPicture.setImage(new Image(RESTAURANT_PICTURE_PATH_TEMPLATE.replaceAll("#NAME", order.getRestaurantName().toLowerCase().replaceAll(" ", "-"))));
        anchorPane.getChildren().add(restaurantPicture);
        
        /*
        *   Add label with restaurant name
        */
        Label restaurantName = new Label();
        restaurantName.setLayoutX(90);
        restaurantName.setLayoutY(10);
        restaurantName.setText(order.getRestaurantName());
        restaurantName.setFont(new Font("System Bold", 24));
        anchorPane.getChildren().add(restaurantName);
        
        /*
        *   Add label with status
        */
        Label statusLabel = new Label();
        statusLabel.setLayoutX(92);
        statusLabel.setLayoutY(47);
        statusLabel.setText(order.getStatus());
        statusLabel.setFont(new Font(14));
        anchorPane.getChildren().add(statusLabel);
        
        /*
        *   Add label with total price
        */
        Label totalPriceLabel = new Label();
        totalPriceLabel.setLayoutX(882);
        totalPriceLabel.setLayoutY(27);
        totalPriceLabel.setText(String.valueOf(order.getTotalPrice()));
        totalPriceLabel.setFont(new Font("System Bold", 18));
        anchorPane.getChildren().add(totalPriceLabel);
        
        /*
        *   Add button for raiting if order is finished
        */
        if(order.getStatus().equals("Isporucena")) {
            Button button = new Button();
            button.setText("Oceni");
            button.setPrefHeight(25);
            button.setPrefWidth(78);
            button.setLayoutX(982);
            button.setLayoutY(28);
            anchorPane.getChildren().add(button);
        }
        
        List<Item> items = order.getItems();
        int diff = 74;
        for(int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            
            /*
            *   Add food picture
            */
            ImageView foodPicture = new ImageView();
            foodPicture.setFitHeight(57);
            foodPicture.setFitWidth(57);
            foodPicture.setLayoutX(85);
            foodPicture.setLayoutY(74 + i*diff);
            foodPicture.setPickOnBounds(true);
            foodPicture.setPreserveRatio(true);
            foodPicture.setImage(new Image(item.getFood().getPicturePath()));
            anchorPane.getChildren().add(foodPicture);
            
            /*
            *   Add label with food name
            */
            Label foodName = new Label();
            foodName.setLayoutX(162);
            foodName.setLayoutY(74 + i*diff);
            foodName.setText(item.getFood().getName());
            foodName.setFont(new Font("System Bold", 14));
            anchorPane.getChildren().add(foodName);
            
            /*
            *   Add label with food desc
            */
            Label foodDesc = new Label();
            foodDesc.setAlignment(Pos.TOP_LEFT);
            foodDesc.setTextAlignment(TextAlignment.JUSTIFY);
            foodDesc.setPrefHeight(37);
            foodDesc.setPrefWidth(680);
            foodDesc.setLayoutX(162);
            foodDesc.setLayoutY(94 + i*diff);
            foodDesc.setWrapText(true);
            foodDesc.setText(item.getFood().getDesc());
            anchorPane.getChildren().add(foodDesc);
            
            /*
            *   Add label 'Cena'
            */
            Label priceLabel = new Label();
            priceLabel.setLayoutX(1007);
            priceLabel.setLayoutY(76 + i*diff);
            priceLabel.setText("Cena");
            anchorPane.getChildren().add(priceLabel);
            
            /*
            *   Add label with price
            */
            Label price = new Label();
            price.setLayoutX(1001);
            price.setLayoutY(103 + i*diff);
            price.setText(String.valueOf(item.getPrice()));
            price.setFont(new Font(14));
            anchorPane.getChildren().add(price);
            
            /*
            *   Add label 'Kolicina'
            */
            Label quantityLabel = new Label();
            quantityLabel.setLayoutX(894.0);
            quantityLabel.setLayoutY(76 + i*diff);
            quantityLabel.setText("Kolicina");
            anchorPane.getChildren().add(quantityLabel);
            
            /*
            *   Add field with quantity
            */
            TextField quantity = new TextField();
            quantity.setAlignment(Pos.CENTER);
            quantity.setEditable(false);
            quantity.setLayoutX(893);
            quantity.setLayoutY(100 + i*diff);
            quantity.setPrefHeight(25);
            quantity.setPrefWidth(44);
            quantity.setText(String.valueOf(item.getQuantity()));
            anchorPane.getChildren().add(quantity);
        }
        
        return anchorPane;
    }
    
}




