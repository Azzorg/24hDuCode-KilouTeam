package application;

import java.io.IOException;
import java.net.ServerSocket;

public class Serveur {
	private static int PORT = 1500;
	
	public static void main(String[] args) {
		try {
			ServerSocket welcomeSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
