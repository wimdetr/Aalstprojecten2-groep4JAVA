/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author niels
 */
public class WachtwoordVergetenScherm  extends AnchorPane{
    @FXML
    private TextField txtEmail;

    @FXML
    private Button btnVerstuur;

    @FXML
    private Button btnAnnuleer;

    public WachtwoordVergetenScherm(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/WachtwoordVergetenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    @FXML
    void enterVerstuurEmail(KeyEvent event) {

    }

    @FXML
    void klikVerstuurEmail(ActionEvent event) {

    }

    @FXML
    void enterAnnuleer(KeyEvent event) {

    }
    
    @FXML
    void klikAnnuleer(ActionEvent event) {
    }
    
    private void sluitScherm(Event event){

    }
    
        
    private void verstuur(boolean gaDoor) {
        
    }
}
