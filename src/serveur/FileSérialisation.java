package serveur;

import java.io.Serializable;

public class FileS�rialisation implements Serializable{
	private String toSend;

	public FileS�rialisation(String s){
		this.toSend = s;
	}
	
	public String getToSend() {
		return toSend;
	}

	public void setToSend(String toSend) {
		this.toSend = toSend;
	}
}	
