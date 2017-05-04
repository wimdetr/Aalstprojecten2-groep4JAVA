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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author wimde
 */
public class DoelgroepToevoegenScherm extends BorderPane {

    @FXML
    private TextField brutoloonVeld;

    @FXML
    private TextField verminderingVeld;

    @FXML
    private Label errorLabel;

    @FXML
    private TextArea beschrijvingTextArea;

    private Schermbeheer beheer;

    public DoelgroepToevoegenScherm(Schermbeheer beheer) {
        this.beheer = beheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/DoelgroepToevoegenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private boolean isValid() {
        return (checkTextField("^[0-9,.]*$", brutoloonVeld)
                & checkTextField("^[0-9,.]*$", verminderingVeld))
                & !beschrijvingTextArea.getText().isEmpty();
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
    void doVoegToe(ActionEvent event) {
        if (isValid()) {
            errorLabel.setVisible(false);
            // voeg doelgroep toe
            Doelgroep d = new Doelgroep(beschrijvingTextArea.getText(),
                    Double.parseDouble(brutoloonVeld.getText()),
                    Double.parseDouble(verminderingVeld.getText()));
            DoelgroepRepository repo = beheer.getDc().getDoelgroepRepo();
            repo.addDoelgroep(d);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Doelgroep toegevoegd");
            alert.setHeaderText("Doelgroep toegevoegd");
            alert.setContentText("De doelgroep is succesvol toegevoegd!");
            alert.showAndWait();
            beheer.sluitPopUpScherm();
        } else {
            errorLabel.setVisible(true);

        }
    }
}
