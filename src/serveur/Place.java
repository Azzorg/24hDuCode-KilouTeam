package serveur;

public class Place {
	private String name;
	private String addr;

	
	public Place(String n, String addr){
		this.name = n;
		this.addr = addr;
	}
	
	/**
	 * Show the info of a place
	 */
	public void ShowInfo(){
		System.out.println("--------------------------");
		System.out.println("Nom: " + this.name);
		System.out.println("Adresse: "+ this.addr);
		System.out.println("--------------------------");
	}


}
