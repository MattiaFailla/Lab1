package ristoratori;

import _database.Database;
import ristoratori.Login.EatAdvisorLogin;

import java.io.IOException;

/**
 * StartApp is the entry point for the app ristoratori
 */
public class RestaurantStartApp {
	/**
	 * This is the entry-point for the app ristoratori.
	 *
	 * @param args The args from the jvm
	 */
	public static void main(String[] args) {
		// Starting the app
		// Init the database
		Database.init();
		// Writing some log
		// Database.write(1, DataBase.data_types.INFO, "App started");

		System.out.println("App started");

		try {
			System.out.println(Database.getClients());
			System.out.println(Database.checkClient("Mattia"));

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Starting the UI
		EatAdvisorLogin.main();
		}
}
