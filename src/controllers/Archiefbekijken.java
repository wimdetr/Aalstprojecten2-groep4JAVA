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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author niels
 */
public class Archiefbekijken extends GridPane {
    @FXML
    private TextField tfNaam;

    @FXML
    private TextField tfVoornaam;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button btnOpslaan;

    @FXML
    private ImageView ivAfbeelding;

    @FXML
    private Label lblHuidigProfielNaam;

    @FXML
    private Hyperlink hlWachtwoordWijzigen;
    
    public Archiefbekijken() {
        this.setId("test");
        this.getStylesheets().add("/css/test.css");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ArchiefBekijken.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
