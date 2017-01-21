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
		ThreadOK threadOK [] = new ThreadOK[nbJoueur];
		
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
			boolean fin;
			//Liste des sockets des clients
			List<Socket> listClientSocket = new ArrayList<Socket>();
			//Liste des BufferedReader des clients -> in
			List<BufferedReader> listClientBufferedReader = new ArrayList<BufferedReader>();
			//Liste des PrintStream des clients -> out 
			List<PrintStream> listClientPrintStream = new ArrayList<PrintStream>();
			
			int joueurTour = 0;
			int nbJoueur = 0;
			
			while(true){
				fin = false;
				joueurTour = -1;
				nbJoueur = 0;
				//Accepte le premier client et l'ajoute à la liste des clients
				listClientSocket.add(welcomeSocket.accept());
				
				//Création du BufferedReader du client1
				listClientBufferedReader.add(new BufferedReader(new InputStreamReader(listClientSocket.get(0).getInputStream())));
				listClientPrintStream.add(new PrintStream(listClientSocket.get(0).getOutputStream()));
				
				//Envoi au client 1 que c'est ok
				listClientPrintStream.get(0).println("OK");
				
				//Récupération du nombre de joueur qu'il aura dans la partie
				nbJoueur = Integer.parseInt(listClientBufferedReader.get(0).readLine());

				//Envoi au client 1 que c'est ok
				listClientPrintStream.get(0).println("OK");
				
				//Attente de tous les joueurs et création de leur socket
				for(int i = 1; i<nbJoueur; i++){
					System.out.println("new player added to the server");
					listClientSocket.add(welcomeSocket.accept());
					System.out.println("Player added");
					
					//System.out.println("Envoie au client" + i+1 + " : OK");
					
					listClientBufferedReader.add(new BufferedReader(new InputStreamReader(listClientSocket.get(i).getInputStream())));
					listClientPrintStream.add(new PrintStream(listClientSocket.get(i).getOutputStream()));

					//Envoi au client 1 que c'est ok
					listClientPrintStream.get(i).println("OK");
				}
				
				//Envoie à tout le monde que la partie commence
				/*for(PrintStream out : listClientPrintStream){
					System.out.println("Dis au clients que le jeu démarre");
					out.println("DEBUT");
				}*/
				
				//Envoi au client 1 que c'est le début de la partie
				listClientPrintStream.get(0).println("DEBUT");
				while(!listClientBufferedReader.get(0).readLine().equals("OK")){
					System.out.println("Client 1 pas OK");
				}
				
				//Envoi au client 2 que c'est le début de la partie
				listClientPrintStream.get(1).println("DEBUT");
				while(!listClientBufferedReader.get(1).readLine().equals("OK")){
					System.out.println("Client 2 pas OK");
				}
				
				System.out.println("Début du game");
				
				//Attend que tous les clients confirme que la partie commence
				//waitConfirmClient(listClientSocket, nbJoueur);
				
				//Début du jeu
				while(!fin){
					joueurTour++;
					
					//Désignation du joueur qui doit jouer
					for(int i = 0; i<nbJoueur; i++){
						//Envoi de "YOURTURN" au joueur qui doit jouer
						if(i == joueurTour%nbJoueur)
							listClientPrintStream.get(i).println("YOURTURN");
						//Envoi de "NOTYOURTURN" aux joueurs qui ne jouent pas + de l'id du joueur qui joue
						else
							listClientPrintStream.get(i).println("NOTYOURTURN\n" + joueurTour%nbJoueur);
					}
					
					
					
					
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} /*catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
