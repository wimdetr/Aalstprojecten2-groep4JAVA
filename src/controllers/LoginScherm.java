/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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

    public LoginScherm() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LoginScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try{
            loader.load();
        }catch(IOException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    
     
    
}
