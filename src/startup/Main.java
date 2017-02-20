/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import controllers.LoginScherm;
import domein.DomeinController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ~dreeki~
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) {
        LoginScherm root = new LoginScherm(new DomeinController());
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Administrator login");
        stage.setResizable(false);
        stage.show();
        
    }
}