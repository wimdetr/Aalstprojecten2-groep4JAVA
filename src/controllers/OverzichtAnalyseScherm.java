/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Analyse;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author iSayBoom
 */
public class OverzichtAnalyseScherm extends BorderPane {

    @FXML
    private TableView<?> overzichtTableView;
    @FXML
    private TableColumn<?, ?> batenColumn;

    @FXML
    private TableColumn<?, ?> resultColumn;

    @FXML
    private TableColumn<?, ?> batenResColumn;

    @FXML
    private TableColumn<?, ?> kostenResColumn;

    @FXML
    private TableColumn<?, ?> kostenColumn;

    public OverzichtAnalyseScherm(Analyse a) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/OverzichtAnalyseScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

}
