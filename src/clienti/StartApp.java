package clienti;

import database.Database;
import clienti.Login.CustomerLogin;
import clienti.Registration.CustomerRegistration;

import java.io.IOException;

/**
 * StartApp is the entry point for the app clienti
 */
public class StartApp {
	/**
	 * This is the entry-point for the app clienti.
	 *
	 * @param args The args from the jvm
	 */
	public static void main(String[] args) {
		// Starting the app
		// Init the database
		try {
			Database.init();
			// Writing some log
			// Database.write(1, DataBase.data_types.INFO, "App started");
			// @todo: remove this testing line
			Database.read(1);
		} catch (IOException e) { e.printStackTrace(); }

		System.out.println("App started");

		// Starting the UI
		CustomerRegistration.main(args);
		CustomerLogin.main(args);
	}
}
