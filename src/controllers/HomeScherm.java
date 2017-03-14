/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Analyse;
import domein.DomeinController;
import domein.repository.AnalyseRepository;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author ~dreeki~
 */
public class HomeScherm extends VBox {

    @FXML
    private FlowPane flowPaneRecenteAnalyses;

    private Schermbeheer schermBeheer;

    public HomeScherm(Schermbeheer schermBeheer) {
        this.schermBeheer = schermBeheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/HomeScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        /*
        Data for testing purposes and DEMO!
        */
        DomeinController dc = schermBeheer.getDc();
        AnalyseRepository repo = dc.getAnalyseRepo();
        List<Analyse> list = repo.getLijst();
        for (int i = 0; i < 8; i++) {
            flowPaneRecenteAnalyses.getChildren().add(new AnalyseCard(this.schermBeheer,list.get(i)));
        }
    }

}
