/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.controller;

import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import rs.ac.bg.etf.pkiproject.context.Context;
import static rs.ac.bg.etf.pkiproject.context.Context.SHOPPING_CART_ATTR;
import rs.ac.bg.etf.pkiproject.context.Session;
import rs.ac.bg.etf.pkiproject.model.Item;
import rs.ac.bg.etf.pkiproject.service.ShoppingService;
import rs.ac.bg.etf.pkiproject.util.FxmlUtil;

/**
 *
 * @author Nemanja
 */
public class ShoppingCartController {

//    static {
//        Session session = Context.getSession();
//        List<Order> orders = new ArrayList<>();
//        orders.add(new Order("1", new Food(1, "Glavno jelo", "Opissdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"), 1, 1200));
//        orders.add(new Order("1", new Food(1, "Glavno jelo", "Opis"), 2, 1200));
//        orders.add(new Order("1", new Food(1, "Glavno jelo", "Opis"), 3, 1200));
//        orders.add(new Order("1", new Food(1, "Glavno jelo", "Opis"), 5, 1200));
//        orders.add(new Order("1", new Food(1, "Glavno jelo", "Opis"), 1, 1200));
//        orders.add(new Order("1", new Food(1, "Glavno jelo", "Opis"), 3, 1200));
//        orders.add(new Order("1", new Food(1, "Glavno jelo", "Opis"), 1, 1200));
//        orders.add(new Order("1", new Food(1, "Glavno jelo", "Opis"), 5, 1200));
//        session.addAttribute("orders", orders);
//    }
    
    private final ShoppingService shoppingService = new ShoppingService();
    
    @FXML
    private VBox orderedFoodPane;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private void showPaymentFormPopup() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/rs/ac/bg/etf/pkiproject/fxml/popupPaymant.fxml"));
        Label label = (Label) root.getChildren().get(1);
        label.setText("Iznos:   " + totalPriceLabel.getText());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void cancelShopping() {
        Session session = Context.getSession();
        session.clearSession();
        ((List)session.getAttribute(SHOPPING_CART_ATTR)).clear();
        FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/restaurantsUser.fxml");
    }

    @FXML
    private void initialize() {
        Session session = Context.getSession();
        List<Item> shoppingCart = (List<Item>) session.getAttribute(SHOPPING_CART_ATTR);

        /*
        *   If there is no order return from method
         */
        if (shoppingCart == null) {
            return;
        }

        /*
        *   ELSE show all orders
         */
        for (Item order : shoppingCart) {
            orderedFoodPane.getChildren().add(createAnchorPaneForOrder(order));
        }
        updateTotalPrice();
    }

    private AnchorPane createAnchorPaneForOrder(Item order) {

        /*
        *   Create AnchorPane with Height set on 100px
         */
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(97);
        anchorPane.setPrefWidth(1000);

        /*
        *   Add restaurant image on pain
         */
        ImageView imageView = new ImageView();
        imageView.setFitHeight(90);
        imageView.setFitWidth(100.0);
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
        nameLabel.setLayoutX(120.0);
        nameLabel.setLayoutY(6);
        nameLabel.setText(order.getFood().getName());
        nameLabel.setFont(new Font("System Bold", 18));
        anchorPane.getChildren().add(nameLabel);

        /*
        *   Add label with description
         */
        Label descLabel = new Label();
        descLabel.setAlignment(Pos.TOP_LEFT);
        descLabel.setLayoutX(120);
        descLabel.setLayoutY(33);
        descLabel.setPrefHeight(60);
        descLabel.setPrefWidth(640);
        descLabel.setWrapText(true);
        descLabel.setText(order.getFood().getDesc());
        anchorPane.getChildren().add(descLabel);
        
        /*
        *   Add label 'Cena'
         */
        Label priceLabel = new Label();
        priceLabel.setLayoutX(930);
        priceLabel.setLayoutY(22);
        priceLabel.setText("Cena");
        anchorPane.getChildren().add(priceLabel);

        /*
        *   Add label with price
         */
        Label price = new Label();
        price.setAlignment(Pos.CENTER);
        price.setLayoutX(900);
        price.setLayoutY(49);
        price.setPrefHeight(25);
        price.setPrefWidth(87);
        price.setFont(new Font("System Bold", 18));
        price.setText(String.valueOf(order.getPrice() * order.getQuantity()));
        anchorPane.getChildren().add(price);

        /*
        *   Add label 'Kolicina'
         */
        Label quantityLabel = new Label();
        quantityLabel.setLayoutX(806);
        quantityLabel.setLayoutY(22);
        quantityLabel.setText("Kolicina");
        anchorPane.getChildren().add(quantityLabel);

        /*
        *   Add field for entering quantity
         */
        TextField quantityTextField = new TextField();
        quantityTextField.setAlignment(Pos.CENTER);
        quantityTextField.setLayoutX(800);
        quantityTextField.setLayoutY(49);
        quantityTextField.setPrefHeight(25);
        quantityTextField.setPrefWidth(55);
        quantityTextField.setText(String.valueOf(order.getQuantity()));
        quantityTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("")) return;
            shoppingService.changeQuantityForFood(order.getFood(), Integer.parseInt(newValue));
            price.setText(String.valueOf(order.getPrice() * Integer.parseInt(newValue)));
            updateTotalPrice();
        });
        anchorPane.getChildren().add(quantityTextField);

        /*
        *   Add button for removing order
         */
        Button removeOrderButton = new Button();
        removeOrderButton.setLayoutX(1020);
        removeOrderButton.setLayoutY(37);
        removeOrderButton.setText("X");
        removeOrderButton.setMnemonicParsing(false);
        removeOrderButton.setPrefHeight(25);
        removeOrderButton.setPrefWidth(32);
        removeOrderButton.setOnAction((ActionEvent e) -> {
            shoppingService.removeFoodFromCart(order.getFood());
            orderedFoodPane.getChildren().remove(anchorPane);
            updateTotalPrice();
        });
        anchorPane.getChildren().add(removeOrderButton);

        return anchorPane;
    }
    
    private void updateTotalPrice() {
        double totalPrice = 0;
        List<Item> shoppingCart = (List<Item>) Context.getSession().getAttribute(SHOPPING_CART_ATTR);
        for (Item order : shoppingCart) {
            totalPrice += order.getPrice() * order.getQuantity();
        }
        totalPriceLabel.setText(String.valueOf(totalPrice));
    }

}
