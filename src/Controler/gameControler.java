package Controler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import serveur.Card;

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
	final String htmlUrl = "C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/site/index2.html";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WebEngine webEngine = MapView.getEngine();
		webEngine.load("file:///"+htmlUrl);

		Card_1.setImage(new Image("file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Carte512.jpg"));
		Card_2.setImage(new Image("file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Carte512.jpg"));
		Card_3.setImage(new Image("file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Carte512.jpg"));
		Card_4.setImage(new Image("file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Carte512.jpg"));
		Card_5.setImage(new Image("file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Carte512.jpg"));
		Card_6.setImage(new Image("file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Carte512.jpg"));
	}

	
}
