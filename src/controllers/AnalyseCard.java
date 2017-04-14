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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author ~dreeki~
 */
public class AnalyseCard extends VBox {

    @FXML
    private Label resultaatLabel;

    @FXML
    private Label jobcoachLabel;

    @FXML
    private Label datumLabel;

    @FXML
    private Label bedrijfOnderdeelLabel;

    @FXML
    private Label bedrijfLabel;

    @FXML
    private HBox kostbaatbox, kostbaatboxkost, kostbaatboxbaat;

    @FXML
    private Label kostenLabel, batenLabel;

    private Schermbeheer schermBeheer;

    private Analyse analyse;

    public AnalyseCard(Schermbeheer schermBeheer, Analyse a) {
        this.schermBeheer = schermBeheer;
        analyse = a;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AnalyseCard.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();

        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        fillInCard();
    }

    private void fillInCard() {
        /*
        TODO: Fill out rest of the labels. Will do when DB gets used.
         */

        bedrijfLabel.setText(analyse.geefWerkgever().getNaam());
        bedrijfOnderdeelLabel.setText(analyse.geefWerkgever().getNaamAfdeling());
    }

    @FXML
    void showDetails(ActionEvent event) {
        // shitty code alert, how do i fix this? Add parent to constructor?
        BorderPane parent = (BorderPane) this.getParent().getParent().getParent().getParent().getParent().getParent();
        parent.setCenter(new OverzichtAnalyseScherm(analyse, schermBeheer));
    }
}
