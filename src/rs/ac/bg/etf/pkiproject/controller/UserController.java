/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.controller;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import rs.ac.bg.etf.pkiproject.context.Context;
import static rs.ac.bg.etf.pkiproject.context.Context.USERNAME_ATTR;
import rs.ac.bg.etf.pkiproject.model.User;
import rs.ac.bg.etf.pkiproject.service.UserService;
import rs.ac.bg.etf.pkiproject.util.FxmlUtil;

/**
 *
 * @author Nemanja
 */
public class UserController {

    private final UserService userService = new UserService();

    @FXML
    private URL location;

    /*
    *   Personal informations fiels
     */
    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField address;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField email;

    /*
    *   Change password fields
     */
    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField newPasswordConfirm;

    /*
    *   Registration fields
     */
    @FXML
    private TextField registerUsername;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerName;

    @FXML
    private TextField registerSurname;

    @FXML
    private TextField registerAddress;

    @FXML
    private TextField registerPhoneNumber;

    @FXML
    private TextField registerEmail;

    @FXML
    private void register() {
        /*
        *   TODO: Registration logic
         */
        User user = new User();
        user.setUsername(registerUsername.getText());
        user.setPassword(registerPassword.getText());
        user.setName(registerName.getText());
        user.setSurname(registerSurname.getText());
        user.setPhoneNumber(registerPhoneNumber.getText());
        user.setAddress(registerAddress.getText());
        user.setPhoneNumber(registerPhoneNumber.getText());
        if (userService.registerUser(user)) {
//            FxmlUtil.loadSceneFromFxmlFileOnNewStage("/rs/ac/bg/etf/pkiproject/fxml/popupSuccess.fxml");
            FxmlUtil.showSuccessPopup("Successful registration");
        } else {
            FxmlUtil.showSuccessPopup("User already exists");
//            FxmlUtil.loadSceneFromFxmlFileOnNewStage("/rs/ac/bg/etf/pkiproject/fxml/popupError.fxml");
        }
    }

    @FXML
    private void changePersonalInfo() {
        String loginUsername = (String) Context.getSession().getAttribute(USERNAME_ATTR);
        User user = new User();
        user.setUsername(loginUsername);
        user.setName(name.getText());
        user.setSurname(surname.getText());
        user.setPhoneNumber(phoneNumber.getText());
        user.setAddress(address.getText());
        user.setEmail(email.getText());
        if (userService.updateUser(user)) {
            FxmlUtil.showSuccessPopup("Informations are updated");
//            FxmlUtil.loadSceneFromFxmlFileOnNewStage("/rs/ac/bg/etf/pkiproject/fxml/popupSuccess.fxml");
        } else {
            FxmlUtil.showSuccessPopup("Error occurred, informations can't be changed");
//            FxmlUtil.loadSceneFromFxmlFileOnNewStage("/rs/ac/bg/etf/pkiproject/fxml/popupError.fxml");
        }
    }

    @FXML
    private void changePassword() {
        String oldPasswordValue = oldPassword.getText();
        String newPasswordValue = newPassword.getText();
        String newPasswordConfirmValue = newPasswordConfirm.getText();

        String loginUsername = (String) Context.getSession().getAttribute(USERNAME_ATTR);
        User user = userService.getUserByUsername(loginUsername);

        if (user.getPassword().equals(oldPasswordValue) && !oldPasswordValue.equals(newPasswordValue)
                && newPasswordValue.equals(newPasswordConfirmValue) && userService.updateUserPassword(loginUsername, newPasswordValue)) {
            FxmlUtil.showSuccessPopup("Password is changed");
//            FxmlUtil.loadSceneFromFxmlFileOnNewStage("/rs/ac/bg/etf/pkiproject/fxml/popupSuccess.fxml");
        } else {
            FxmlUtil.showSuccessPopup("Password is not changed");
//            FxmlUtil.loadSceneFromFxmlFileOnNewStage("/rs/ac/bg/etf/pkiproject/fxml/popupError.fxml");
        }

    }

    @FXML
    private void initialize() {
        if (location.toString().endsWith("personalInfo.fxml")) {
            String loginUsername = (String) Context.getSession().getAttribute(USERNAME_ATTR);
            User user = userService.getUserByUsername(loginUsername);
            name.setText(user.getName());
            surname.setText(user.getSurname());
            address.setText(user.getAddress());
            phoneNumber.setText(user.getPhoneNumber());
            email.setText(user.getEmail());
        }
    }

}
