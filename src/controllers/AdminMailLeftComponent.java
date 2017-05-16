package controllers;

import domein.AdminMail;
import domein.repository.AdminRepository;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Calendar;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author wimde
 */
public class AdminMailLeftComponent extends VBox {

    @FXML
    private Label afzenderLabel;

    @FXML
    private Label datumLabel;

    @FXML
    private Label onderwerpLabel;

    @FXML
    private Label inhoudLabel;

    @FXML
    private HBox headerBox;

    private AdminMail mail;

    private ObjectProperty<Node> emailView;

    private Schermbeheer beheer;

    public AdminMailLeftComponent(AdminMail o, ObjectProperty<Node> emailView, Schermbeheer beheer) {
        this.beheer = beheer;
        this.emailView = emailView;
        mail = o;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AdminMailLeftComponent.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        fillData();
    }

    private void fillData() {
        afzenderLabel.setText(mail.getAfzender().getVoornaam() + " " + mail.getAfzender().getNaam());
        if (mail.getIsGelezen()) {
            headerBox.getStyleClass().add("topBarRead");
        } else {
            headerBox.getStyleClass().add("topBarUnRead");

        }
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Date verzendDate = mail.getVerzendDatum();
        String dateString;

        Date date = new Date();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date);
        cal2.setTime(verzendDate);
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        if (sameDay) {
            dateString = hourFormat.format(verzendDate);
        } else {
            dateString = dateFormat.format(verzendDate);

        }

        datumLabel.setText(dateString);
        onderwerpLabel.setText(mail.getOnderwerp());
        inhoudLabel.setText(mail.getInhoud());
    }

    @FXML
    void openMail(MouseEvent event) {
        if (!mail.getIsGelezen()) {
            headerBox.getStyleClass().remove("topBarUnRead");
            headerBox.getStyleClass().add("topBarRead");
            AdminRepository repo = beheer.getDc().getAdminRepo();
            mail.setIsGelezen(true);
            repo.modifyMail(mail);
        }
        emailView.set(new AdminMailRightComponent(beheer, this, mail));
    }
}
