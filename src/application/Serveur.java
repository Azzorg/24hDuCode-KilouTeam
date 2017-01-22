package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import serveur.HTMLWriter;
import serveur.Place;
import serveur.PlaceSearcher;

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
	public static void waitConfirmClient(List<JoueurSimple> list, int nbJoueur) throws InterruptedException{
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
			/*//Liste des sockets des clients
			List<Socket> listClientSocket = new ArrayList<Socket>();
			//Liste des BufferedReader des clients -> in
			List<BufferedReader> listClientBufferedReader = new ArrayList<BufferedReader>();
			//Liste des PrintStream des clients -> out 
			List<PrintStream> listClientPrintStream = new ArrayList<PrintStream>();*/
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
			String[] HTMLtoClient;
			
			while(true){
				int j =0;
				fin = false;
				joueurTour = -1;
				nbJoueur = 0;
				
				//Envoie a tous les client de la carte
				PlaceSearcher ps = new PlaceSearcher();
				
				/*InputStream boulangerie =  ps.searchPlace("rennes", "boulangerie");
				InputStream boucherie =  ps.searchPlace("rennes", "boucherie");
				InputStream pharmacie =  ps.searchPlace("rennes", "pharmacie");
				
				ps.writeToFile(boulangerie, "boulangerie.json");
				ps.writeToFile(boucherie, "boucherie.json");
				ps.writeToFile(pharmacie, "pharmacie.json");*/
				
				ArrayList<Place> listBoulangerie = ps.parseResult("boulangerie.json", "boulangerie");
				ArrayList<Place> listBoucherie = ps.parseResult("boucherie.json", "boucherie");
				ArrayList<Place> listPharmacie = ps.parseResult("pharmacie.json", "pharmacie");
				
				ArrayList<ArrayList<Place>> allBat = new ArrayList<ArrayList<Place>>();
				
				allBat.add(listBoulangerie);
				allBat.add(listBoucherie);
				allBat.add(listPharmacie);
				
				HTMLWriter h = new HTMLWriter();
				String js = h.CreateJSGeocode(allBat);
				
				String filetoSend = h.RewriteFile(js);
				System.out.println("Fichier ecrit");
				
				//Accepte le premier client et l'ajoute à la liste des clients
				listJoueur.add(new JoueurSimple());
				listJoueur.get(0).setSocket(welcomeSocket.accept());

				//Création du BufferedReader du client1
				listJoueur.get(0).setIn(new BufferedReader(new InputStreamReader(listJoueur.get(0).getSocket().getInputStream())));
				listJoueur.get(0).setOut(new PrintStream(listJoueur.get(0).getSocket().getOutputStream()));
				
				//Envoi au client 1 que c'est ok
				listJoueur.get(0).getOut().println("OK");
				
				//Récupération du nombre de joueur qu'il aura dans la partie
				nbJoueur = Integer.parseInt(listJoueur.get(0).getIn().readLine());
				
				//get name joueur
				listJoueur.get(0).setNom(listJoueur.get(0).getIn().readLine());

				//Envoi au client 1 que c'est ok
				listJoueur.get(0).getOut().println("OK");
				listJoueur.get(0).getOut().println(filetoSend);

				
				System.out.println("Attente de tous les clients");
				//Attente de tous les joueurs et création de leur socket
				for(int i = 1; i<nbJoueur; i++){
					System.out.println("new player added to the server");
					listJoueur.add(new JoueurSimple());
					listJoueur.get(i).setSocket(welcomeSocket.accept());
					System.out.println("Player added");
					
					listJoueur.get(i).setIn(new BufferedReader(new InputStreamReader(listJoueur.get(i).getSocket().getInputStream())));
					listJoueur.get(i).setOut(new PrintStream(listJoueur.get(i).getSocket().getOutputStream()));

					//Envoi au client 1 que c'est ok
					listJoueur.get(i).getOut().println("OK");
					listJoueur.get(i).getOut().println(filetoSend);
					
				}
				
				System.out.println("Split du fichier HTML");
				//Envoi à tous la carte 
				HTMLtoClient = filetoSend.split("\n");
				
				System.out.println("Taille : " + HTMLtoClient.length);
					
				//Envoi à tous les clients la taille du document HTML
				for(JoueurSimple joueur : listJoueur){
					joueur.getOut().println("TAILLEFICHIER\n" + HTMLtoClient.length);
				}
				
				//Attente de la confirmation de tous les clients
				waitConfirmClient(listJoueur, nbJoueur);
				
				//Envoi du document à tous les clients				
				for(int i = 0; i<HTMLtoClient.length; i++){
					for(JoueurSimple joueur : listJoueur){
						System.out.println(HTMLtoClient[i]);
						joueur.getOut().println(HTMLtoClient[i]);
					}
					waitConfirmClient(listJoueur, nbJoueur);
				}
				
				
				//Envoie à tout le monde que la partie commence
				/*for(JoueurSimple joueur : listJoueur){
					System.out.println("Dis au clients que le jeu démarre");
					joueur.getOut().println("DEBUT");
				}*/
				
				//Envoi au client 1 que c'est le début de la partie
				listJoueur.get(0).getOut().println("DEBUT");
				while(!listJoueur.get(0).getIn().readLine().equals("OK")){
					System.out.println("Client 1 pas OK");
				}
				
				//Envoi au client 2 que c'est le début de la partie
				listJoueur.get(1).getOut().println("DEBUT");
				while(!listJoueur.get(1).getIn().readLine().equals("OK")){
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
							listJoueur.get(i).getOut().println("TURN\nYOURTURN");
						//Envoi de "NOTYOURTURN" aux joueurs qui ne jouent pas + de l'id du joueur qui joue
						else
							listJoueur.get(i).getOut().println("TURN\nNOTYOURTURN\n" + indexJoueur);
					}
					
					//Attente de la confirmation de tous les clients
					waitConfirmClient(listJoueur, nbJoueur);
					
					//Envoi au client qui doit jouer qu'il peut effectivement jouer car tous les autres clients le savent
					listJoueur.get(indexJoueur).getOut().println("OK");
					
					//Attente de l'action du client
					while(!listJoueur.get(indexJoueur).getIn().readLine().equals("ACTION")){
						System.out.println("Attente choix joueur...");
					}
					
					action = Action.valueOf(listJoueur.get(indexJoueur).getIn().readLine());
					
					//Gestion de l'action effectuée par le client
					switch(action){
						case MOVED:
							//Gestion du moved
							System.out.println("Le joueur "+ indexJoueur + " a bougé");
							break;
						case MALUS:
							typeMalus = listJoueur.get(indexJoueur).getIn().readLine();
							targetMalus = Integer.parseInt(listJoueur.get(indexJoueur).getIn().readLine());
							System.out.println("Le joueur " + indexJoueur + "a envoyé le malus " + typeMalus + " au joueur " + targetMalus);
							break;
						case BONUS:
							typeBonus = listJoueur.get(indexJoueur).getIn().readLine();
							System.out.println("Le joueur " + indexJoueur + "a utilisé le bonus " + typeBonus);
							break;
						case BOTTE:
							typeBotte = listJoueur.get(indexJoueur).getIn().readLine();
							System.out.println("Le joueur");
							break;
						default:
							System.out.println("Problème réception action du client");
							break;
					}
					
					//Ecriture des MAJ dans le document HTML
					
					
					
					
					
					//Envoi du nouveau document HTML à tous les clients
					for(JoueurSimple joueur : listJoueur)
						joueur.getOut().println("Document HTML");
					
					//Attente de tous les OK
					waitConfirmClient(listJoueur, nbJoueur);
					
					//Un joueur a gagné
					if(listJoueur.get(indexJoueur).getKmParcouru() == 10240){
						gagnant = listJoueur.get(indexJoueur);
						fin = true;
						for(JoueurSimple joueur : listJoueur){
							joueur.getOut().println("ETAT\nFIN\n" + joueur.getNom());
						}
					}
					else{
						for(JoueurSimple joueur : listJoueur){
							joueur.getOut().println("ETAT\nNONFIN\n" + joueur.getNom());
						}
					}
					
					//Fin boucle
					if(j == 10){
						fin = true;
					}
					j++;
					
				}
				System.out.println("FIN");
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
