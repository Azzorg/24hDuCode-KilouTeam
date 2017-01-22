/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author slokhas
 */
class Browser extends Region {
 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    final Button but;
    private PositionModifier posMod = new PositionModifier();
    final String htmlUrl = "D:/Etudes/24Hducode/Ressources/test_ajax/index2.html";
    float x1,x2,y1,y2;
   
    public Browser() {
    
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
   
        webEngine.load("file:///"+htmlUrl);
        
        but = new Button();
        but.setText("Aller a un autre endroit");
        
        but.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                	x1 = (float)Math.random()*100;
                	y1 = (float)Math.random()*100;
                	x2 = x1 + 2;
                	y2 = y1 + 2;
                    //posMod.ChangePosition(htmlUrl, 48, 2);
                    posMod.DrawLine(htmlUrl);
                    webEngine.load("file:///"+htmlUrl);
                } catch (IOException ex) {
                    Logger.getLogger(Browser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //add the web view to the scene
        getChildren().add(browser);
        getChildren().add(but);

    }
    

    
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
        layoutInArea(but,0,0,w,h,0,HPos.CENTER,VPos.BOTTOM);
        
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
    }
}