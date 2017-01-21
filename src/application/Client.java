package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Client extends Application{

	private Scene scene;
	
	@FXML
	AnchorPane browerEnplacement;
	
	@Override
	public void start(Stage stage) throws IOException {
		// create the scene
		
		
		Parent root = FXMLLoader.load(getClass().getResource("../inter/test.fxml"));
		Scene scene = new Scene(root);
		
		
		System.out.println(browerEnplacement);
		
		
		stage.setTitle("Web View");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
