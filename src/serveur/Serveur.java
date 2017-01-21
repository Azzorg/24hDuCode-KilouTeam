package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
	private static int PORT = 1500;
	
	public static void main(String[] args) {
		try {
			ServerSocket welcomeSocket = new ServerSocket(PORT);
			List<Socket> listClientSocket = new ArrayList<Socket>();
			List<BufferedReader> listClientBufferedReader = new ArrayList<BufferedReader>();
			int nbJoueur;
			
			while(true){
				//Accepte le premier client et l'ajoute à la liste des clients
				listClientSocket.add(welcomeSocket.accept());
				
				//Création du BufferedReader du client1
				listClientBufferedReader.add(new BufferedReader(new InputStreamReader(listClientSocket.get(0).getInputStream())));
				
				//Récupération du nombre de joueur qu'il aura dans la partie
				nbJoueur = Integer.parseInt(listClientBufferedReader.get(0).readLine());
				
				//Attente de tous les joueurs et création de leur socket
				for(int i = 0; i<nbJoueur-1; i++){
					System.out.println("new player added to the server");
					listClientSocket.add(welcomeSocket.accept());
				}
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
