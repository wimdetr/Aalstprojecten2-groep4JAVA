/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.DomeinController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author wimde
 */
public class HoofdScherm extends BorderPane {

    @FXML
    private Button homeButton;

    @FXML
    private Button profielButton;

    @FXML
    private Button analyseButton;

    @FXML
    private Button jobcoachButton;

    @FXML
    private Button admintvgnButton;

    @FXML
    private Button logOutButton;

    private Stage stage;
    private DomeinController dc;

    public HoofdScherm(Stage stage, DomeinController dc) {
        this.stage = stage;
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/HoofdScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        stage.setOnCloseRequest(this::sluitScherm);
    }

    public DomeinController getDc() {
        return dc;
    }

    private void sluitScherm(Event event) {
        System.exit(0);
    }

    @FXML
    void logOut(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void toonProfielScherm(ActionEvent event) {

    }

    @FXML
    public void toonHomeScherm(ActionEvent event) {
        Stage stage = new Stage();
        BevestigVerwijderenScherm bvs = new BevestigVerwijderenScherm("Bevestig Verwijderen Jobcoach Mark", "Bent u zeker dat u Jobcoach Mark wilt verwijderen?", this, stage);
        Scene scene = new Scene(bvs, 700, 250);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
        //hoe verhinderen dat main stage kan gebruikt worden?
    }

    @FXML
    public void toonJobcoachesScherm(ActionEvent event) {
        GebruikerAccountsBeherenScherm scherm = new GebruikerAccountsBeherenScherm(dc);
        BorderPane.setMargin(scherm, new Insets(0, 0, 0, 10));
        this.setCenter(scherm);
    }

    @FXML
    public void toonAnalyseScherm(ActionEvent event) {

    }

    @FXML
    public void toonAdminToevoegenScherm(ActionEvent event) {
        AdminToevoegenScherm scherm = new AdminToevoegenScherm();
        BorderPane.setMargin(scherm, new Insets(0, 0, 0, 10));
        this.setCenter(scherm);
    }
}
