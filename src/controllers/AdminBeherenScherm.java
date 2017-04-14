/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Admin;
import domein.DomeinController;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author wimde
 */
public class AdminBeherenScherm extends BorderPane {

    @FXML
    private TableView<Admin> adminTableView;

    @FXML
    private TableColumn<Admin, String> voornaamColumn;

    @FXML
    private TableColumn<Admin, Boolean> checkboxColumn;

    @FXML
    private TableColumn<Admin, String> naamColumn;

    @FXML
    private TableColumn<Admin, String> emailColumn;

    @FXML
    private Button verwijderAdminBtn;

    @FXML
    private Button voegToeBtn;

    private DomeinController dc;

    private Schermbeheer beheer;

    public AdminBeherenScherm(Schermbeheer beheer) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AdminBeherenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        dc = beheer.getDc();
        this.beheer = beheer;
        checkboxColumn.setCellValueFactory(
                param -> param.getValue().isChecked()
        );
        checkboxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkboxColumn));

        voornaamColumn.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        naamColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        verwijderAdminBtn.setDisable(!dc.getAdmin().isSuperAdmin());
        checkboxColumn.setVisible(!dc.getAdmin().isSuperAdmin());

        adminTableView.setRowFactory(tv -> {
            TableRow<Admin> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Admin rowData = row.getItem();
                    rowData.setChecked(rowData.isChecked().get() ? Boolean.FALSE : Boolean.TRUE);
                }
            });
            return row;
        });

        adminTableView.setSelectionModel(null);
        adminTableView.setPlaceholder(new Label("Er zijn geen andere admins"));
        adminTableView.setItems(FXCollections.observableArrayList(dc.getAdminRepo().getLijst()));
    }

    @FXML
    void verwijderAdmin(ActionEvent event) {
        /*
            TODO
         */

    }

    @FXML
    void voegAdminToe(ActionEvent event) {
        beheer.plaatsPopUpScherm(new AdminToevoegenScherm(beheer), "Admin Toevoegen");
    }
}
