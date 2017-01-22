package Controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class gameControler implements Initializable{

	@FXML
	ImageView Card_1;
	@FXML
	ImageView Card_2;
	@FXML
	ImageView Card_3;
	@FXML
	ImageView Card_4;
	@FXML
	ImageView Card_5;
	@FXML
	ImageView Card_6;

	@FXML
	WebView MapView;
	final String htmlUrl = "C:/Users/Alice/git/24hDuCode-KilouTeam/resources/site/index2.html";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/*WebEngine webEngine = MapView.getEngine();
		webEngine.load("file:///"+htmlUrl);
		System.out.println(htmlUrl);
		System.err.println(Card_1);*/
	}

	
}
