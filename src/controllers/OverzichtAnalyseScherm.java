/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Analyse;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author wimdetr
 */
public class OverzichtAnalyseScherm extends BorderPane {

    @FXML
    private Label baat1, baat2, baat3, baat4, baat5, baat6, baat7, baat8, baat9, baat10, baat11;

    @FXML
    private Label subtotaalBaat, subtotaalKost;

    @FXML
    private Label kost1, kost2, kost3, kost4, kost5, kost6, kost7, kost8;

    @FXML
    private Label resultaat;

    private Analyse analyse;

    private Stage prevStage;
    private Schermbeheer schermbeheer;

    public OverzichtAnalyseScherm(Analyse a, Stage prevStage, Schermbeheer beheer) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/OverzichtAnalyseScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        analyse = a;
        fillData();
        this.prevStage = prevStage;
        this.schermbeheer = beheer;
    }

    private void fillData() {
        /*
        TODO: fill up the textfields with data from the DB.
         */
    }

    @FXML
    void goBack(ActionEvent event) {
        schermbeheer.returnToPreviousScreen(prevStage);
    }

}
