package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author wim
 */
public class AdminToevoegenScherm extends BorderPane {

    @FXML
    private TextField voorNaamVeld;

    @FXML
    private TextField naamVeld;

    @FXML
    private TextField emailVeld;

    @FXML
    private Label errorLabel;

    private Schermbeheer beheer;

    public AdminToevoegenScherm(Schermbeheer beheer) {
        this.beheer = beheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AdminToevoegenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @FXML
    void doCancel(ActionEvent event) {
        beheer.sluitPopUpScherm();
    }

    @FXML
    void doToevoegen(ActionEvent event) {
        /*
        TODO -> email versturen naar admin met wachtwoord
         */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmatie");
        alert.setHeaderText(null);
        alert.setContentText("Er is een mail verstuurd naar "+ emailVeld.getText() + " met de logingegevens.");
        alert.showAndWait();
        doCancel(event);
    }

}
