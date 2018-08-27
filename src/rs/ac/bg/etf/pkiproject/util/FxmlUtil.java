/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.util;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import rs.ac.bg.etf.pkiproject.context.Context;

/**
 *
 * @author Nemanja
 */
public class FxmlUtil {
    
    public static void showSuccessPopup(String message) {
        try {
            BorderPane root = FXMLLoader.load(Context.class.getResource("/rs/ac/bg/etf/pkiproject/fxml/popupSuccess.fxml"));
            FlowPane flowPane = (FlowPane) root.getChildren().get(0);
            Label messageLabel = (Label) flowPane.getChildren().get(1);
            messageLabel.setText(message);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void showErrorPopup(String message) {
        try {
            BorderPane root = FXMLLoader.load(Context.class.getResource("/rs/ac/bg/etf/pkiproject/fxml/popupError.fxml"));
            FlowPane flowPane = (FlowPane) root.getChildren().get(0);
            Label messageLabel = (Label) flowPane.getChildren().get(1);
            messageLabel.setText(message);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadSceneFromFxmlFileOnPrimaryStage(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(Context.class.getResource(fxmlFilePath));
            Scene scene = new Scene(root);
            Stage primaryStage = Context.getPrimaryStage();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void loadSceneFromFxmlFileOnNewStage(String fxmlFilePath) {
        try {
            Parent root = FXMLLoader.load(Context.class.getResource(fxmlFilePath));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
