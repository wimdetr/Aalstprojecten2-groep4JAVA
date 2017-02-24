/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.DomeinController;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ~dreeki~
 */
public class SchermBeheer extends VBox{
    private DomeinController dc;
    private Stage myStage;
    private IsPopUpScherm popUpScherm;
    private boolean heeftPopUpGeopend;

    public SchermBeheer(DomeinController dc, Stage stage) {
        this.dc = dc;
        myStage = stage;
        heeftPopUpGeopend = false;
        
        myStage.setOnCloseRequest(e -> {
            if(heeftPopUpGeopend){
                popUpScherm.sluit();
                heeftPopUpGeopend = false;
                this.setDisable(false);
            }
        });
    }
    
    public void zetStageResizable(boolean resizable){
        myStage.setResizable(true);
    }
    
    public void plaatsScherm(Node node, String schermId, String cssLocatie, String paginaTitel, int width, int height){
        getChildren().setAll(node);
        //node.setId(schermId);
        //myStage.getScene().getStylesheets().setAll(cssLocatie);
        VBox.setVgrow(node, Priority.ALWAYS);
        myStage.setWidth(width);
        myStage.setHeight(height);
        myStage.setTitle(paginaTitel);
        
        myStage.centerOnScreen();
    }
    
    public void openPopUpScherm(IsPopUpScherm scherm){
        popUpScherm = scherm;
        heeftPopUpGeopend = true;
        this.setDisable(true);
        
    }
    
    public void sluitPopUpScherm(){
        if(heeftPopUpGeopend){
            popUpScherm.sluit();
            heeftPopUpGeopend = false;
            this.setDisable(false);
        }
    }

    public DomeinController getDc() {
        return dc;
    }
    
}
