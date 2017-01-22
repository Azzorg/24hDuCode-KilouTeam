package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadOK extends Thread{
	private JoueurSimple client;
	final private String attendu = "OK";
	
	public ThreadOK(JoueurSimple client){
		super();
		this.client = client;
	}
	
	public void run(){
		System.out.println("Attente du client...");
		try {			
			while(!client.getIn().readLine().equals(attendu)){
				System.out.println("Pas le bon message du client");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
