package application;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

public class JoueurSimple {
	private String nom = "";
	private int nJoueur;
	private Socket socket;
	private BufferedReader in;
	private PrintStream out;
	private int kmParcouru;
	private double positionX;
	private double positionY;
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
	public BufferedReader getIn() {
		return in;
	}
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	
	
	public PrintStream getOut() {
		return out;
	}
	public void setOut(PrintStream out) {
		this.out = out;
	}
	
	
	public int getKmParcouru() {
		return kmParcouru;
	}
	public void setKmParcouru(int kmParcouru) {
		this.kmParcouru = kmParcouru;
	}
	
	
	public double getPositionX() {
		return positionX;
	}
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	
	
	public double getPositionY() {
		return positionY;
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	
	
	public int getnJoueur() {
		return nJoueur;
	}
	public void setnJoueur(int nJoueur) {
		this.nJoueur = nJoueur;
	}
	
}
