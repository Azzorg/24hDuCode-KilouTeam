package application;

public class ClientCommunicationServeur {
	private int PORT = 1500;

	public static void main(String args[]) {

		JoueurClient J2 = new JoueurClient("Joueur 2", 2, 1500);
		J2.start();
		J2.setActionSuivante("ACTION\nMOVED\n54\n2");
		System.out.println("FIN CLIENT");
		
		

	}
}
