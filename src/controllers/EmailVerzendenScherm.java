/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.AdminMail;
import domein.Email;
import domein.JobCoach;
import domein.repository.EmailRepository;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import util.MailVerzender;

/**
 *
 * @author wimde
 */
public class EmailVerzendenScherm extends BorderPane {

    @FXML
    private TextField onderwerpVeld;

    @FXML
    private Label errorLabel;

    @FXML
    private TextArea inhoudVeld;

    @FXML
    private CheckBox emailCheckBox;

    private Schermbeheer beheer;

    private List<JobCoach> ontvangers;

    public EmailVerzendenScherm(Schermbeheer beheer, AdminMail mail) {
        this(beheer, Arrays.asList(mail.getAfzender()));
        onderwerpVeld.setText("Re:"+mail.getOnderwerp());
        inhoudVeld.setText(mail.getInhoud());
    }

    public EmailVerzendenScherm(Schermbeheer beheer, List<JobCoach> coaches) {
        this.beheer = beheer;
        ontvangers = coaches;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EmailVerzendenScherm.fxml"));
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
    void doVerzend(ActionEvent event) {
        String onderwerp = onderwerpVeld.getText();
        String inhoud = inhoudVeld.getText();
        EmailRepository repo = beheer.getDc().getEmailRepo();
        Email e = new Email(beheer.getDc().getAdmin(), onderwerp, inhoud);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                repo.sendMail(e, ontvangers);
                if (emailCheckBox.isSelected()) {
                    ontvangers.forEach(o -> MailVerzender.sendMail(o.getEmail(), onderwerp, inhoud));
                }
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Email(s) verzonden");
        alert.setHeaderText("Email(s) verzonden");
        alert.setContentText("De email(s) zijn succesvol verzonden!");
        alert.showAndWait();
        beheer.sluitPopUpScherm();
    }

}
