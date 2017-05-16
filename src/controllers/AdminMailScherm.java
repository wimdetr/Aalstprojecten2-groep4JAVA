/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Admin;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author wimde
 */
public class AdminMailScherm extends BorderPane implements Initializable{

    @FXML
    private VBox emailBox;

    @FXML
    private ScrollPane emailScrollPane;


    private Schermbeheer beheer;

    AdminMailScherm(Schermbeheer schermBeheer) {
        beheer = schermBeheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AdminMailScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        

    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
                Admin admin = beheer.getDc().getAdmin();
        admin.getMails().stream().sorted(Comparator.reverseOrder()).forEach(o -> {
            emailBox.getChildren().add(new AdminMailLeftComponent(o,this.centerProperty(),beheer));
        });
    }
}
