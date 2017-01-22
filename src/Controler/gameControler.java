package Controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import serveur.Card;

import javafx.stage.Stage;


public class gameControler implements Initializable {

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

	Card Carte1 = new Card("512", "MOVED", "file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Carte512.jpg");
	Card Carte2 = new Card("2048", "MOVED",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Carte2048.jpg");
	Card Carte3 = new Card("Retrecissement_arc", "MALUS",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Retrecissement_arc.jpg");
	Card Carte4 = new Card("Agrandissement_arc", "BONUS",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Agrandissement_arc.jpg");
	Card Carte5 = new Card("Nourriture_licorne", "BONUS",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Nourriture_licorne.jpg");
	Card Carte6 = new Card("Licorne_rafistolee", "MALUS",
			"file:/C:/Users/Rémy/git/24hDuCode-KilouTeam/resources/Interface/Licorne_rafistolee.jpg");

	@FXML
	WebView MapView;

	final String htmlUrl = "C:/Users/Alice/git/24hDuCode-KilouTeam/resources/site/index2.html";
	
	/**
	 * Called when the user clicks on the connection button "Jouer".
	 */
	@FXML
	private void gameConnection (ActionEvent event) {
				
		System.out.println("cool je fonctionne !");
		 try {
             Stage stage;
             Button b = (Button) event.getSource();
             stage = (Stage) b.getScene().getWindow();
             switchToView("../View/New_Game.fxml", stage);
         } catch (IOException ex) {
             ex.getMessage();
         }
		
	}
	

	/**
	 * Called when the user clicks on the button "Go !".
	 */
	@FXML
	private void newGame (ActionEvent event) {
		
		 try {
             Stage stage;
             Button b = (Button) event.getSource();
             stage = (Stage) b.getScene().getWindow();
             switchToView("../View/Game.fxml", stage);
         } catch (IOException ex) {
             ex.getMessage();
         }
		
	}
	
	/**
	 * Called when the user clicks on the button "Première connexion ?".
	 */
	@FXML
	private void inscription (ActionEvent event) {
		
		 try {
             Stage stage;
             Hyperlink h = (Hyperlink) event.getSource();
             stage = (Stage) h.getScene().getWindow();
             switchToView("../View/Inscription.fxml", stage);
         } catch (IOException ex) {
             ex.getMessage();
         }
		
	}
	
	/**
	 * Called when the user clicks on the button "Se connecter et jouer".
	 */
	@FXML
	private void playAfterInscription (ActionEvent event) {
		
		 try {
             Stage stage;
             Button b = (Button) event.getSource();
             stage = (Stage) b.getScene().getWindow();
             switchToView("../View/Game.fxml", stage);
         } catch (IOException ex) {
             ex.getMessage();
         }
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

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
			ImageView c = (ImageView) event.getSource();
			//System.out.println(c);
			switch (c.getId()) {
			case "Card_1":
				System.out.println(Carte1.getAction() + " : " + Carte1.getName());
				Client.J1.setActionSuivante("ACTION\nMOVED\n54\n26");
				break;
			case "Card_2":
				System.out.println(Carte2.getAction() + " : " + Carte2.getName());
				Client.J1.setActionSuivante("ACTION\nMOVED\n54\n26");
				break;
			case "Card_3":
				System.out.println(Carte3.getAction() + " : " + Carte3.getName());
				Client.J1.setActionSuivante("ACTION\nMOVED\n54\n26");
				break;
			case "Card_4":
				System.out.println(Carte4.getAction() + " : " + Carte4.getName());
				Client.J1.setActionSuivante("ACTION\nMOVED\n54\n26");
				break;
			case "Card_5":
				System.out.println(Carte5.getAction() + " : " + Carte5.getName());
				Client.J1.setActionSuivante("ACTION\nMOVED\n54\n26");
				break;
			case "Card_6":
				System.out.println(Carte6.getAction() + " : " + Carte6.getName());
				Client.J1.setActionSuivante("ACTION\nMOVED\n54\n26");
				break;
			}
		}
	};
    /**
     * Permet de changer de vue
     *
     * @param view La nouvelle vue voulu
     * @param stage La fenetre pour la quelle la vue est à changer
     * @throws IOException
     */
    public void switchToView(String view, Stage stage) throws IOException {
        Parent newView;
        double h = stage.getHeight();
        double w = stage.getWidth();

        newView = FXMLLoader.load(getClass().getResource(view));
        Scene scene = new Scene(newView);

        stage.setScene(scene);
        stage.setHeight(h);
        stage.setWidth(w);
    }
	
}
