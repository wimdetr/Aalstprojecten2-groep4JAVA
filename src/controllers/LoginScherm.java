package controllers;

import domein.DomeinController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ~dreeki~
 */
public class LoginScherm extends BorderPane {

    @FXML
    private TextField tfGebruikersnaam;

    @FXML
    private PasswordField pfWachtwoord;

    @FXML
    private Label lblErrorBericht;

    @FXML
    private CheckBox checkbox;

    private Schermbeheer beheer;

    private Preferences prefs;
    public LoginScherm(Schermbeheer beheer) {
        prefs = Preferences.userRoot().node(this.getClass().getName());
        this.beheer = beheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        loadSavedData();
    }

    private void loadSavedData() {
        tfGebruikersnaam.setText(prefs.get("email", ""));
        pfWachtwoord.setText(prefs.get("wachtwoord", ""));
        if(!tfGebruikersnaam.getText().isEmpty()){
            checkbox.setSelected(true);
        }
    }

    private void saveData(String email, String wachtwoord) {
        prefs.put("email", email);
        prefs.put("wachtwoord", wachtwoord);
    }

    @FXML
    void logIn(ActionEvent event) {
        if (beheer.getDc().controleerOfAdminKanInloggen(tfGebruikersnaam.getText().trim(), pfWachtwoord.getText().trim())) {
            beheer.getDc().logAdminIn(tfGebruikersnaam.getText().trim());
            HoofdScherm hoofd = new HoofdScherm(beheer);
            beheer.plaatsScherm(hoofd, "Kairos - Administratie");
            beheer.registerHoofdScherm(hoofd);
            beheer.setMainStageResizable(true);
            if (checkbox.isSelected()) {
                saveData(tfGebruikersnaam.getText(), pfWachtwoord.getText());
            } else {
                saveData("", "");
            }
        } else {
            lblErrorBericht.setVisible(true);
        }
    }
}
