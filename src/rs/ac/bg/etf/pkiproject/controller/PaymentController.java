/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rs.ac.bg.etf.pkiproject.context.Context;
import static rs.ac.bg.etf.pkiproject.context.Context.SHOPPING_CART_ATTR;
import rs.ac.bg.etf.pkiproject.context.Session;
import rs.ac.bg.etf.pkiproject.util.FxmlUtil;

/**
 *
 * @author Nemanja
 */
public class PaymentController {
    
    @FXML
    private AnchorPane paymantPane;
    
    @FXML
    private void pay() {
        Session session = Context.getSession();
        session.clearSession();
        ((List)session.getAttribute(SHOPPING_CART_ATTR)).clear();
        Stage stage = (Stage) paymantPane.getScene().getWindow();
        stage.close();
        FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/restaurantsUser.fxml");
    }
    
}
