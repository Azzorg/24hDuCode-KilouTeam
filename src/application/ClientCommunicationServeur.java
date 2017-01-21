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
		try {
			//Nombre de joueur
			int nbJoueur = 2;
			
			//Création de deux clients
			ClientCommunicationServeur client1 = new ClientCommunicationServeur();
			ClientCommunicationServeur client2 = new ClientCommunicationServeur();

			//Le premier client tente de se connecter au serveur
			Socket socketClient1 = new Socket("localhost", client1.PORT);
			
			System.out.println("Connection client 1 : OK");
			
			//Création de in et out pour le client 1
			BufferedReader client1In = new BufferedReader(new InputStreamReader(socketClient1.getInputStream()));
			PrintStream client1Out = new PrintStream(socketClient1.getOutputStream());
			
			//Attente que le serveur soit OK
			while(!client1In.readLine().equals("OK")){
				System.out.println("Serveur pas OK");
			}
			
			//Envoi au serveur du nombre de 
			client1Out.println(nbJoueur);
			
			System.out.println("Envoi du nombre de joueur");
			
			//Attente que le serveur soit OK
			while(!client1In.readLine().equals("OK")){
				System.out.println("Serveur pas OK");
			}
			
			System.out.println("Client 1 OK");
			
			//Le deuxième client se connecte au serveur
			Socket socketClient2 = new Socket("localhost", client2.PORT);
			
			System.out.println("Connection client 2 : OK");
			
			//Création de in et out pour le client 1
			BufferedReader client2In = new BufferedReader(new InputStreamReader(socketClient2.getInputStream()));
			PrintStream client2Out = new PrintStream(socketClient2.getOutputStream());

			//Attente que le serveur soit OK
			while(!client2In.readLine().equals("OK")){
				System.out.println("Serveur pas OK");
			}
			
			System.out.println("Client 2 OK");
			
			//Client 1 attend le début de la partie
			while(!client1In.readLine().equals("DEBUT")){
				System.out.println("Client 1 : Attente du début de la partie");
			}
			client1Out.println("OK");
			
			System.out.println("Client 1  début ok");
			
			//Client 2 attend le début de la partie
			while(!client2In.readLine().equals("DEBUT")){
				System.out.println("Client 2 : Attente du début de la partie");
			}
			client2Out.println("OK");

			System.out.println("Client 2 début ok");
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
