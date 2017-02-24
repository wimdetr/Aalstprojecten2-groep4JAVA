/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author ~dreeki~
 */
public class OverzichtScherm extends VBox{   
    @FXML
    private FlowPane flowPaneRecenteAnalyses;
    
    private SchermBeheer schermBeheer;

    public OverzichtScherm(SchermBeheer schermBeheer) {
        this.schermBeheer = schermBeheer;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/OverzichtScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        for(int i = 0; i< 6; i++)
        flowPaneRecenteAnalyses.getChildren().add(new Mark(this.schermBeheer));
    }
    
    
}
