package controllers;

import domein.DomeinController;
import domein.JobCoach;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

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

    public GebruikerAccountsBeherenScherm(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GebruikerAccountsBeherenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        HoofdScherm parent = (HoofdScherm) this.getParent();
        this.dc = dc;
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
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst().stream()
                .filter(p -> p.getVoornaam().contains(query))
                .collect(Collectors.toList()));
        gebruikersTableView.setItems(data);

    }

    public void zoekNaam(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst().stream()
                .filter(p -> p.getNaam().contains(query))
                .collect(Collectors.toList()));
        gebruikersTableView.setItems(data);

    }

    public void zoekBedrijf(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst().stream()
                .filter(p -> p.getBedrijf().contains(query))
                .collect(Collectors.toList()));
        gebruikersTableView.setItems(data);

    }

    public void zoekEmail(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst().stream()
                .filter(p -> p.getEmail().contains(query))
                .collect(Collectors.toList()));
        gebruikersTableView.setItems(data);

    }

    public void zoekPostcode(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst().stream()
                .filter(p -> Integer.toString(p.getPostcode()).equals(query))
                .collect(Collectors.toList()));
        gebruikersTableView.setItems(data);
    }
}
