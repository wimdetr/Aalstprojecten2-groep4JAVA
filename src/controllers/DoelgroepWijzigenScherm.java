/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Doelgroep;
import domein.repository.DoelgroepRepository;
import java.io.IOException;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author wimde
 */
public class DoelgroepWijzigenScherm extends BorderPane {

    @FXML
    private TextField brutoloonVeld;

    @FXML
    private TextField verminderingVeld;

    @FXML
    private CheckBox isActiefCheckbox;

    @FXML
    private Label errorLabel;

    private Schermbeheer beheer;
    private Doelgroep doelgroep;

    public DoelgroepWijzigenScherm(Schermbeheer beheer, Doelgroep d) {
        this.beheer = beheer;
        doelgroep = d;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DoelgroepWijzigenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        fillData();
    }

    private void fillData() {
        brutoloonVeld.setText(Double.toString(doelgroep.getDoelgroepMaxLoon()));
        verminderingVeld.setText(Double.toString(doelgroep.getDoelgroepWaarde()));
        isActiefCheckbox.setSelected(!doelgroep.getIsVerwijderd());
    }

    private boolean isValid() {
        return (checkTextField("^[0-9,.]*$", brutoloonVeld)
                & checkTextField("^[0-9,.]*$", verminderingVeld));
    }

    private boolean checkTextField(String pattern, TextField txf) {
        if (Pattern.matches(pattern, txf.getText())) {
            removeIncorrectStyling(txf);
            return true;
        } else {
            addIncorrectStyling(txf);
            return false;
        }
    }

    private void addIncorrectStyling(TextField txf) {
        txf.getStyleClass().add("textfieldIncorrect");
    }

    private void removeIncorrectStyling(TextField txf) {
        txf.getStyleClass().remove("textfieldIncorrect");
    }

    @FXML
    void doCancel(ActionEvent event) {
        beheer.sluitPopUpScherm();
    }

    @FXML
    void doOpslaan(ActionEvent event) {
        if (isValid()) {
            errorLabel.setVisible(false);
            DoelgroepRepository repo = beheer.getDc().getDoelgroepRepo();
            doelgroep.setDoelgroepMaxLoon(Double.parseDouble(brutoloonVeld.getText()));
            doelgroep.setDoelgroepWaarde(Double.parseDouble(verminderingVeld.getText()));
            doelgroep.setIsVerwijderd(!isActiefCheckbox.isSelected());
            repo.modifyDoelgroep(doelgroep);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Doelgroep gewijzigd");
            alert.setHeaderText("Doelgroep gewijzigd");
            alert.setContentText("De doelgroep is succesvol gewijzigd!");
            alert.showAndWait();
            beheer.sluitPopUpScherm();
        }
        errorLabel.setVisible(true);
    }

}
