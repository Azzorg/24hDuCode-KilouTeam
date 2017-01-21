package application;

import java.net.Socket;

public class ThreadOK extends Thread{
	private Socket client;
	
	public ThreadOK(Socket client){
		super();
		this.client = client;
	}
	
	public void run(){
		System.out.println("Attente du client...");
	}
	
	
}
