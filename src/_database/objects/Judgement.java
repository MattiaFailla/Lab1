package _database.objects;

import java.io.Serializable;

public class Judgement implements Serializable {

	private static final long serialVersionUID = -3744536479556587616L;

	public String username;
	public String restaurantName;
	public Integer rating;
	public String judgement;

	public Judgement(){}

	public Judgement(String username, String restaurantName, Integer rating, String judgement){
		this.username = username;
		this.restaurantName = restaurantName;
		this.rating = rating;
		this.judgement = judgement.substring(0, 256);
	}

	public String toString(){
		return "The user" + username + " has rated the restaurant " + restaurantName +
				" with " + rating.toString() + " stars.\n" +
				"Comment:\n" + judgement;
	}
}