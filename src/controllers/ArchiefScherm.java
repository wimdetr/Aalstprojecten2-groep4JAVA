/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Analyse;
import domein.JobCoach;
import domein.Resultaat;
import domein.Werkgever;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author niels
 */
public class ArchiefScherm extends BorderPane {

    @FXML
    private TreeTableView<String> treeTableView;

    @FXML
    private TreeTableColumn<String, String> bedrijfColumn;

    @FXML
    private TreeTableColumn<Analyse, String> datumColumn;

    @FXML
    private TreeTableColumn<JobCoach, String> jobcoachColumn;

    @FXML
    private TreeTableColumn<Resultaat, String> resultaatColumn;

    public ArchiefScherm() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ArchiefScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        fillUp();
    }

    private void fillUp() {
        //testing purposes
        TreeItem<String> root = new TreeItem<>();
        
        TreeItem<String> bed1 = new TreeItem<>("Colruyt");
        TreeItem<String> dep1 = new TreeItem<>("Logistics");
        TreeItem<String> dep2 = new TreeItem<>("Marketing");
        TreeItem<String> dep3 = new TreeItem<>("Sales");
        bed1.getChildren().setAll(dep1, dep2, dep3);
        TreeItem<String> bed2 = new TreeItem<>("De Nationale Loterij");
        TreeItem<String> dep4 = new TreeItem<>("IT");
        TreeItem<String> dep5 = new TreeItem<>("HR");
        TreeItem<String> dep6 = new TreeItem<>("Management");
        bed2.getChildren().setAll(dep4, dep5, dep6);
        root.getChildren().addAll(bed1,bed2);
        TreeItem<String> time1 = new TreeItem<>(new Date().toString());
        TreeItem<String> time2 = new TreeItem<>(new Date().toString());
        TreeItem<String> time3 = new TreeItem<>(new Date().toString());

        bedrijfColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<String, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue())
        );
        
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);
    }
}
