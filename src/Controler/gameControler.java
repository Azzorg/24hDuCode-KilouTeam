package Controler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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
	final String htmlUrl = "C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/site/indexClient.html";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WebEngine webEngine = MapView.getEngine();
		webEngine.load("file:///"+htmlUrl);
		System.out.println(htmlUrl);
		System.err.println(Card_1);
	}

	
}
