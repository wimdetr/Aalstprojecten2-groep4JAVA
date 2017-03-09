package controllers;

import domein.DomeinController;
import domein.JobCoach;
import domein.repository.JobCoachRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author wim
 */
public class GebruikersBeherenScherm extends BorderPane {

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
    private ChoiceBox<Function<String, List<JobCoach>>> zoekChoiceBox;

    @FXML
    private Button verwijderBtn;

    @FXML
    private Button exporteerBtn;

    private ObservableList<JobCoach> data;
    private final DomeinController dc;
    private SchermBeheer schermBeheer;

    public GebruikersBeherenScherm(SchermBeheer schermbeheer) {
        this.schermBeheer = schermbeheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GebruikersBeherenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        this.dc = schermbeheer.getDc();
        data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst());

        gebruikersTableView.setPlaceholder(new Label("Geen gebruikers gevonden."));

        voorNaamCol.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        naamCol.setCellValueFactory(new PropertyValueFactory<>("naam"));
        organisatieCol.setCellValueFactory(new PropertyValueFactory<>("organisatie"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        straatCol.setCellValueFactory(cellData -> Bindings.concat(cellData.getValue().getStraat(),
                " ", cellData.getValue().getHuisnummer())); // 2 properties bijeen binden voor UX
        postcodeCol.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        gemeenteCol.setCellValueFactory(new PropertyValueFactory<>("gemeente"));

        gebruikersTableView.setRowFactory((p) -> {
            TableRow<JobCoach> rij = new TableRow<>();
            rij.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !rij.isEmpty()) {
                    JobCoach j = rij.getItem();
                    schermBeheer.plaatsPopUpScherm(new GebruikerDetailScherm(schermbeheer, j), "Gebruiker ");

                }
            });
            return rij;
        });

        gebruikersTableView.setItems(data);
        JobCoachRepository jcr = new JobCoachRepository();
        zoekChoiceBox.getItems().add(createSearchOption(jcr::zoekVoornaam, "Voornaam"));
        zoekChoiceBox.getItems().add(createSearchOption(jcr::zoekNaam, "Naam"));
        zoekChoiceBox.getItems().add(createSearchOption(jcr::zoekOrganisatie, "Organisatie"));
        zoekChoiceBox.getItems().add(createSearchOption(jcr::zoekStraat, "Straat"));
        zoekChoiceBox.getItems().add(createSearchOption(jcr::zoekPostCode, "Postcode"));
        zoekChoiceBox.getItems().add(createSearchOption(jcr::zoekEmail, "Email"));
        zoekChoiceBox.getItems().add(createSearchOption(jcr::zoekGemeente, "Gemeente"));
        zoekChoiceBox.setValue(zoekChoiceBox.getItems().get(0));

        gebruikersTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        exporteerBtn.disableProperty().bind(Bindings.isEmpty(gebruikersTableView.getSelectionModel().getSelectedItems()));
        verwijderBtn.disableProperty().bind(Bindings.isEmpty(gebruikersTableView.getSelectionModel().getSelectedItems()));
    }

    private Function<String, List<JobCoach>> createSearchOption(Function<String, List<JobCoach>> func, String a) {
        return new Function<String, List<JobCoach>>() {
            @Override
            public List<JobCoach> apply(String t) {
                return func.apply(t);
            }

            @Override
            public String toString() {
                return a;
            }
        };
    }

    @FXML
    private void doZoek(ActionEvent event) {
        String query = zoekTextField.getText();
        if (query.isEmpty()) {
            data = FXCollections.observableArrayList(dc.getJobCoachRepo().getLijst());
        } else {
            data = FXCollections.observableArrayList(zoekChoiceBox.getValue().apply(query));
        }
        gebruikersTableView.setItems(data);
    }

    @FXML
    private void doDelete(ActionEvent event) {
        ObservableList<JobCoach> coaches = gebruikersTableView.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Verwijderconfirmatie");
        alert.setHeaderText("Verwijderen gebruiker(s)");
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (JobCoach j : coaches) {
            sb.append(i)
                    .append(". ")
                    .append(j.getVoornaam())
                    .append(" ")
                    .append(j.getNaam())
                    .append("\n");
            i++;
        }

        alert.setContentText("Wilt u de volgende gebruikers echt verwijderen? \n \n" + sb.toString());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            data.removeAll(coaches);
        }
    }

    @FXML
    private void doExporteer(ActionEvent event) {
        ObservableList<JobCoach> coaches = gebruikersTableView.getSelectionModel().getSelectedItems();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Exporteer gebruiker(s)");
        dialog.setHeaderText("Bestandsnaam invoeren");
        dialog.setContentText("Voer een passende bestaandsnaam in:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().isEmpty()) {
            String naamBestand = result.get() + ".csv";
            DirectoryChooser dirChooser = new DirectoryChooser();
            File chosenDir = dirChooser.showDialog(this.getScene().getWindow());
            if (chosenDir != null) {
                StringBuilder csvBuilder = new StringBuilder();
                csvBuilder.append("Voornaam;")
                        .append("Naam;")
                        .append("Organisatie;")
                        .append("Straat;")
                        .append("Postcode;")
                        .append("Gemeente;")
                        .append("Email")
                        .append("\n");
                coaches.forEach((coach) -> {
                    csvBuilder.append(coach.getVoornaam())
                            .append(";")
                            .append(coach.getNaam())
                            .append(";")
                            .append(coach.getOrganisatie())
                            .append(";")
                            .append(coach.getGemeente())
                            .append(";")
                            .append(coach.getPostcode())
                            .append(";")
                            .append(coach.getGemeente())
                            .append(";")
                            .append(coach.getEmail())
                            .append("\n");
                });
                String csvString = csvBuilder.toString();
                String location = chosenDir.toString() + "/" + naamBestand;
                try {
                    Files.write(Paths.get(location), csvString.getBytes());
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Exporteren succesvol!");
                    alert.setContentText("Het bestand is succesvol geëxporteerd! \n \n" + "Locatie:" + location);

                    alert.showAndWait();
                } catch (IOException ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Exporteren mislukt!");
                    alert.setContentText("Er is iets foutgelopen, probeer het nog eens.");

                }
            }
        }
    }

    @FXML
    private void doSelectAll(ActionEvent event) {
        gebruikersTableView.getSelectionModel().selectAll();
    }

}
