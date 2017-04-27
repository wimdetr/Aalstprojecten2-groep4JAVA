package controllers;

import domein.DomeinController;
import domein.JobCoach;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import persistentie.JobCoachMapper;

/**
 *
 * @author wim
 */
public class GebruikerDetailScherm extends BorderPane {

    @FXML
    private TextField voorNaamVeld;

    @FXML
    private TextField naamVeld;

    @FXML
    private TextField organisatieVeld;

    @FXML
    private TextField straatVeld;

    @FXML
    private TextField huisnrVeld;

    @FXML
    private TextField postcodeVeld;

    @FXML
    private TextField gemeenteVeld;

    @FXML
    private Label errorLabel;

    private Schermbeheer schermBeheer;

    private JobCoach jobcoach;

    public GebruikerDetailScherm(Schermbeheer schermbeheer, JobCoach j) {
        jobcoach = j;
        this.schermBeheer = schermbeheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GebruikerDetailScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        fillInData();
    }

    private void fillInData() {
        voorNaamVeld.setText(jobcoach.getVoornaam());
        naamVeld.setText(jobcoach.getNaam());
        organisatieVeld.setText(jobcoach.getNaamBedrijf());
        straatVeld.setText(jobcoach.getStraatBedrijf());
        huisnrVeld.setText(Integer.toString(jobcoach.getNummerBedrijf()));
        postcodeVeld.setText(Integer.toString(jobcoach.getPostcodeBedrijf()));
        gemeenteVeld.setText(jobcoach.getGemeenteBedrijf());

    }

    @FXML
    private void doCancel(ActionEvent event) {
        schermBeheer.sluitPopUpScherm();
    }

    @FXML
    private void doOpslaan(ActionEvent event) {
        if (isValid()) {
            errorLabel.setVisible(false);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Wijzigen gebruiker");
            alert.setHeaderText("Confirmeer wijzigingen");
            alert.setContentText("Ben je zeker dat je de gegevens wil wijzigen?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                updateUser();
                schermBeheer.sluitPopUpScherm();
                JobCoachMapper m = schermBeheer.getDc().getJobCoachRepo().getJobCoachMapper();
                m.modify(jobcoach);
                
            }
        } else {
            errorLabel.setVisible(true);
        }
    }

    private boolean isValid() {
        // & instead of && forces java to check every statement
        return (checkTextField("[ a-zA-Z'-]+", naamVeld)
                & checkTextField("[ a-zA-Z'-]+", voorNaamVeld)
                & checkTextField(".+", organisatieVeld)
                & checkTextField("[ a-zA-Z'-]+", straatVeld)
                & checkTextField("[0-9]+[a-zA-Z]?", huisnrVeld)
                & checkTextField("^[1-9][0-9]{3}$", postcodeVeld)
                & checkTextField("[ a-zA-Z'-]+", gemeenteVeld));
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

    private void updateUser() {
        jobcoach.setVoornaam(voorNaamVeld.getText());
        jobcoach.setNaam(naamVeld.getText());
        jobcoach.setNaamBedrijf(organisatieVeld.getText());
        jobcoach.setStraatBedrijf(straatVeld.getText());
        jobcoach.setNummerBedrijf(Integer.parseInt(huisnrVeld.getText()));
        jobcoach.setPostcodeBedrijf(Integer.parseInt(postcodeVeld.getText()));
        jobcoach.setGemeenteBedrijf(gemeenteVeld.getText());
    }
}
