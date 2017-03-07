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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author wimde
 */
public class HoofdScherm extends BorderPane {

    private SchermBeheer schermBeheer;
    private DomeinController dc;
    private final int myWidth = 1000;
    private final int myHeight = 715;

    public HoofdScherm(SchermBeheer schermBeheer) {
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
//
//        HomeScherm os = new HomeScherm(schermBeheer);
//        BorderPane.setAlignment(os, Pos.TOP_LEFT);
//        this.setCenter(os);
    }

    @FXML
    void doLogOut(ActionEvent event) {
        dc.logAdminUit();
        LoginScherm ls = new LoginScherm(schermBeheer);

        schermBeheer.zetStageResizable(false);
        schermBeheer.plaatsScherm(ls, "loginSchermId", "/css/loginScherm.css", "Login", ls.getMyWidth(), ls.getmyHeight());
    }

    @FXML
    public void toonProfielScherm(ActionEvent event) {

        ProfielBekijkenScherm scherm = new ProfielBekijkenScherm();
        this.setCenter(scherm);
        GridPane.setHalignment(scherm, HPos.LEFT);
    }

    @FXML
    public void toonHomeScherm(ActionEvent event) {
        HomeScherm os = new HomeScherm(schermBeheer);
        BorderPane.setAlignment(os, Pos.TOP_LEFT);
        this.setCenter(os);
    }

    @FXML
    public void toonJobcoachesScherm(ActionEvent event) {
        GebruikersBeherenScherm scherm = new GebruikersBeherenScherm(schermBeheer);
        this.setCenter(scherm);
        BorderPane.setAlignment(scherm, Pos.TOP_LEFT);
    }

    @FXML
    public void toonAnalyseScherm(ActionEvent event) {
        Archiefbekijken scherm = new Archiefbekijken();
        this.setCenter(scherm);
        GridPane.setHalignment(scherm, HPos.LEFT);
    }

    @FXML
    void klikToonProfiel(MouseEvent event) {
        ProfielBekijkenScherm ab = new ProfielBekijkenScherm();
        this.setCenter(ab);
        BorderPane.setAlignment(ab, Pos.TOP_LEFT);
    }

    @FXML
    public void toonAdminToevoegenScherm(ActionEvent event) {
        AdminToevoegenScherm scherm = new AdminToevoegenScherm();
        this.setCenter(scherm);
        GridPane.setHalignment(scherm, HPos.LEFT);
    }

    public int getMyWidth() {
        return myWidth;
    }

    public int getMyHeight() {
        return myHeight;
    }

}
