/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import rs.ac.bg.etf.pkiproject.context.Context;
import static rs.ac.bg.etf.pkiproject.context.Context.USERNAME_ATTR;
import static rs.ac.bg.etf.pkiproject.context.Context.RESTAURANT_ATTR;
import rs.ac.bg.etf.pkiproject.context.Session;
import rs.ac.bg.etf.pkiproject.service.FoodService;
import rs.ac.bg.etf.pkiproject.model.Food;
import rs.ac.bg.etf.pkiproject.model.Restaurant;
import rs.ac.bg.etf.pkiproject.service.ShoppingService;
import static rs.ac.bg.etf.pkiproject.context.Context.FOOD_ATTR;
import rs.ac.bg.etf.pkiproject.util.FxmlUtil;

/**
 *
 * @author Nemanja
 */
public class FoodController {

    private final FoodService foodService = new FoodService();
    private final ShoppingService shoppingService = new ShoppingService();

    private final ObservableSet<String> selectedIngredients = FXCollections.observableSet();
    
    @FXML
    private TextField namePattern;
    
    @FXML
    private ComboBox kitchenType;
    
    @FXML
    private VBox foodPane;

    @FXML
    private FlowPane ingredientsCheckBoxGroup;

    @FXML
    private void searchFood() {
        Restaurant restaurant = (Restaurant) Context.getSession().getAttribute(RESTAURANT_ATTR);
        foodPane.getChildren().clear();
        List<Food> foodList = foodService.searchFood(restaurant.getId(), namePattern.getText(),
                    (String) kitchenType.getValue(), new ArrayList(selectedIngredients));
        for (Food food : foodList) {
            foodPane.getChildren().add(createAnchorPaneForFood(food));
        }
    }

    @FXML
    private void initialize() {
        Restaurant restaurant = (Restaurant) Context.getSession().getAttribute(RESTAURANT_ATTR);
        List<Food> foodList = foodService.getAllFoodForRestaurant(restaurant.getId());
        for (Food food : foodList) {
            foodPane.getChildren().add(createAnchorPaneForFood(food));
        }
        addIngredients();
    }

    private AnchorPane createAnchorPaneForFood(Food food) {

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
//        imageView.setImage(new Image("file:resources/" + food.getName() + ".jpg"));
        imageView.setImage(new Image("file:resources/meals.jpg"));
        anchorPane.getChildren().add(imageView);

        /*
        *   Add label with food name
         */
        Label nameLabel = new Label();
        nameLabel.setLayoutX(98);
        nameLabel.setLayoutY(0);
        nameLabel.setText(food.getName());
        nameLabel.setFont(new Font("System Bold", 16));
        anchorPane.getChildren().add(nameLabel);

        /*
        *   Add label with price
         */
        Label priceLabel = new Label();
        priceLabel.setLayoutX(98);
        priceLabel.setLayoutY(20);
        priceLabel.setText(String.valueOf(food.getPrice()) + " RSD");
        priceLabel.setFont(new Font("System Bold", 12));
        anchorPane.getChildren().add(priceLabel);

        /*
        *   Add label with description
         */
        Label descLabel = new Label();
        descLabel.setAlignment(Pos.TOP_LEFT);
        descLabel.setLayoutX(98);
        descLabel.setLayoutY(31);
        descLabel.setPrefHeight(53);
        descLabel.setPrefWidth(806);
        descLabel.setWrapText(true);
        descLabel.setTextAlignment(TextAlignment.JUSTIFY);
        descLabel.setText(food.getDesc());
        anchorPane.getChildren().add(descLabel);

        /*
        *   Add link for comments if user is logged in
        *   Add button for adding food to cart if user is logged in
         */
        if (Context.getSession().getAttribute(USERNAME_ATTR) != null) {
            Button addFoodToCartButton = new Button();
            addFoodToCartButton.setLayoutX(812);
            addFoodToCartButton.setLayoutY(6);
            addFoodToCartButton.setText("Dodaj u korpu");
            addFoodToCartButton.setOnAction((ActionEvent e) -> {
                shoppingService.addFoodToCart(food);
                FxmlUtil.showSuccessPopup("Food is added to cart");
//                FxmlUtil.loadSceneFromFxmlFileOnNewStage("/rs/ac/bg/etf/pkiproject/fxml/popupSuccess.fxml");
            });
            anchorPane.getChildren().add(addFoodToCartButton);

            Hyperlink commentsLink = new Hyperlink();
            commentsLink.setText("Komentari(" + foodService.getCommentsForFood(food.getId()).size() + ")");
            commentsLink.setFont(new Font(14));
            commentsLink.setLayoutX(816);
            commentsLink.setLayoutY(70);
            commentsLink.setOnAction((ActionEvent e) -> {
                Session session = Context.getSession();
                session.clearSession();
                session.addAttribute(FOOD_ATTR, food);
                FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/commentsFood.fxml");
            });
            anchorPane.getChildren().add(commentsLink);
        }

        return anchorPane;
    }

    private void addIngredients() {
        List<String> ingredients = foodService.getAllIngredients();
        for (String ingredient : ingredients) {
            CheckBox checkBox = new CheckBox();
            checkBox.setPrefWidth(85);
            checkBox.setText(ingredient);
            checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    selectedIngredients.add(checkBox.getText());
                } else {
                    selectedIngredients.remove(checkBox.getText());
                }
            });
            ingredientsCheckBoxGroup.getChildren().add(checkBox);
        }
    }
    
}
