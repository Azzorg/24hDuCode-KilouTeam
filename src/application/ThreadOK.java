package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadOK extends Thread{
	private Socket client;
	final private String attendu = "OK";
	
	public ThreadOK(Socket client){
		super();
		this.client = client;
	}
	
	public void run(){
		System.out.println("Attente du client...");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			while(!in.readLine().equals(attendu)){
				System.out.println("Pas le bon message du client");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
