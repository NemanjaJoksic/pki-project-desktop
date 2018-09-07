/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject;

import javafx.application.Application;
import javafx.stage.Stage;
import rs.ac.bg.etf.pkiproject.context.Context;
import rs.ac.bg.etf.pkiproject.util.FxmlUtil;

/**
 *
 * @author Nemanja
 */
public class Main extends Application {

    /*
    *   Load starting page
    */
    @Override
    public void start(Stage stage) throws Exception {
//        Context.getSession().addAttribute(Context.USERNAME_ATTR, "nemanja");
        Context.setPrimaryStage(stage);
        FxmlUtil.loadSceneFromFxmlFileOnPrimaryStage("/rs/ac/bg/etf/pkiproject/fxml/homeGuest.fxml");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
