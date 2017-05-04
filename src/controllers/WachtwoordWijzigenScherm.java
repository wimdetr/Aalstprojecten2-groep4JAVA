/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.repository.AdminRepository;
import java.io.IOException;
import java.util.Optional;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import persistentie.AdminMapper;

/**
 *
 * @author wimde
 */
public class WachtwoordWijzigenScherm extends BorderPane {

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField newPasswordFieldAgain;

    @FXML
    private Label errorLabel;

    private Schermbeheer beheer;

    public WachtwoordWijzigenScherm(Schermbeheer beheer) {
        this.beheer = beheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/WachtwoordWijzigenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private boolean isValid() {
        String passw = newPasswordField.getText();
        String passwAgain = newPasswordFieldAgain.getText();
        if (!(passw.isEmpty() && passwAgain.isEmpty())) {
            if (passw.equals(passwAgain)) {
                if (passw.length() >= 6) {
                    return true;
                }
            }
        }
        return false;
    }

    @FXML
    void doCancel(ActionEvent event) {
        beheer.sluitPopUpScherm();
    }

    @FXML
    void doWijzig(ActionEvent event) {
        if (isValid()) {
            errorLabel.setVisible(false);
            AdminRepository repo = beheer.getDc().getAdminRepo();
            repo.changePasswordForCurrentUser(newPasswordField.getText());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Wachtwoord gewijzigd");
            alert.setHeaderText("Wachtwoord gewijzigd");
            alert.setContentText("U heeft uw wachtwoord succesvol gewijzigd!");
            alert.showAndWait();
            Preferences prefs = Preferences.userRoot().node(LoginScherm.class.getName());
            prefs.remove("wachtwoord");
            beheer.sluitPopUpScherm();
        } else {
            errorLabel.setVisible(true);
        }
    }
}
