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
		Database.init();
		// Writing some log
		// Database.write(1, DataBase.data_types.INFO, "App started");

		System.out.println("App started");

		// Starting the UI
		//CustomerLogin.main(args);
		CustomerRegistration.main(args);
	}
}
