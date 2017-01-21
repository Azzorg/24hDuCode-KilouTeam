package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Serveur {
	private static int PORT = 1500;
	private enum Action {
	    MOVED, MALUS, BONUS, BOTTE;
	}
	
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
			//Liste de joueur
			List<JoueurSimple> listJoueur = new ArrayList<JoueurSimple>();
			
			int joueurTour;
			int nbJoueur;
			int indexJoueur;
			Action action;
			String typeMalus = "";
			int targetMalus = -1;
			String typeBonus = "";
			String typeBotte;
			JoueurSimple gagnant = null;
			
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
					indexJoueur = joueurTour%nbJoueur;
					
					//Désignation du joueur qui doit jouer
					for(int i = 0; i<nbJoueur; i++){
						//Envoi de "YOURTURN" au joueur qui doit jouer
						if(i == indexJoueur)
							listClientPrintStream.get(i).println("YOURTURN");
						//Envoi de "NOTYOURTURN" aux joueurs qui ne jouent pas + de l'id du joueur qui joue
						else
							listClientPrintStream.get(i).println("NOTYOURTURN\n" + joueurTour%nbJoueur);
					}
					
					//Attente de la confirmation de tous les clients
					waitConfirmClient(listClientSocket, nbJoueur);
					
					//Envoi au client qui doit jouer qu'il peut effectivement jouer car tous les autres clients le savent
					listClientPrintStream.get(indexJoueur).println("OK");
					
					//Attente de l'action du client
					while(!listClientBufferedReader.get(indexJoueur).readLine().equals("ACTION")){
						System.out.println("Attente choix joueur...");
					}
					
					action = Action.valueOf(listClientBufferedReader.get(indexJoueur).readLine());
					
					//Gestion de l'action effectuée par le client
					switch(action){
						case MOVED:
							//Gestion du moved
							System.out.println("Le joueur "+ indexJoueur + " a bougé");
							break;
						case MALUS:
							typeMalus = listClientBufferedReader.get(indexJoueur).readLine();
							targetMalus = Integer.parseInt(listClientBufferedReader.get(indexJoueur).readLine());
							System.out.println("Le joueur " + indexJoueur + "a envoyé le malus " + typeMalus + " au joueur " + targetMalus);
							break;
						case BONUS:
							typeBonus = listClientBufferedReader.get(indexJoueur).readLine();
							System.out.println("Le joueur " + indexJoueur + "a utilisé le bonus " + typeBonus);
							break;
						case BOTTE:
							typeBotte = listClientBufferedReader.get(indexJoueur).readLine();
							System.out.println("Le joueur");
							break;
						default:
							System.out.println("Problème réception action du client");
							break;
					}
					
					//Ecriture des MAJ dans le document HTML
					
					//Envoi du nouveau document HTML à tous les clients
					for(PrintStream out : listClientPrintStream)
						out.println("Docuement HTML");
					
					//Attente de tous les OK
					waitConfirmClient(listClientSocket, nbJoueur);
					
					if(listJoueur.get(indexJoueur).getKmParcouru() == 10240){
						gagnant = listJoueur.get(indexJoueur);
						
					}
						
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
