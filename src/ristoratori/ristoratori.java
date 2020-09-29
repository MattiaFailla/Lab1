/*
 * D'Angelo Lorenzo - 742577 - Varese
 * Failla Mattia - 742581 - Varese
 * Pedotti Samuel - 739897 - Varese
 * */

package ristoratori;

import _database.Database;
import _database.DatabaseExceptions;
import ristoratori.Login.EatAdvisorLogin;

import java.io.IOException;

/**
 * StartApp is the entry point for the app ristoratori
 */
public class ristoratori {
	/**
	 * This is the entry-point for the app ristoratori.
	 *
	 * @param args The args from the jvm
	 */
	public static void main(String[] args) {
		// Starting the app
		// Init the database
		Database.init();
		System.out.println("App started");

		//todo: auto-registrazione di un ristoratore (con verifica che non esista)
		String name = "ristoratore";
		String surname = "ristoratore";
		String city = "Milano";
		String province = "MI";
		String email = "ristoratore@lab.com";
		String nickname = "ristoratore";
		String password = "!Ristoratore1";

		try {
			// Check the customer in db
			Database.getCustomer(nickname);
			//JOptionPane.showMessageDialog(null, "Eat advisor already exists");
		} catch (IOException | ClassNotFoundException ioException) {
			ioException.printStackTrace();
		} catch (DatabaseExceptions databaseExceptions) {
			// Saving the customer in the database
			Database.insertClient(name, surname, city, province, email, nickname, password);
		}

		// Starting the UI
		EatAdvisorLogin.main();
	}
}
