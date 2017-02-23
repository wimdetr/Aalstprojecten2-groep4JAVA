package controllers;

import domein.DomeinController;
import domein.JobCoach;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author wim
 */
public class GebruikerAccountsBeherenScherm extends BorderPane {

    @FXML
    private TableView<JobCoach> gebruikersTableView;

    @FXML
    private TableColumn<JobCoach, String> voorNaamCol;

    @FXML
    private TableColumn<JobCoach, String> naamCol;
    @FXML
    private TableColumn<JobCoach, String> organisatieCol;
    @FXML
    private TableColumn<JobCoach, String> adresCol;
    @FXML
    private TableColumn<JobCoach, String> emailCol;

    @FXML
    private TableColumn<JobCoach, Integer> postcodeCol;

    @FXML
    private TextField zoekTextField;

    @FXML
    private ChoiceBox<String> zoekChoiceBox;

    private ObservableList<JobCoach> data;
    private final DomeinController dc;
    private SchermBeheer schermBeheer;

    public GebruikerAccountsBeherenScherm(SchermBeheer schermbeheer) {
        this.schermBeheer = schermbeheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GebruikerAccountsBeherenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        HoofdScherm parent = (HoofdScherm) this.getParent();
        this.dc = schermbeheer.getDc();
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst());

        voorNaamCol.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        naamCol.setCellValueFactory(new PropertyValueFactory<>("naam"));
        organisatieCol.setCellValueFactory(new PropertyValueFactory<>("bedrijf"));
        adresCol.setCellValueFactory(new PropertyValueFactory<>("straat"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        postcodeCol.setCellValueFactory(new PropertyValueFactory<>("postcode"));

        gebruikersTableView.setItems(data);
        zoekChoiceBox.setValue("Voornaam");
        zoekChoiceBox.setItems(FXCollections.observableArrayList("Voornaam", "Naam", "Bedrijf", "Straat", "Email", "Postcode"));
    }

    @FXML
    public void doZoek(ActionEvent event) {
        String query = zoekTextField.getText();
        if (query.isEmpty()) {
            data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst());
            gebruikersTableView.setItems(data);
        } else {

            String searchOn = "zoek" + zoekChoiceBox.getValue();
            try {
                Method m = this.getClass().getMethod(searchOn, String.class);
                m.invoke(this, query);
            } catch (Exception e) {

            }
        }
    }

    public void zoekVoornaam(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().zoekVoornaam(query));
        gebruikersTableView.setItems(data);

    }

    public void zoekNaam(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().zoekNaam(query));
        gebruikersTableView.setItems(data);
    }

    public void zoekBedrijf(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().zoekBedrijf(query));
        gebruikersTableView.setItems(data);

    }

    public void zoekEmail(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().zoekEmail(query));
        gebruikersTableView.setItems(data);

    }

    public void zoekPostcode(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().zoekPostCode(query));
        gebruikersTableView.setItems(data);
    }

    @FXML
    public void doDelete(ActionEvent event) {
        JobCoach j = gebruikersTableView.getSelectionModel().getSelectedItem();
        if (j != null) {
            BevestigVerwijderenScherm bvs = new BevestigVerwijderenScherm(schermBeheer, "Bevestig Verwijderen Jobcoach Mark", "Bent u zeker dat u Jobcoach Mark wilt verwijderen?");
            schermBeheer.openPopUpScherm(bvs);
            Stage stage = new Stage();
            Scene scene = new Scene(bvs, 700, 250);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
            if (!schermBeheer.popUpOpen()) {
                data.remove(j);
                // verwijderen in domein!
            }

        }
    }

    @FXML
    public void doExporteer(ActionEvent event) {
    }

}
