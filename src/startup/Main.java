/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import controllers.LoginScherm;
import controllers.Schermbeheer;
import domein.DomeinController;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.lang.RandomStringUtils;
import org.scenicview.ScenicView;

/**
 *
 * @author ~dreeki~
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Schermbeheer schermbeheer = new Schermbeheer(new DomeinController(), stage);
        LoginScherm ls = new LoginScherm(schermbeheer);
        schermbeheer.plaatsScherm(ls, "Kairos - Administrator login");
        schermbeheer.setMainStageResizable(false);
        //ScenicView.show(scene);
    }
}
