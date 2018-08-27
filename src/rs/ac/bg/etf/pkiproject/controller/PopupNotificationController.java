/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Nemanja
 */
public class PopupNotificationController {
    
    @FXML
    private Parent popupNotification;
    
    @FXML
    private void okPressed() {
        Stage stage = (Stage) popupNotification.getScene().getWindow();
        stage.close();
    }
    
}
