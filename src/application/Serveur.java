package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
	private static int PORT = 1500;
	
	public static void main(String[] args) {
		try {
			System.out.println("d�but");
			ServerSocket welcomeSocket = new ServerSocket(PORT);
			//Liste des sockets des clients
			List<Socket> listClientSocket = new ArrayList<Socket>();
			//Liste des BufferedReader des clients -> in
			List<BufferedReader> listClientBufferedReader = new ArrayList<BufferedReader>();
			//Liste des PrintStream des clients -> out 
			List<PrintStream> listClientPrintStream = new ArrayList<PrintStream>();
			
			int nbJoueur;
			
			while(true){
				//Accepte le premier client et l'ajoute � la liste des clients
				listClientSocket.add(welcomeSocket.accept());
				
				//Cr�ation du BufferedReader du client1
				listClientBufferedReader.add(new BufferedReader(new InputStreamReader(listClientSocket.get(0).getInputStream())));
				listClientPrintStream.add(new PrintStream(listClientSocket.get(0).getOutputStream()));
				
				//R�cup�ration du nombre de joueur qu'il aura dans la partie
				nbJoueur = Integer.parseInt(listClientBufferedReader.get(0).readLine());
				
				//Attente de tous les joueurs et cr�ation de leur socket
				for(int i = 1; i<nbJoueur; i++){
					System.out.println("new player added to the server");
					listClientSocket.add(welcomeSocket.accept());
					listClientBufferedReader.add(new BufferedReader(new InputStreamReader(listClientSocket.get(i).getInputStream())));
					listClientPrintStream.add(new PrintStream(listClientSocket.get(i).getOutputStream()));
				}
				
				//Envoie � tout le monde que la partie commence
				for(PrintStream out : listClientPrintStream){
					System.out.println("Dis au clients que le jeu d�marre");
					out.println("DEBUT");
				}
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
