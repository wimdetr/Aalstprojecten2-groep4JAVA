/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import controllers.LoginScherm;
import controllers.SchermBeheer;
import domein.DomeinController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

/**
 *
 * @author ~dreeki~
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) {
        SchermBeheer root = new SchermBeheer(new DomeinController(), stage);
        LoginScherm ls = new LoginScherm(root);
        root.plaatsScherm(ls, "Login", ls.getMyWidth(), ls.getmyHeight());
        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Administrator login");
        stage.setResizable(false);
        stage.show();
        //ScenicView.show(scene);
    }
}