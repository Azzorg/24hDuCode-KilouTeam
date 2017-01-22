package serveur;

import java.io.Serializable;

public class FileSérialisation implements Serializable{
	private String toSend;

	public FileSérialisation(String s){
		this.toSend = s;
	}
	
	public String getToSend() {
		return toSend;
	}

	public void setToSend(String toSend) {
		this.toSend = toSend;
	}
}	
