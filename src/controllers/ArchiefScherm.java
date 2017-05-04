/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Analyse;
import domein.Werkgever;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import util.NumberUtil;

/**
 *
 * @author niels
 */
public class ArchiefScherm extends BorderPane {

    @FXML
    private TableView<Analyse> archiefTableView;

    @FXML
    private TableColumn<Analyse, Number> idColumn;
    @FXML
    private TableColumn<Analyse, String> voornaamColumn;

    @FXML
    private TableColumn<Analyse, String> naamColumn;

    @FXML
    private TableColumn<Analyse, String> bedrijfColumn;

    @FXML
    private TableColumn<Analyse, String> departementColumn;

    @FXML
    private TableColumn<Analyse, String> resultaatColumn;

    @FXML
    private TableColumn<Analyse, String> datumColumn;

    private ObservableList<Analyse> data;

    private Schermbeheer beheer;

    public ArchiefScherm(Schermbeheer beheer) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ArchiefScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        this.beheer = beheer;
        data = FXCollections.observableArrayList(beheer.getDc().getAnalyseRepo().getLijst());

        idColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getId()));
        voornaamColumn.setCellValueFactory(e -> e.getValue().getJobcoach().voornaamProperty());
        naamColumn.setCellValueFactory(e -> e.getValue().getJobcoach().naamProperty());
        bedrijfColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getWerkgever().getNaam()));
        departementColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getWerkgever().getNaamAfdeling()));
        resultaatColumn.setCellValueFactory(e -> new SimpleStringProperty(NumberUtil.formatDouble(e.getValue().getResultaat())));
        datumColumn.setCellValueFactory(e
                -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(dateFormat.format(e.getValue().getLaatsteAanpasDatum()));
            return property;
        });
        archiefTableView.setRowFactory((e) -> {
            TableRow<Analyse> rij = new TableRow<>();
            rij.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !rij.isEmpty()) {
                    Analyse a = rij.getItem();
                    OverzichtAnalyseScherm scherm = new OverzichtAnalyseScherm(a, beheer);
                    beheer.setMiddenScherm(scherm);
                }
            });
            return rij;
        });
        archiefTableView.setItems(data);
    }

}
