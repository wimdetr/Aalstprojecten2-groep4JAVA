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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    private Button toonProfielButton;

    @FXML
    private Label lblProfiel;
    
    @FXML
    private Button jobcoachButton;

    @FXML
    private Button admintvgnButton;

    @FXML
    private Button logOutButton;

    private SchermBeheer schermBeheer;
    private DomeinController dc;
    private final int myWidth = 925;
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

    }

    @FXML
    void logOut(ActionEvent event) {
        dc.logAdminUit();
        LoginScherm ls = new LoginScherm(schermBeheer);

          schermBeheer.zetStageResizable(false);
        schermBeheer.plaatsScherm(ls, "loginSchermId", "/css/loginScherm.css", "Login", ls.getMyWidth(), ls.getmyHeight());
    }

    @FXML
    public void toonProfielScherm(ActionEvent event) {
        Archiefbekijken ab = new Archiefbekijken();
        this.setCenter(ab);
        BorderPane.setAlignment(ab, Pos.TOP_LEFT);
    }

    @FXML
    public void toonHomeScherm(ActionEvent event) {
        OverzichtScherm os = new OverzichtScherm(schermBeheer);
        BorderPane.setAlignment(os, Pos.TOP_LEFT);
        this.setCenter(os);
    }

    @FXML
    public void toonJobcoachesScherm(ActionEvent event) {
        GebruikerAccountsBeherenScherm scherm = new GebruikerAccountsBeherenScherm(schermBeheer);
        BorderPane.setMargin(scherm, new Insets(0, 0, 0, 10));
        this.setCenter(scherm);
        BorderPane.setAlignment(scherm, Pos.TOP_LEFT);
    }

    @FXML
    public void toonAnalyseScherm(ActionEvent event) {
        ProfielBekijkenScherm scherm = new ProfielBekijkenScherm();
        this.setCenter(scherm);
        GridPane.setHalignment(scherm, HPos.LEFT);
    }

    @FXML
    void klikToonProfiel(MouseEvent event) {
        ProfielBekijkenScherm scherm = new ProfielBekijkenScherm();
        this.setCenter(scherm);
        GridPane.setHalignment(scherm, HPos.LEFT);
    }
    
    @FXML
    public void toonAdminToevoegenScherm(ActionEvent event) {
        AdminToevoegenScherm scherm = new AdminToevoegenScherm();
        //BorderPane.setMargin(scherm, new Insets(0, 0, 0, 10));
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
