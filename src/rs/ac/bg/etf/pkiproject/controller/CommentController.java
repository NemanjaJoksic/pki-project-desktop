/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import rs.ac.bg.etf.pkiproject.context.Context;
import rs.ac.bg.etf.pkiproject.context.Session;
import rs.ac.bg.etf.pkiproject.model.Comment;
import rs.ac.bg.etf.pkiproject.model.Food;
import rs.ac.bg.etf.pkiproject.model.Restaurant;
import rs.ac.bg.etf.pkiproject.service.FoodService;
import rs.ac.bg.etf.pkiproject.service.RestaurantService;
import static rs.ac.bg.etf.pkiproject.context.Context.RESTAURANT_ATTR;
import static rs.ac.bg.etf.pkiproject.context.Context.FOOD_ATTR;
import static rs.ac.bg.etf.pkiproject.context.Context.USERNAME_ATTR;
import rs.ac.bg.etf.pkiproject.util.FxmlUtil;

/**
 *
 * @author Nemanja
 */
public class CommentController {

    private final RestaurantService restaurantService = new RestaurantService();
    private final FoodService foodService = new FoodService();

    private Restaurant restaurant = null;
    private Food food = null;
    private String name;
    private String picturePath;

    @FXML
    private TextArea comment;

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameLabel;

    @FXML
    private VBox comments;

    @FXML
    private void sendComment() {
        boolean successSending = false;
        String username = (String) Context.getSession().getAttribute(USERNAME_ATTR);
        if (restaurant != null) {
            successSending = restaurantService.sendComment(username, restaurant.getId(), comment.getText());
            addComments(name, restaurantService.getCommentsForRestaurant(restaurant.getId()));
        } else if (food != null) {
            successSending = foodService.sendComment(username, food.getId(), comment.getText());
            addComments(name, foodService.getCommentsForFood(food.getId()));
        }
        if (!successSending) {
            FxmlUtil.showErrorPopup("Comment is not send");
//            FxmlUtil.loadSceneFromFxmlFileOnNewStage("/rs/ac/bg/etf/pkiproject/fxml/popupError.fxml");
        }
        comment.setText("");
    }

    @FXML
    private void initialize() {
        List<Comment> allComments;
        Session session = Context.getSession();
        if ((restaurant = (Restaurant) session.getAttribute(RESTAURANT_ATTR)) != null) {
            name = restaurant.getName() + ", " + restaurant.getAddress() + ", " + restaurant.getLocation();
            picturePath = restaurant.getPicturePath();
            allComments = restaurantService.getCommentsForRestaurant(restaurant.getId());
        } else if ((food = (Food) session.getAttribute(FOOD_ATTR)) != null) {
            name = food.getName();
            picturePath = food.getPicturePath();
            allComments = foodService.getCommentsForFood(food.getId());
        } else {
            return;
        }
        addComments(name, allComments);
    }
    
    private void addComments(String name, List<Comment> allComments) {
        /*
        *   Clear pane
        */
        comments.getChildren().clear();
        
        /*
        *   Add restaurant name and image
         */
        nameLabel.setText(name);
        imageView.setImage(new Image(picturePath));

        /*
        *   Add all comments on pain
         */
        for (Comment comment : allComments) {
            Label usernameLabel = new Label();
            usernameLabel.setText(comment.getUsername());
            usernameLabel.setFont(new Font("System Bold", 14));
            usernameLabel.setPadding(new Insets(0, 0, 0, 7));
            comments.getChildren().add(usernameLabel);

            Label messageLabel = new Label();
            messageLabel.setText(comment.getMessage());
            messageLabel.setPadding(new Insets(0, 0, 0, 7));
            messageLabel.setWrapText(true);
            comments.getChildren().add(messageLabel);
        }
    }

}
