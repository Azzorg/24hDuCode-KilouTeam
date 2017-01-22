package Controler;

import java.net.URL;
import java.util.ResourceBundle;

import application.Client;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import serveur.Card;

public class gameControler implements Initializable {

	public String action = null;

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
	ImageView botte1;
	@FXML
	ImageView botte2;
	@FXML
	ImageView botte3;
	@FXML
	ImageView botte4;

	@FXML
	TextField kilometre;

	Card Carte1 = new Card("512", "MOVED",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Carte512.jpg");
	Card Carte2 = new Card("2048", "MOVED",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Carte2048.jpg");
	Card Carte3 = new Card("Retrecissement_arc", "MALUS",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Retrecissement_arc.jpg");
	Card Carte4 = new Card("Agrandissement_arc", "BONUS",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Agrandissement_arc.jpg");
	Card Carte5 = new Card("Demarrage_arc", "BONUS",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Demarrage_arc.jpg");
	Card Carte6 = new Card("Terrier_Licorne", "BOTTE",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Terrier_Licorne.jpg");

	@FXML
	WebView MapView;

	final String htmlUrl = "C:/Users/rémy/git/24hDuCode-KilouTeam/resources/site/index2.html";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		kilometre.setText("0");
		WebEngine webEngine = MapView.getEngine();
		webEngine.load("file:///" + htmlUrl);


		Card_1.setImage(new Image(Carte1.getImagePath()));
		Card_1.setOnMouseClicked(CardClick);

		Card_2.setImage(new Image(Carte2.getImagePath()));
		Card_2.setOnMouseClicked(CardClick);

		Card_3.setImage(new Image(Carte3.getImagePath()));
		Card_3.setOnMouseClicked(CardClick);

		Card_4.setImage(new Image(Carte4.getImagePath()));
		Card_4.setOnMouseClicked(CardClick);

		Card_5.setImage(new Image(Carte5.getImagePath()));
		Card_5.setOnMouseClicked(CardClick);

		Card_6.setImage(new Image(Carte6.getImagePath()));
		Card_6.setOnMouseClicked(CardClick);
	}

	EventHandler<MouseEvent> CardClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			ImageView b = (ImageView) event.getSource();
			
			Card c;
			// System.out.println(c);
			int n;
			switch (b.getId()) {
			case "Card_1":
				c = Carte1;
				/*n = Integer.parseInt(kilometre.getText()) + 512;
				VerifWin(n);
				kilometre.setText(n + "");
				Client.J1.setActionSuivante("ACTION\nMOVED\n54\n26");*/
				break;
			case "Card_2":
				c = Carte2;

				break;
			case "Card_3":
				c = Carte3;

				break;
			case "Card_4":
				c = Carte4;
				break;
				
			case "Card_5":
				c = Carte5;
				break;
				
			case "Card_6":
				c = Carte6;
				break;
			default:
				c = Carte1;
				break;
					
			}
			
			System.out.println(c.getAction() + " : " + c.getName());
			switch (c.getAction()) {
			case "MOVED":
				n = Integer.parseInt(kilometre.getText()) + Integer.parseInt(c.getName());
				VerifWin(n);
				kilometre.setText(n + "");
				Client.J1.setActionSuivante("ACTION\nMOVED\n54\n26");
				break;
			case "MALUS":
				Client.J1.setActionSuivante("ACTION\nMALUS\n"+c.getName()+"\n2");
				break;
			case "BONUS":
				Client.J1.setActionSuivante("ACTION\nBONUS\n"+c.getName());
				break;
			case "BOTTE":
				botte1.setImage(new Image(c.getImagePath()));
				Client.J1.setActionSuivante("ACTION\nBOTTE\n"+c.getName());
				break;

			default:
				break;
			}
		}
	};

	public void VerifWin(int n) {
		if (n >= 10240) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("OH OH OH GOOD JOB");
			// alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("YOU HAVE WON MOTHER FUCKER !");
			alert.showAndWait().ifPresent(rs -> {
				if (rs == ButtonType.OK) {
					System.out.println("Pressed OK.");
				}
			});
		}

	}

}
