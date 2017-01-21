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
	
	/**
	 * Méthode qui attend la confirmation de tous les clients
	 * @param list
	 * @param nbJoueur
	 * @throws InterruptedException
	 */
	public static void waitConfirmClient(List<Socket> list, int nbJoueur) throws InterruptedException{
		//Tableau de Thread pour les attentes de confirmation des clients
		ThreadOK threadOK [] = new ThreadOK[nbJoueur];;
		
		for(int i = 0; i<nbJoueur; i++){
			threadOK[i] = new ThreadOK(list.get(i));
			threadOK[i].start();
		}
		
		for(int i = 0; i<nbJoueur; i++){
			threadOK[i].join();
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("début");
			ServerSocket welcomeSocket = new ServerSocket(PORT);
			//Liste des sockets des clients
			List<Socket> listClientSocket = new ArrayList<Socket>();
			//Liste des BufferedReader des clients -> in
			List<BufferedReader> listClientBufferedReader = new ArrayList<BufferedReader>();
			//Liste des PrintStream des clients -> out 
			List<PrintStream> listClientPrintStream = new ArrayList<PrintStream>();
			
			
			
			int nbOk = 0;
			int nbJoueur = 0;
			
			while(true){
				//Accepte le premier client et l'ajoute à la liste des clients
				listClientSocket.add(welcomeSocket.accept());
				
				//Création du BufferedReader du client1
				listClientBufferedReader.add(new BufferedReader(new InputStreamReader(listClientSocket.get(0).getInputStream())));
				listClientPrintStream.add(new PrintStream(listClientSocket.get(0).getOutputStream()));
				
				//Récupération du nombre de joueur qu'il aura dans la partie
				nbJoueur = Integer.parseInt(listClientBufferedReader.get(0).readLine());
				
				//Attente de tous les joueurs et création de leur socket
				for(int i = 1; i<nbJoueur; i++){
					System.out.println("new player added to the server");
					listClientSocket.add(welcomeSocket.accept());
					listClientBufferedReader.add(new BufferedReader(new InputStreamReader(listClientSocket.get(i).getInputStream())));
					listClientPrintStream.add(new PrintStream(listClientSocket.get(i).getOutputStream()));
				}
				
				//Envoie à tout le monde que la partie commence
				for(PrintStream out : listClientPrintStream){
					System.out.println("Dis au clients que le jeu démarre");
					out.println("DEBUT");
				}
				
				//Attend que tous les clients confirme que la partie commence
				waitConfirmClient(listClientSocket, nbJoueur);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
