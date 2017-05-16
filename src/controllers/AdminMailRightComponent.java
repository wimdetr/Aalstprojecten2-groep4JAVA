/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.AdminMail;
import domein.JobCoach;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author wimde
 */
public class AdminMailRightComponent extends VBox {

    @FXML
    private Label headerVeld;

    @FXML
    private Text inhoudVeld;

    private AdminMail mail;

    private AdminMailLeftComponent left;

    private Schermbeheer beheer;

    public AdminMailRightComponent(Schermbeheer beheer, AdminMailLeftComponent left, AdminMail o) {
        this.beheer = beheer;
        mail = o;
        this.left = left;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AdminMailRightComponent.fxml"));
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
        headerVeld.setText(mail.getAfzender().getVoornaam() + " " + mail.getAfzender().getNaam() + " - " + mail.getOnderwerp());
        inhoudVeld.setText(mail.getInhoud());
    }

    @FXML
    void doBeantwoord(ActionEvent event) {
        beheer.plaatsPopUpScherm(new EmailVerzendenScherm(beheer,mail), "Mail Verzenden");
    }

    @FXML
    void doVerwijder(ActionEvent event) {
        VBox leftbox = (VBox) left.getParent(); //emailBox
        leftbox.getChildren().remove(left);
        this.getChildren().removeAll(this.getChildren());
        beheer.getDc().getAdminRepo().deleteMail(mail);
        beheer.getDc().getAdmin().getMails().remove(mail);
    }
}
