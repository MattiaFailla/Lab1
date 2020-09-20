/*
 * D'Angelo Lorenzo - 742577 - Varese
 * Failla Mattia - 742581 - Varese
 * Pedotti Samuel - 739897 - Varese
 * */

package clienti;

import _database.Database;
import clienti.Search.CustomerSearch;

/**
 * StartApp is the entry point for the app clienti
 */
public class clienti {
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
		CustomerSearch.main();
	}
}
