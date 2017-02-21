package controllers;

import domein.DomeinController;
import domein.JobCoach;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    private final ObservableList<JobCoach> data;
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

        voorNaamCol.setCellValueFactory(new PropertyValueFactory<JobCoach, String>("voornaam"));
        naamCol.setCellValueFactory(new PropertyValueFactory<JobCoach, String>("naam"));
        organisatieCol.setCellValueFactory(new PropertyValueFactory<JobCoach, String>("bedrijf"));
        adresCol.setCellValueFactory(new PropertyValueFactory<JobCoach, String>("straat"));
        emailCol.setCellValueFactory(new PropertyValueFactory<JobCoach, String>("email"));
        postcodeCol.setCellValueFactory(new PropertyValueFactory<JobCoach, Integer>("postcode"));

        gebruikersTableView.setItems(data);
    }

}
