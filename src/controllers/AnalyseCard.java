/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Analyse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import util.NumberUtil;

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
    private GridPane kostbaatbox;
    @FXML
    private HBox kostbaatboxkost, kostbaatboxbaat, resultBox;

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

        double kosten = analyse.getResultaatKosten();
        double baten = analyse.getResultaatBaten();
        double resultaat = analyse.getResultaat();
        bedrijfLabel.setText(analyse.getDepartement().getWerkgever().getNaam());
        bedrijfOnderdeelLabel.setText(analyse.getDepartement().getNaam());
        kostenLabel.setText(NumberUtil.formatDouble(kosten));
        batenLabel.setText(NumberUtil.formatDouble(baten));
        resultaatLabel.setText(NumberUtil.formatDouble(resultaat));
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        datumLabel.setText(dateFormat.format(analyse.getLaatsteAanpasDatum()));
        if (resultaat >= 0) {
            resultBox.getStyleClass().add("resultBoxPositive");
        } else {
            resultBox.getStyleClass().add("resultBoxNegative");
        }
        if (kosten + baten != 0) {
            double kostShare = kosten / (kosten + baten);
            double baatShare = baten / (kosten + baten);
            kostbaatbox.getColumnConstraints().get(0).setPercentWidth(NumberUtil.getAsPercent(kostShare));
            kostbaatbox.getColumnConstraints().get(1).setPercentWidth(NumberUtil.getAsPercent(baatShare));

        }
    }

    @FXML
    void showDetails(ActionEvent event) {
        schermBeheer.setMiddenScherm(new OverzichtAnalyseScherm(analyse, schermBeheer));
    }
}
