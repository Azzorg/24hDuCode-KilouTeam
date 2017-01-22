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
import javafx.stage.Stage;

public class InscriptionController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
