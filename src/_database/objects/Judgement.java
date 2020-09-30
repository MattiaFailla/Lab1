package _database.objects;

import java.io.Serializable;

/**
 * This is the custom Judjement serializable class to hold the Judgement of the restaurants
 */
public class Judgement implements Serializable {

	private static final long serialVersionUID = -3744536479556587616L;

	public String username;
	public String restaurantName;
	public Integer rating;
	public String judgement;

	public Judgement() {}

	public Judgement(String username, String restaurantName, Integer rating, String judgement) {
		this.username = username;
		this.restaurantName = restaurantName;
		this.rating = rating;
		this.judgement = judgement;
	}

	public String toString() {
		return  username + " | " +
				rating.toString() + " stars. "
				+ judgement;
	}
}