package serveur;

public class Place {
	private String name;
	private String addr;
	private String type;
	
	public Place(String n, String addr, String type){
		this.name = n;
		this.addr = addr;
		this.type = type;
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
	
	
	public String getAddr(){
		return addr;
	}
	
	public String getType(){
		return type;
	}
}
