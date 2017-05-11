/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Werkgever;
import domein.repository.WerkgeverRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author wimde
 */
public class WerkgeverBeherenScherm extends BorderPane {

    @FXML
    private TableView<Werkgever> werkgeversTableView;

    @FXML
    private TableColumn<Werkgever, Boolean> checkboxCol;

    @FXML
    private TableColumn<Werkgever, String> bedrijfCol;

    @FXML
    private TableColumn<Werkgever, String> afdelingCol;

    @FXML
    private TableColumn<Werkgever, String> contactpersoonCol;

    @FXML
    private TableColumn<Werkgever, String> emailCol;

    @FXML
    private TableColumn<Werkgever, String> gemeenteCol;

    @FXML
    private TableColumn<Werkgever, String> straatCol;

    @FXML
    private TableColumn<Werkgever, String> postcodeCol;

    @FXML
    private TextField zoekTextField;

    @FXML
    private ChoiceBox<Function<String, List<Werkgever>>> zoekChoiceBox;

    @FXML
    private Button exporteerBtn;

    private Schermbeheer beheer;

    private ObservableList<Werkgever> data;

    public WerkgeverBeherenScherm(Schermbeheer beheer) {
        this.beheer = beheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/WerkgeverBeherenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        /*
        cell factories
         */
        checkboxCol.setCellValueFactory(param -> param.getValue().isChecked());
        checkboxCol.setCellFactory(CheckBoxTableCell.forTableColumn(checkboxCol));
        bedrijfCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getNaam()));
//        afdelingCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getNaamAfdeling()));
//        contactpersoonCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getContactPersoonVoornaam()
//                + " " + e.getValue().getContactPersoonNaam()));
//        emailCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getContactPersoonEmail()));
//        gemeenteCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getGemeente()));
//        straatCol.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getStraat() + e.getValue().getNummer()));
//        postcodeCol.setCellValueFactory(e -> new SimpleStringProperty(Integer.toString(e.getValue().getPostcode())));
        data = FXCollections.observableList(beheer.getDc().getBedrijfRepo().getLijst());
        werkgeversTableView.setItems(data);
        BooleanBinding checkBinding = new BooleanBinding() {
            {
                data.stream().map(c -> c.isChecked()).forEach(super::bind);
            }

            @Override
            protected boolean computeValue() {
                return data.stream().noneMatch(c -> c.isChecked().get());
            }
        };
        exporteerBtn.disableProperty().bind(checkBinding);

        /*
        Zoekfunctie implementeren.
         */
        WerkgeverRepository repo = beheer.getDc().getBedrijfRepo();
        zoekChoiceBox.getItems().add(createSearchOption(repo::zoekNaam, "Naam"));
        zoekChoiceBox.getItems().add(createSearchOption(repo::zoekAfdeling, "Afdeling"));
        zoekChoiceBox.getItems().add(createSearchOption(repo::zoekGemeente, "Gemeente"));
        zoekChoiceBox.setValue(zoekChoiceBox.getItems().get(0));

    }

    private Function<String, List<Werkgever>> createSearchOption(Function<String, List<Werkgever>> func, String a) {
        return new Function<String, List<Werkgever>>() {
            @Override
            public List<Werkgever> apply(String t) {
                return func.apply(t);
            }

            @Override
            public String toString() {
                return a;
            }
        };
    }

    @FXML
    void doExporteer(ActionEvent event) {
        ObservableList<Werkgever> werkgevers = data.filtered(p -> p.isChecked().get() == true);

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Exporteer werkgevers");
        dialog.setHeaderText("Bestandsnaam invoeren");
        dialog.setContentText("Voer een passende bestaandsnaam in:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().isEmpty()) {
            String naamBestand = result.get() + ".csv";
            DirectoryChooser dirChooser = new DirectoryChooser();
            File chosenDir = dirChooser.showDialog(this.getScene().getWindow());
            if (chosenDir != null) {
                StringBuilder csvBuilder = new StringBuilder();
                csvBuilder
                        .append("Organisatie;")
                        .append("Afdeling;")
                        .append("Gemeente;")
                        .append("Straat")
                        .append("Nummer;")
                        .append("Postcode;")
                        .append("\n");
                werkgevers.forEach((werkgever) -> {
//                    csvBuilder.append(werkgever.getNaam())
//                            .append(";")
//                            .append(werkgever.getNaamAfdeling())
//                            .append(";")
//                            .append(werkgever.getGemeente())
//                            .append(";")
//                            .append(werkgever.getStraat())
//                            .append(";")
//                            .append(werkgever.getNummer())
//                            .append(werkgever.getBus() == null ? "" : werkgever.getBus())
//                            .append(";")
//                            .append(werkgever.getPostcode())
//                            .append(";")
//                            .append("\n");
                });
                String csvString = csvBuilder.toString();
                String location = chosenDir.toString() + "/" + naamBestand;
                try {
                    Files.write(Paths.get(location), csvString.getBytes());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Exporteren succesvol!");
                    alert.setContentText("Het bestand is succesvol geëxporteerd! \n \n" + "Locatie:" + location);

                    alert.showAndWait();
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Exporteren mislukt!");
                    alert.setContentText("Er is iets foutgelopen, probeer het nog eens.");

                }
            }
        }
    }

    @FXML
    void doSelectAll(ActionEvent event) {
        if (data.stream().allMatch(a -> a.isChecked().get() == true)) {
            data.forEach(a -> a.setChecked(Boolean.FALSE));
        } else {
            data.forEach(a -> a.setChecked(Boolean.TRUE));
        }
    }

    @FXML
    void doZoek(ActionEvent event) {
        String query = zoekTextField.getText();
        if (query.isEmpty()) {
            data = FXCollections.observableList(data);
        } else {
            data = FXCollections.observableList(zoekChoiceBox.getValue().apply(query));
        }
        werkgeversTableView.setItems(data);
    }

}
