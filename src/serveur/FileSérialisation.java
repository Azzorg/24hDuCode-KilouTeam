package serveur;

import java.io.Serializable;

public class FileSÚrialisation implements Serializable{
	private String toSend;

	public FileSÚrialisation(String s){
		this.toSend = s;
	}
	
	public String getToSend() {
		return toSend;
	}

	public void setToSend(String toSend) {
		this.toSend = toSend;
	}
}	
