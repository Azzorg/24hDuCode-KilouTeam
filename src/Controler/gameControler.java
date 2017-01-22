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
import javafx.scene.control.Hyperlink;
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
		/*WebEngine webEngine = MapView.getEngine();
		webEngine.load("file:///"+htmlUrl);
		System.out.println(htmlUrl);
		System.err.println(Card_1);*/
	}

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
