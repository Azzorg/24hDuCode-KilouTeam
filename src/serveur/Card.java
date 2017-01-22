package serveur;

public class Card {
	private String name;
	private String action;
	private String imagePath;
	private boolean canBePlayed;

	/**
	 * 
	 * @param name
	 * @param type
	 * @param action
	 * @param imagePath
	 */
	public Card(String name, String action, String imagePath) {
		this.setName(name);
		this.setAction(action);
		this.setImagePath(imagePath);
		this.canBePlayed = false;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * @return the canBePlayed
	 */
	public boolean isCanBePlayed() {
		return canBePlayed;
	}

	/**
	 * @param canBePlayed
	 *            the canBePlayed to set
	 */
	public void setCanBePlayed(boolean canBePlayed) {
		this.canBePlayed = canBePlayed;
	}

}
