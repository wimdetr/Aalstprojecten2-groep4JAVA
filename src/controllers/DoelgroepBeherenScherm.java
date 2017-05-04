/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Doelgroep;
import java.io.IOException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import util.NumberUtil;

/**
 *
 * @author wimde
 */
public class DoelgroepBeherenScherm extends BorderPane {

    @FXML
    private TableView<Doelgroep> doelgroepTableView;

    @FXML
    private TableColumn<Doelgroep, Number> idColumn;

    @FXML
    private TableColumn<Doelgroep, String> beschrijvingColumn;

    @FXML
    private TableColumn<Doelgroep, String> maxLoonColumn;

    @FXML
    private TableColumn<Doelgroep, String> doelgroepverminderingColumn;

    @FXML
    private TableColumn<Doelgroep, String> actiefColumn;

    private Schermbeheer beheer;

    public DoelgroepBeherenScherm(Schermbeheer beheer) {
        this.beheer = beheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DoelgroepBeherenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        /*
        cellvalue factories
         */
        idColumn.setCellValueFactory(e -> new SimpleIntegerProperty(e.getValue().getDoelgroepId()));
        beschrijvingColumn.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getDoelgroepText()));
        maxLoonColumn.setCellValueFactory(e -> new SimpleStringProperty(NumberUtil.formatDouble(e.getValue().getDoelgroepMaxLoon())));
        doelgroepverminderingColumn.setCellValueFactory(e -> new SimpleStringProperty(NumberUtil.formatDouble(e.getValue().getDoelgroepWaarde())));
        actiefColumn.setCellValueFactory((e) -> {
            e.getValue().getIsVerwijderd();
            String answer = e.getValue().getIsVerwijderd() ? "NEEN" : "JA";
            return new SimpleStringProperty(answer);
        });
        doelgroepTableView.setItems(FXCollections.observableList(beheer.getDc().getDoelgroepRepo().getLijst()));
        doelgroepTableView.setRowFactory((e) -> {
            TableRow<Doelgroep> rij = new TableRow<>();
            rij.setOnMouseClicked(ev -> {
                if (ev.getClickCount() == 2 && !rij.isEmpty()) {
                    Doelgroep d = rij.getItem();
                    beheer.plaatsPopUpScherm(new DoelgroepWijzigenScherm(beheer,d), "Doelgroep wijzigen");
                    doelgroepTableView.refresh();
                }
            });
            return rij;
        });
    }

    @FXML
    void doelgroepToevoegen(ActionEvent event) {
        beheer.plaatsPopUpScherm(new DoelgroepToevoegenScherm(beheer), "Doelgroep toevoegen");
        doelgroepTableView.refresh();
    }

}
