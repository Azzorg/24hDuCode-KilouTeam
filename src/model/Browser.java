package model;

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
    final String htmlUrl = "C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/site/index2.html";
    
    public Browser() {
    
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
   
        webEngine.load("file:///"+htmlUrl);
        
        but = new Button();
        but.setText("Aller a un autre endroit");
       

        //add the web view to the scene
        getChildren().add(browser);

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