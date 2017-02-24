package controllers;

import domein.DomeinController;
import domein.JobCoach;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.function.Consumer;
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
    private TableColumn<JobCoach, String> straatCol;

    @FXML
    private TableColumn<JobCoach, String> gemeenteCol;

    @FXML
    private TableColumn<JobCoach, String> emailCol;

    @FXML
    private TableColumn<JobCoach, Integer> postcodeCol;

    @FXML
    private TextField zoekTextField;

    @FXML
    private ChoiceBox<Consumer<String>> zoekChoiceBox;

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
        this.dc = schermbeheer.getDc();
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst());

        voorNaamCol.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        naamCol.setCellValueFactory(new PropertyValueFactory<>("naam"));
        organisatieCol.setCellValueFactory(new PropertyValueFactory<>("organisatie"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        straatCol.setCellValueFactory(new PropertyValueFactory<>("straat"));
        postcodeCol.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        gemeenteCol.setCellValueFactory(new PropertyValueFactory<>("gemeente"));

        gebruikersTableView.setItems(data);
        zoekChoiceBox.getItems().add(createSearchOption(this::zoekVoornaam, "Voornaam"));
        zoekChoiceBox.getItems().add(createSearchOption(this::zoekNaam, "Naam"));
        zoekChoiceBox.getItems().add(createSearchOption(this::zoekOrganisatie, "Organisatie"));
        zoekChoiceBox.getItems().add(createSearchOption(this::zoekPostcode, "Postcode"));
        zoekChoiceBox.getItems().add(createSearchOption(this::zoekEmail, "Voornaam"));
        zoekChoiceBox.getItems().add(createSearchOption(this::zoekGemeente, "Gemeente"));
        zoekChoiceBox.setValue(zoekChoiceBox.getItems().get(0));

    }

    private Consumer<String> createSearchOption(Consumer<String> cons, String name) {
        return new Consumer<String>() {
            @Override
            public void accept(String t) {
                cons.accept(t);
            }

            @Override
            public String toString() {
                return name;
            }
        };
    }

    @FXML
    public void doZoek(ActionEvent event) {
        String query = zoekTextField.getText();
        if (query.isEmpty()) {
            data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst());
            gebruikersTableView.setItems(data);
        } else {
            zoekChoiceBox.getValue().accept(query);
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

    public void zoekOrganisatie(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().zoekOrganisatie(query));
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

    public void zoekGemeente(String query) {
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().zoekGemeente(query));
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
