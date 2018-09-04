/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rs.ac.bg.etf.pkiproject.context.Context;
import static rs.ac.bg.etf.pkiproject.context.Context.USERNAME_ATTR;
import rs.ac.bg.etf.pkiproject.context.Session;
import rs.ac.bg.etf.pkiproject.model.User;
import rs.ac.bg.etf.pkiproject.service.UserService;
import rs.ac.bg.etf.pkiproject.util.FxmlUtil;

/**
 *
 * @author Nemanja
 */
public class HeaderController {
    
    private final UserService userService = new UserService();
    
    @FXML
    private TextField loginUsername;
    
    @FXML
    private PasswordField loginPassword;
    
    @FXML
    private Label userNameSurname;
    
    @FXML
    private void goToHomeScene() {
        Session session = Context.getSession();
        session.clearSession();
        if (session.getAttribute(USERNAME_ATTR) != null) {
            FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/homeUser.fxml");
        } else {
            FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/homeGuest.fxml");
        }
    }

    @FXML
    private void goToRestaurantsScene() {
        Session session = Context.getSession();
        session.clearSession();
        if (session.getAttribute(USERNAME_ATTR) != null) {
            FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/restaurantsUser.fxml");
        } else {
            FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/restaurantsGuest.fxml");
        }
    }
    
    @FXML
    private void goToPersonalInfo() {
        Session session = Context.getSession();
        session.clearSession();
        FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/personalInfo.fxml");
    }
    
    @FXML
    private void goToShoppingCart() {
        Session session = Context.getSession();
        session.clearSession();
        FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/shoppingCart.fxml");
    }
    
    @FXML
    private void goToHistory() {
        Session session = Context.getSession();
        session.clearSession();
        FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/history_1.fxml");
    }
    
    @FXML
    private void login() {
        String username = loginUsername.getText();
        String password = loginPassword.getText();
        Session session = Context.getSession();
        session.clearSession();
        if(userService.loginUser(username, password)) {
            session.addAttribute(USERNAME_ATTR, username);
            FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/homeUser.fxml");
        } else {
            FxmlUtil.showErrorPopup("Wrong username or password");
//            FxmlUtil.loadSceneFromFxmlFileOnNewStage("/rs/ac/bg/etf/pkiproject/fxml/popupError.fxml");
        }
    }
    
    @FXML
    private void logout() {
        Session session = Context.getSession();
        session.clearSession();
        Context.destroySession();
        FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/homeGuest.fxml");
    }
    
    @FXML
    private void initialize() {
        if(userNameSurname != null) {
            String username = (String) Context.getSession().getAttribute(USERNAME_ATTR);
            User user = userService.getUserByUsername(username);
            userNameSurname.setText(user.getName() + " " + user.getSurname());
        }
    }
    
}
