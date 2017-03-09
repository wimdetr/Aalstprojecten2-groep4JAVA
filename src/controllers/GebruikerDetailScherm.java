package controllers;

import domein.JobCoach;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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

    private SchermBeheer schermBeheer;

    private JobCoach user;

    public GebruikerDetailScherm(SchermBeheer schermbeheer, JobCoach j) {
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
            // todo
        }
    }

    private boolean isValid() {
        //todo 
        return true;
    }
}
