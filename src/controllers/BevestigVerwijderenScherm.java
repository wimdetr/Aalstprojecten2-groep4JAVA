/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ~dreeki~
 */
public class BevestigVerwijderenScherm extends VBox implements IsPopUpScherm {

    @FXML
    private Label lblTitel;

    @FXML
    private Label lblDetail;

    @FXML
    private Button btnAnnuleer;

    @FXML
    private Button btnBevestig;

    private SchermBeheer schermBeheer;

    private boolean magVerwijderdWorden;

    public BevestigVerwijderenScherm(SchermBeheer schermBeheer, String titel, String detail) {
        this.schermBeheer = schermBeheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/BevestigVerwijderenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        lblTitel.setText(titel);
        lblDetail.setText(detail);
    }

    @FXML
    private void enterAnnuleer(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            bevestig(false);
        }
    }

    @FXML
    private void enterBevestig(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            bevestig(true);
        }
    }

    @FXML
    private void klikAnnuleer(ActionEvent event) {
        bevestig(false);
    }

    @FXML
    private void klikBevestig(ActionEvent event) {
        bevestig(true);
    }

    private void bevestig(boolean bevestigd) {
        magVerwijderdWorden = bevestigd;
        schermBeheer.sluitPopUpScherm();
    }

    @Override
    public void sluit() {
        Stage stage = (Stage) this.getScene().getWindow();
        stage.close();
    }

    public boolean getMagVerwijderdWorden() {
        return magVerwijderdWorden;
    }

}
