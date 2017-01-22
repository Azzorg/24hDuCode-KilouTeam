package serveur;

import java.util.ArrayList;

public class Place {
	private String name;
	private String addr;
	private String type;
	private ArrayList<Card> DropableCard;

	public Place(String n, String addr, String type) {
		this.name = n;
		this.addr = addr;
		this.type = type;
		this.DropableCard = new ArrayList<Card>();

	}

	/**
	 * Init the place whit the card it should drop
	 */
	public void InitCard() {
		switch (type) {
		case "boulangerie":
			setDropableCard();
			break;
		case "boucherie":
			setDropableCard();
			break;
		case "pharmacie":
			setDropableCard();
			break;
		default:
			setDropableCard();
			break;
		}
	}

	public void setDropableCard() {
		DropableCard.add(new Card("", "", "resources/cardImg/Card.png"));
		DropableCard.add(new Card("", "", "resources/cardImg/Card.png"));
		DropableCard.add(new Card("", "", "resources/cardImg/Card.png"));
		DropableCard.add(new Card("", "", "resources/cardImg/Card.png"));
		
	}

	public void dropCard() {
		int cardToDrop = (int) Math.floor(Math.random() * DropableCard.size());
		System.err.println(cardToDrop);

	}

	/**
	 * Show the info of a place
	 */
	public void ShowInfo() {
		System.out.println("--------------------------");
		System.out.println("Nom: " + this.name);
		System.out.println("Adresse: " + this.addr);
		System.out.println("Type: " + this.type);
		System.out.println("--------------------------");
	}

	/*
	 * List of the getter and setter
	 */
	public String getAddr() {
		return addr;
	}

	public String getType() {
		return type;
	}
}
