/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.DomeinController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author ~dreeki~
 */
public class Schermbeheer {

    private final DomeinController dc;
    private final Stage mainStage;
    private final Stage popUpStage;

    
    public Schermbeheer(DomeinController dc, Stage stage) {
        this.dc = dc;
        mainStage = stage;
        popUpStage = new Stage();
        mainStage.getIcons().add(new Image(getClass().getResource("/img/kairos_icon_black.png").toString()));
        popUpStage.getIcons().add(new Image(getClass().getResource("/img/kairos_icon_black.png").toString()));
        mainStage.setOnCloseRequest(e -> {
            sluitPopUpScherm();
        });
    }

    public void setMainStageResizable(boolean resizable) {
        mainStage.setResizable(resizable);
    }

    private void setDisableMainStage(boolean disabled) {
        mainStage.getScene().getRoot().setDisable(disabled);
    }

    public void plaatsScherm(Parent screen, String schermTitel) {
        Scene scene = new Scene(screen);
        mainStage.setScene(scene);
        mainStage.setTitle(schermTitel);
        mainStage.centerOnScreen();
        mainStage.show();
    }

    public void plaatsPopUpScherm(Parent popUpScreen, String schermTitel) {
        // todo: niet altijd nieuwe scene maken
        setDisableMainStage(true);
        Scene scene = new Scene(popUpScreen);
        popUpStage.setScene(scene);
        popUpStage.setTitle(schermTitel);
        popUpStage.show();
        popUpStage.setOnCloseRequest(e -> {
            sluitPopUpScherm();
        });
    }

    public void sluitPopUpScherm() {
        popUpStage.close();
        setDisableMainStage(false);
    }

    public DomeinController getDc() {
        return dc;
    }

}
