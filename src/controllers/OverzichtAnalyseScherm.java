/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Analyse;
import domein.KOBEnum;
import domein.KostOfBaat;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.NumberUtil;

/**
 *
 * @author wimdetr
 */
public class OverzichtAnalyseScherm extends BorderPane {

    @FXML
    private Label gemeenteLabel, straatLabel, postcodeLabel, emailLabel;

    @FXML
    private Label baat1, baat2, baat3, baat4, baat5, baat6, baat7, baat8, baat9, baat10, baat11;

    @FXML
    private Label subtotaalBaat, subtotaalKost;

    @FXML
    private Label kost1, kost2, kost3, kost4, kost5, kost6, kost7, kost8;

    @FXML
    private Label resultaat, overzichtAnalyseTitel, overzichtAnalyseSubtitel;

    private Analyse analyse;

    private Schermbeheer schermbeheer;

    public OverzichtAnalyseScherm(Analyse a, Schermbeheer beheer) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/OverzichtAnalyseScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        analyse = a;
        this.schermbeheer = beheer;
        fillData();

    }

    private void fillData() {
        /*
        TODO: mss in een lijst zetten want dit is wel heel lelijk ma bon
         */
        overzichtAnalyseTitel.setText(analyse.getWerkgever().getNaam() + " - " + analyse.getWerkgever().getNaamAfdeling()
                + " (" + analyse.getJobcoach().getVoornaam() + " " + analyse.getJobcoach().getNaam() + ")");

        String bus = analyse.getWerkgever().getBus() == null ? "" : analyse.getWerkgever().getBus();
        gemeenteLabel.setText(analyse.getWerkgever().getGemeente());
        straatLabel.setText(analyse.getWerkgever().getStraat() + " "
                + analyse.getWerkgever().getNummer()
                + bus);
        postcodeLabel.setText(Integer.toString(analyse.getWerkgever().getPostcode()));
        emailLabel.setText(analyse.getWerkgever().getContactPersoonEmail());
        List<Label> baten = Arrays.asList(baat1, baat2, baat3, baat4, baat5, baat6, baat7, baat8, baat9, baat10, baat11);
        List<Label> kosten = Arrays.asList(kost1, kost2, kost3, kost4, kost5, kost6, kost7, kost8);
        //baten
        for (int i = 0; i < 11; i++) {
            KostOfBaat a = analyse.geefKostOfBaatMetNummer(KOBEnum.Baat, i + 1);
            if (a != null) {
                baten.get(i).setText(NumberUtil.formatDouble(a.getResultaat()));
            }
        }

        for (int i = 0; i < 8; i++) {
            KostOfBaat a = analyse.geefKostOfBaatMetNummer(KOBEnum.Kost, i + 1);
            if (a != null) {
                kosten.get(i).setText(NumberUtil.formatDouble(a.getResultaat()));
            }
        }

        resultaat.setText(NumberUtil.formatDouble(analyse.getResultaat()));
        subtotaalBaat.setText(NumberUtil.formatDouble(analyse.getResultaatBaten()));
        subtotaalKost.setText(NumberUtil.formatDouble(analyse.getResultaatKosten()));

    }

    @FXML
    void goBack(ActionEvent event) {
        /*
        TODO: Go back to previous screen.
         */

    }

}
