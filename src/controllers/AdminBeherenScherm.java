/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Admin;
import domein.DomeinController;
import domein.JobCoach;
import domein.repository.AdminRepository;
import java.io.IOException;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.fxmisc.easybind.EasyBind;
import persistentie.JobCoachMapper;

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

    private ObservableList<Admin> data;

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
        data = FXCollections.observableList(dc.getAdminRepo().getLijst());
        checkboxColumn.setCellValueFactory(
                param -> param.getValue().isChecked()
        );
        checkboxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkboxColumn));

        voornaamColumn.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        naamColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        verwijderAdminBtn.setVisible(dc.getAdmin().isSuperAdmin());
        checkboxColumn.setVisible(dc.getAdmin().isSuperAdmin());

        adminTableView.setRowFactory(tv -> {
            TableRow<Admin> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Admin rowData = row.getItem();
                    rowData.setChecked(rowData.isChecked().get() ? Boolean.FALSE : Boolean.TRUE);
                }
            });
            row.disableProperty().bind(EasyBind.select(row.itemProperty())
                    .selectObject(Admin::emailProperty)
                    .map(a -> a.equals(beheer.getDc().getAdmin().getEmail())));
            return row;
        });
        adminTableView.setItems(data);
        BooleanBinding checkBinding = new BooleanBinding() {
            {
                data.stream().map(c -> c.isChecked()).forEach(super::bind);
            }

            @Override
            protected boolean computeValue() {
                return data.stream().noneMatch(c -> c.isChecked().get());
            }
        };
        verwijderAdminBtn.disableProperty().bind(checkBinding);
    }

    @FXML
    void verwijderAdmin(ActionEvent event) {
        ObservableList<Admin> admins = data.filtered(p -> p.isChecked().get() == true);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Verwijderconfirmatie");
        alert.setHeaderText("Verwijderen Administrator(s)");
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Admin a : admins) {
            sb.append(i)
                    .append(". ")
                    .append(a.getVoornaam())
                    .append(" ")
                    .append(a.getNaam())
                    .append("\n");
            i++;
        }

        alert.setContentText("Bent u zeker dat u volgende admin(s) echt wil verwijderen? \n \n" + sb.toString());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            AdminRepository repo = dc.getAdminRepo();
            for (Admin a : admins) {
                repo.deleteAdmin(a);
                a.setChecked(Boolean.FALSE);
            }
            data.removeAll(admins);
        }

    }

    @FXML
    void voegAdminToe(ActionEvent event) {
        beheer.plaatsPopUpScherm(new AdminToevoegenScherm(beheer), "Admin Toevoegen");
        adminTableView.refresh(); // this method will get executed after popupstage gets closed, something something spaghetti
    }
}
