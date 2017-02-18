/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

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

    public HoofdScherm() {
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
        System.exit(0);
    }

    @FXML
    void toonAdminToevoegenScherm(ActionEvent event) {

    }

    @FXML
    void toonAnalyseScherm(ActionEvent event) {

    }

    @FXML
    void toonHomeScherm(ActionEvent event) {

    }

    @FXML
    void toonJobcoachesScherm(ActionEvent event) {

    }

    @FXML
    void toonProfielScherm(ActionEvent event) {

    }

}
