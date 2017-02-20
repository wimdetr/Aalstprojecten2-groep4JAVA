/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.DomeinController;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ~dreeki~
 */
public class LoginScherm extends GridPane {

    @FXML
    private TextField tfGebruikersnaam;

    @FXML
    private TextField tfWachtwoord;

    @FXML
    private Hyperlink hlWachtwoordVergeten;

    @FXML
    private Button btnInloggen;

    @FXML
    private CheckBox checkBoxOnthoudWachtwoord;
    
    @FXML
    private Label lblErrorBericht;
    
    private DomeinController dc;

    public LoginScherm(DomeinController dc) {
        this.dc = dc;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        lblErrorBericht.setVisible(false);
    }
    
    @FXML
    private void keyPressedCheckBox(KeyEvent event){
        if(event.getCode().equals(KeyCode.TAB)){
            veranderFocus(hlWachtwoordVergeten);
        }
    }
    
    @FXML
    private void keyPressedGebruikersnaamVeld(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            veranderFocus(tfWachtwoord);
        }
        if(event.getCode().equals(KeyCode.TAB)){
            veranderFocus(tfWachtwoord);
        }
    }

    @FXML
    private void keyPressedLoginKnop(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER){
            logIn();
        }
        if(event.getCode().equals(KeyCode.TAB)){
            veranderFocus(tfGebruikersnaam);
        }
    }
    
    @FXML
    private void keyPressedWachtwoordVergeten(KeyEvent event){
        if(event.getCode() == KeyCode.TAB){
            veranderFocus(btnInloggen);
        }
    }
    
    @FXML
    private void keyPressedWachtwoordVeld(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            logIn();
        }
        if(event.getCode().equals(KeyCode.TAB)){
            veranderFocus(checkBoxOnthoudWachtwoord);
        } 
    }
    
    @FXML
    private void klikKnop(ActionEvent event) {
        logIn();
    }
    
    private void veranderFocus(Node node){
        Platform.runLater(() -> node.requestFocus());
    }
    
    private void logIn(){
        if(dc.controleerOfAdminKanInloggen(tfGebruikersnaam.getText().trim(), tfWachtwoord.getText().trim())){
            dc.logAdminIn(tfGebruikersnaam.getText().trim());
            
            Stage st = (Stage) this.getScene().getWindow();
            HoofdScherm hoofd = new HoofdScherm();
            Scene sc = new Scene(hoofd);
            st.setScene(sc);
            st.setResizable(true);
        }else{
            lblErrorBericht.setVisible(true);
        }
    }

}
