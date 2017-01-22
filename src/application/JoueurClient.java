package application;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class JoueurClient extends Thread {
	private final int PORT;
	private int nJoueur;
	private String name;
	private String actionSuivante;
	
	public void setActionSuivante(String a){
		actionSuivante = a;
	}
	
	private enum Turn{
		YOURTURN, NOTYOURTURN;
	}
	private enum Action{
		MOVED, MALUS, BONUS, BOTTE;
	}
	private enum EtatPartie{
		FIN, NONFIN;
	}
	
	
	public JoueurClient(String n, int nJoueur, int PORT){
		super();
		this.PORT = PORT;
		this.nJoueur = nJoueur;
		this.name = n;
	}
	
	public void run(){
		int i = 0;
		//Nombre de joueur
		int nbJoueur = 2;
		Turn turn;
		EtatPartie etat;
		boolean fin = false;
		int al;
		Random rd;
		
		
		try {
			//Le premier client tente de se connecter au serveur
			Socket socket = new Socket("localhost", PORT);
			
			//Création de in et out pour le client
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());
			
			System.out.println("Connection client " + name + " : OK");
			
			//Attente que le serveur soit OK
			while(!in.readLine().equals("OK")){
				System.out.println(name + " : Serveur pas OK");
			}
			
			//Si c'est le joueur qui choisi le nombre de joueur
			if(nJoueur == 1){
				//Envoi au serveur du nombre de 
				out.println(nbJoueur + "\n" + nJoueur);
				
				System.out.println(name + " : Envoi du nombre de joueur");
				
				//Attente que le serveur soit OK
				while(!in.readLine().equals("OK")){
					System.out.println("Serveur pas OK");
				}
				
				System.out.println(name + " : Client 1 OK");
			}
				
		
			
			//Joueur attend le début de la partie
			while(!in.readLine().equals("DEBUT")){
				System.out.println(name + " : Attente du début de la partie");
			}
			
			
			out.println("OK");
			
			System.out.println(name + " : début ok");
			
			while(!fin){
				//YOURTURN ou NOTYOURTURN
				while(!in.readLine().equals("TURN")){
					System.out.println("Attente serveur ...");
				}
				turn = Turn.valueOf(in.readLine());
				switch (turn) {
				//Tour de ce joueur
				case YOURTURN:
					//confirmation au serveur
					out.println("OK");
					//Attente serveur
					while(!in.readLine().equals("OK")){
						System.out.println("Attente du serveur ...");
					}
					System.out.println(name + " : A toi de jouer");
					
					//Action joueur
					//Random number
					rd = new Random();
					al = rd.nextInt(3);
					
					//Action du joueur
					
					Thread.sleep(10000);
					
					System.out.println(name + " : Envoi de " + actionSuivante);
					out.println(actionSuivante);

					break;
					
				case NOTYOURTURN:
					//Confirmation au serveur
					out.println("OK");
					break;

				default:
					break;
				}
				
				//Réception de la nouvelle carte HTML
				while(!in.readLine().equals("Document HTML")){
					System.out.println("Attente serveur...");
				}
				
				//Envoi confirmation au serveur
				out.println("OK");
				
				while(!in.readLine().equals("ETAT")){
					System.out.println("Attente serveur");
				}
				
				etat = EtatPartie.valueOf(in.readLine());
				switch (etat) {
				case FIN:
					System.out.println("Gagnant : " + in.readLine());
					fin = true;
					break;

				default:
					break;
				}
				
				//Fin boucle
				if(i == 10){
					fin = true;
				}
				i++;
				
			}
			
			System.out.println("FIN");
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
