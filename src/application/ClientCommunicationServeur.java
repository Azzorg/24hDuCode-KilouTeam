package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientCommunicationServeur {
	private int PORT = 1500;

	public static void main(String args[]) {
		//try {
			//Nombre de joueur
			int nbJoueur = 2;
			
			boolean fin = false;
			
			//Création du client 1
			JoueurClient J1 = new JoueurClient("Joueur 2", 2, 1500);
			J1.start();
		
			//Thread.sleep(500);
			/*
			//Création du client 2
			JoueurClient J2 = new JoueurClient("Joueur 2", 2, 1500);
			J2.start();
			
			System.out.println("FIN CLIENT");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
