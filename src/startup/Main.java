/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import controllers.LoginScherm;
import controllers.Schermbeheer;
import domein.DomeinController;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.lang.RandomStringUtils;
import org.scenicview.ScenicView;
import util.NumberUtil;

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
