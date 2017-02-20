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
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author niels
 */
public class ArchiefBekijken extends GridPane{
    
    @FXML
    private TableView<?> tvTabel;

    @FXML
    private Button btnExport;

    @FXML
    private Button btnZoek;

    public ArchiefBekijken() {
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
