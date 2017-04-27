/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;



import domein.Analyse;
import domein.Werkgever;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author niels
 */
public class ArchiefScherm extends BorderPane {

    @FXML
    private TableView<Analyse> archiefTableView;

    @FXML
    private TableColumn<Analyse, Number> idColumn;

    @FXML
    private TableColumn<Analyse, String> voornaamColumn;

    @FXML
    private TableColumn<Analyse, String> naamColumn;

    @FXML
    private TableColumn<Analyse, String> bedrijfColumn;

    @FXML
    private TableColumn<Analyse, String> departementColumn;

    @FXML
    private TableColumn<Analyse, Number> resultaatColumn;

    private ObservableList<Analyse> data;

    private Schermbeheer beheer;

    public ArchiefScherm(Schermbeheer beheer) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ArchiefScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        this.beheer = beheer;
        data = FXCollections.observableArrayList(beheer.getDc().getAnalyseRepo().getLijst());

        // -> table moet van Jobcoach zijn niet van analyse!
        // hoe probleem met lijst dep. oplossen??
        idColumn.setCellValueFactory(celldata -> new SimpleIntegerProperty(celldata.getValue().getId()));
        voornaamColumn.setCellValueFactory(celldata -> new SimpleStringProperty("Wim"));
        naamColumn.setCellValueFactory(celldata -> new SimpleStringProperty("De Troyer"));
        bedrijfColumn.setCellValueFactory(celldata -> new SimpleStringProperty(celldata.getValue().getWerkgever().getNaam()));
        departementColumn.setCellValueFactory(celldata -> new SimpleStringProperty("Dienst IT"));
        Random rnd = new Random();
        resultaatColumn.setCellValueFactory(celldata -> new SimpleDoubleProperty(round(rnd.nextDouble() * 10000, 2)));
        archiefTableView.setItems(data);
    }

    private double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @FXML
    void doExport(ActionEvent event) {
        Set<Werkgever> werkgevers = new HashSet<>();
        for (Analyse ana : beheer.getDc().getAnalyseRepo().getLijst()) {
            werkgevers.add(ana.getWerkgever());
        }

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
                        .append("Gemeente;")
                        .append("Postcode;")
                        .append("Huisnr;")
                        .append("\n");
                werkgevers.forEach((werkgever) -> {
                    csvBuilder.append(werkgever.getNaam())
                            .append(";")
                            .append(werkgever.getGemeente())
                            .append(";")
                            .append(werkgever.getPostcode())
                            .append(";")
                            .append(werkgever.getNummer())
                            .append(";")
                            .append("\n");
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
}
