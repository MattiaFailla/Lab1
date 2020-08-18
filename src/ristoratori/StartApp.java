package ristoratori;

import database.Database;
import ristoratori.Login.EatAdvisorLogin;
import ristoratori.Registration.EatAdvisorRegistration;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Arrays;

/**
 * StartApp is the entry point for the app ristoratori
 */
public class StartApp {
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
			System.out.println(Arrays.toString(Database.getClients()));
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Starting the UI
		//EatAdvisorLogin.main(args);
		EatAdvisorRegistration.main(args);
	}
}
