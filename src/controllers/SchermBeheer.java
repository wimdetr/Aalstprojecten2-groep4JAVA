/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.DomeinController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ~dreeki~
 */
public class SchermBeheer extends VBox {

    private final DomeinController dc;
    private final Stage mainStage;
    private Stage popUpStage;

    public Stage getPopUpStage() {
        return popUpStage;
    }

    public SchermBeheer(DomeinController dc, Stage stage) {
        this.dc = dc;
        mainStage = stage;

        mainStage.setOnCloseRequest(e -> {
            if (popUpStage != null) {
                popUpStage.close();
                this.setDisable(false);
            }
        });
    }

    public void zetStageResizable(boolean resizable) {
        mainStage.setResizable(resizable);
    }

    public void plaatsScherm(Node node, String schermTitel, int width, int height) {
        getChildren().setAll(node);
        VBox.setVgrow(node, Priority.ALWAYS);
        mainStage.setWidth(width);
        mainStage.setHeight(height);
        mainStage.setTitle(schermTitel);

        mainStage.centerOnScreen();
    }

    public void plaatsPopUpScherm(Parent popUpScreen, String schermTitel) {
        this.setDisable(true);
        popUpStage = new Stage();
        Scene scene = new Scene(popUpScreen);
        popUpStage.setScene(scene);
        popUpStage.setAlwaysOnTop(true);
        popUpStage.setTitle(schermTitel);
        popUpStage.show();
        popUpStage.setOnCloseRequest(e -> {
            sluitPopUpScherm();
        });
    }

    public void sluitPopUpScherm() {
        popUpStage.close();
        this.setDisable(false);
    }

    public DomeinController getDc() {
        return dc;
    }

}
