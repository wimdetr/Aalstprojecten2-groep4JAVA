/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.DomeinController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import util.JPAUtil;

/**
 *
 * @author wimde
 */
public class HoofdScherm extends BorderPane {

    private Schermbeheer schermBeheer;
    private DomeinController dc;

    @FXML
    private Label naamLabel;

    @FXML
    private MenuButton menuButton;

    public HoofdScherm(Schermbeheer schermBeheer) {
        this.schermBeheer = schermBeheer;
        this.dc = schermBeheer.getDc();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/HoofdScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        menuButton.setText(dc.getAdmin().getVoornaam() + " " + dc.getAdmin().getNaam());
        HomeScherm os = new HomeScherm(schermBeheer);
        this.setCenter(os);
    }

    @FXML
    private void doLogOut(ActionEvent event) {
        dc.logAdminUit();
        JPAUtil.destroyConnection();
        LoginScherm ls = new LoginScherm(schermBeheer);
        schermBeheer.plaatsScherm(ls, "Login");
        schermBeheer.setMainStageResizable(false);
    }

    @FXML
    private void wachtwoordVeranderen(ActionEvent event) {
        schermBeheer.plaatsPopUpScherm(new WachtwoordWijzigenScherm(schermBeheer), "Wachtwoord wijzigen");
    }

    @FXML
    public void toonHomeScherm(ActionEvent event) {
        HomeScherm os = new HomeScherm(schermBeheer);
        this.setCenter(os);
    }

    @FXML
    public void toonJobcoachesScherm(ActionEvent event) {
        GebruikersBeherenScherm scherm = new GebruikersBeherenScherm(schermBeheer);
        this.setCenter(scherm);
    }

    @FXML
    public void toonDoelgroepScherm(ActionEvent event) {
        DoelgroepBeherenScherm scherm = new DoelgroepBeherenScherm(schermBeheer);
        this.setCenter(scherm);
    }

    @FXML
    public void toonAnalyseScherm(ActionEvent event) {
        ArchiefScherm scherm = new ArchiefScherm(schermBeheer);
        this.setCenter(scherm);
    }

    @FXML
    public void toonAdminToevoegenScherm(ActionEvent event) {
        AdminBeherenScherm scherm = new AdminBeherenScherm(schermBeheer);
        this.setCenter(scherm);
    }

    @FXML
    void toonWerkgeverScherm(ActionEvent event) {
        WerkgeverBeherenScherm scherm = new WerkgeverBeherenScherm(schermBeheer);
        this.setCenter(scherm);
    }

    @FXML
    void toonEmailScherm(ActionEvent event) {
        AdminMailScherm scherm = new AdminMailScherm(schermBeheer);
        this.setCenter(scherm);
    }

}
