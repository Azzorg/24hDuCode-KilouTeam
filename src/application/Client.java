package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{
	
	@Override
	public void start(Stage stage) throws IOException {
		// create the scene

		Parent root = FXMLLoader.load(getClass().getResource("../View/Game.fxml"));
		Scene scene = new Scene(root);

		stage.setTitle("Web View");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
