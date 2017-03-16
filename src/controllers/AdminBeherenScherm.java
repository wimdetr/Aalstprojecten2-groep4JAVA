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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author wimde
 */
public class AdminBeherenScherm extends GridPane {

    @FXML
    private TextField emailVeld;

    @FXML
    private TextField voornaamVeld;

    @FXML
    private TextField naamVeld;

    @FXML
    private Button adminVoegToeButton;

    public AdminBeherenScherm() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AdminBeherenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @FXML
    void voegAdminToe(ActionEvent event) {

    }
}
