package Controler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class testControler implements Initializable{

	@FXML
	WebView MapView;
	final String htmlUrl = "C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/site/index2.html";
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WebEngine webEngine = MapView.getEngine();
		webEngine.load("file:///"+htmlUrl);
		System.out.println(htmlUrl);
	}
}
