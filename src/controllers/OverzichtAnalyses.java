/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

/**
 *
 * @author iSayBoom
 */
public class OverzichtAnalyses extends TabPane{
    @FXML
    private TableView<?> tvBaten;

    @FXML
    private Label lblSubtotaalBatenVanBaten;

    @FXML
    private Label lblGetalVanBaten;

    @FXML
    private Button btnVerwijderBaten;

    @FXML
    private Button btnVerzendenBaten;

    @FXML
    private Button btnArchiveerBaten;

    @FXML
    private Label lblTitelAnalyseBaten;

    @FXML
    private TableView<?> tvOverzicht;

    @FXML
    private Label lblSubtotaalBatenVanOverzicht;

    @FXML
    private Label lblGetalBatenVanOverzicht;

    @FXML
    private Label lblGetalKostenVanOverzicht;

    @FXML
    private Label lblSubtotaalKostenVanOverzicht;

    @FXML
    private Button btnVerwijderOverzicht;

    @FXML
    private Button btnArchiveerOverzicht;

    @FXML
    private Button btnVerzendenOverzicht;

    @FXML
    private Label lblTitelAnalyseOverzicht;

    @FXML
    private TableView<?> tvKosten;

    @FXML
    private Label lblGetalVanKosten;

    @FXML
    private Label lblSubtotaalKostenVanKosten;

    @FXML
    private Button btnVerwijderKosten;

    @FXML
    private Button btnArchiveerKosten;

    @FXML
    private Button btnVerzendenKosten;

    @FXML
    private Label lblTitelVanAnalyseKosten;
    
    public OverzichtAnalyses() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/OverzichtAnalyses.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
