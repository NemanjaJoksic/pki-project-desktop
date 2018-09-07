/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.controller;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
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
import rs.ac.bg.etf.pkiproject.context.Session;
import rs.ac.bg.etf.pkiproject.service.RestaurantService;
import rs.ac.bg.etf.pkiproject.model.Restaurant;
import static rs.ac.bg.etf.pkiproject.context.Context.RESTAURANT_ATTR;
import rs.ac.bg.etf.pkiproject.util.FxmlUtil;

/**
 *
 * @author Nemanja
 */
public class RestaurantController {

    private final RestaurantService restaurantService = new RestaurantService();

    @FXML
    private TextField namePattern;
    
    @FXML
    private ComboBox restaurantLocation;
    
    @FXML
    private ComboBox kitchenType;
    
    @FXML
    private TextField minRaiting;
    
    @FXML
    private TextField maxRaiting;
    
    @FXML
    private VBox restaurantsPane;

    @FXML
    private void searchRestaurants() {
        restaurantsPane.getChildren().clear();
        List<Restaurant> restaurants = restaurantService.searchRestaurants(namePattern.getText(),
                (String) restaurantLocation.getValue(), (String) kitchenType.getValue(), 
                minRaiting.getText(), maxRaiting.getText());
        for (Restaurant restaurant : restaurants) {
            restaurantsPane.getChildren().add(createAnchorPaneForRestaurant(restaurant));
        }
    }
    
    @FXML
    private void initialize() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        for (Restaurant restaurant : restaurants) {
            restaurantsPane.getChildren().add(createAnchorPaneForRestaurant(restaurant));
        }
    }

    private AnchorPane createAnchorPaneForRestaurant(Restaurant restaurant) {

        /*
        *   Create AnchorPane with Height set on 100px
         */
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(100);

        /*
        *   Add restaurant image on pain
         */
        ImageView imageView = new ImageView();
        imageView.setFitHeight(98);
        imageView.setFitWidth(87);
        imageView.setLayoutX(7);
        imageView.setLayoutY(1);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(restaurant.getPicturePath()));
//        imageView.setImage(new Image("file:resources/meals.jpg"));
        anchorPane.getChildren().add(imageView);

        /*
        *   Add label with restaurant name and address
         */
        Hyperlink nameLink = new Hyperlink();
        nameLink.setLayoutX(98);
        nameLink.setLayoutY(-5);
        nameLink.setText(restaurant.getName() + ", " + restaurant.getAddress() + ", " + restaurant.getLocation());
        nameLink.setFont(new Font("System Bold", 16));
        nameLink.setOnAction((ActionEvent e) -> {
            Session session = Context.getSession();
            session.addAttribute(RESTAURANT_ATTR, restaurant);
            if(session.getAttribute(USERNAME_ATTR) != null) {
                FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/foodUser.fxml");
            } else {
                FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/foodGuest.fxml");
            }
        });
        anchorPane.getChildren().add(nameLink);
        
        /*
        *   Add label with raiting
         */
        Label raitingLabel = new Label();
        raitingLabel.setLayoutX(874);
        raitingLabel.setLayoutY(-1);
        raitingLabel.setText(String.valueOf(restaurant.getRaiting()));
        raitingLabel.setFont(new Font(16));
        anchorPane.getChildren().add(raitingLabel);

        /*
        *   Add label with description
         */
        Label descLabel = new Label();
        descLabel.setAlignment(Pos.TOP_LEFT);
        descLabel.setLayoutX(101);
        descLabel.setLayoutY(26);
        descLabel.setPrefHeight(49);
        descLabel.setPrefWidth(806);
        descLabel.setWrapText(true);
        descLabel.setTextAlignment(TextAlignment.JUSTIFY);
        descLabel.setText(restaurant.getDesc());
        anchorPane.getChildren().add(descLabel);

        /*
        *   Add link for comments if user is logged in
        *   Add button for adding food to cart if user is logged in
         */
        if (Context.getSession().getAttribute(USERNAME_ATTR) != null) {

            Hyperlink commentsLink = new Hyperlink();
            commentsLink.setText("Komentari(" + restaurantService.getCommentsForRestaurant(restaurant.getId()).size() + ")");
            commentsLink.setFont(new Font(14));
            commentsLink.setLayoutX(818);
            commentsLink.setLayoutY(70);
            commentsLink.setOnAction((ActionEvent e) -> {
                Session session = Context.getSession();
                session.clearSession();
                session.addAttribute(RESTAURANT_ATTR, restaurant);
                FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/commentsRestaurant.fxml");
            });
            anchorPane.getChildren().add(commentsLink);
        }

        return anchorPane;
    }

}
