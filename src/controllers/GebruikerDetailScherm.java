package controllers;

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

    private JobCoach user;

    public GebruikerDetailScherm(Schermbeheer schermbeheer, JobCoach j) {
        user = j;
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
        voorNaamVeld.setText(user.getVoornaam());
        naamVeld.setText(user.getNaam());
        organisatieVeld.setText(user.getOrganisatie());
        straatVeld.setText(user.getStraat());
        huisnrVeld.setText(Integer.toString(user.getHuisnummer()));
        postcodeVeld.setText(Integer.toString(user.getPostcode()));
        gemeenteVeld.setText(user.getGemeente());

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
                // todo, wegschrijven naar databank
            }
        } else {
            errorLabel.setVisible(true);
        }
    }

    private boolean isValid() {
        boolean flag = true;
        if (Pattern.matches("[ a-zA-Z'-]+", voorNaamVeld.getText())) {
            removeIncorrectStyling(voorNaamVeld);
        } else {
            addIncorrectStyling(voorNaamVeld);
            flag = false;
        }
        if (Pattern.matches("[ a-zA-Z'-]+", naamVeld.getText())) {
            removeIncorrectStyling(naamVeld);
        } else {
            addIncorrectStyling(naamVeld);
            flag = false;
        }
        if (Pattern.matches(".+", organisatieVeld.getText())) {
            removeIncorrectStyling(organisatieVeld);
        } else {
            addIncorrectStyling(organisatieVeld);
            flag = false;
        }
        if (Pattern.matches("[ a-zA-Z'-]+", straatVeld.getText())) {
            removeIncorrectStyling(straatVeld);
        } else {
            addIncorrectStyling(straatVeld);
            flag = false;
        }
        if (Pattern.matches("[0-9]+[a-zA-Z]?", huisnrVeld.getText())) {
            removeIncorrectStyling(huisnrVeld);
        } else {
            addIncorrectStyling(huisnrVeld);
            flag = false;
        }
        if (Pattern.matches("^[1-9][0-9]{3}$", postcodeVeld.getText())) {
            removeIncorrectStyling(postcodeVeld);
        } else {
            addIncorrectStyling(postcodeVeld);
            flag = false;
        }
        if (Pattern.matches("[ a-zA-Z'-]+", gemeenteVeld.getText())) {
            removeIncorrectStyling(gemeenteVeld);
        } else {
            addIncorrectStyling(gemeenteVeld);
            flag = false;
        }
        return flag;
    }

    private void addIncorrectStyling(TextField txf) {
        txf.getStyleClass().add("textfieldIncorrect");
    }

    private void removeIncorrectStyling(TextField txf) {
        txf.getStyleClass().remove("textfieldIncorrect");
    }

    private void updateUser() {
        user.setVoornaam(voorNaamVeld.getText());
        user.setNaam(naamVeld.getText());
        user.setOrganisatie(organisatieVeld.getText());
        user.setStraat(straatVeld.getText());
        user.setHuisnummer(Integer.parseInt(huisnrVeld.getText()));
        user.setPostcode(Integer.parseInt(postcodeVeld.getText()));
        user.setGemeente(gemeenteVeld.getText());
    }
}
