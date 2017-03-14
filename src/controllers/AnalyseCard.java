/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ~dreeki~
 */
public class AnalyseCard extends VBox {

    @FXML
    private Label kostenLabel;

    @FXML
    private Label batenLabel;

    @FXML
    private Label resultaatLabel;

    @FXML
    private Label jobcoachLabel;

    @FXML
    private Label datumLabel;

    @FXML
    private Label onderdeelLabel;

    private Schermbeheer schermBeheer;

    public AnalyseCard(Schermbeheer schermBeheer) {
        this.schermBeheer = schermBeheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AnalyseCard.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @FXML
    void showDetails(ActionEvent event) {

    }

    @FXML
    void enterDownloadKnop(KeyEvent event) {

    }

    @FXML
    void enterVerturenKnop(KeyEvent event) {

    }

    @FXML
    void enterVerwijderKnop(KeyEvent event) {

    }

    @FXML
    void klikDownloadKnop(ActionEvent event) {

    }

    @FXML
    void klikVersturenKnop(ActionEvent event) {

    }

    @FXML
    void klikVerwijderKnop(ActionEvent event) {

    }

}
