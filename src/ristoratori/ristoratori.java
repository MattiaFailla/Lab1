/*
 * D'Angelo Lorenzo - 742577 - Varese
 * Failla Mattia - 742581 - Varese
 * Pedotti Samuel - 739897 - Varese
 * */

package ristoratori;

import _database.Database;
import ristoratori.Login.EatAdvisorLogin;

import javax.xml.crypto.Data;

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

		// Starting the UI
		EatAdvisorLogin.main();
	}
}
