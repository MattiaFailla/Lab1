package _database;

import _database.objects.Client;
import _database.objects.Judgement;
import _database.objects.Restaurant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Database is the class responsible for correct communication with the
 *  * database.
 *  * <p>
 *  * The data are saved in the following format:
 *  * --- TODO ---
 *  * <p>
 *  * In this class you can find helper functions to find, insert, update and delete data
 *  * from the database. Since this class is in common between clienti and ristoratori our
 *  * priority is to make a powerful yet simple helper class to achieve a single point of
 *  * junction between these two applications.
 */
public class Database {
	private static final String restaurant_db = "./EatAdvisor.dati";
	private static final String client_db = "./Utenti.dati";

	public static boolean init() {
		// Initialize the database
		// Creating database if not exists
		File db_clienti = new File(client_db);
		// Forcing the os to create the files
		try {
			boolean success = true;
			File db_ristoratori = new File(restaurant_db);
			if (!db_ristoratori.exists()) success = db_clienti.createNewFile();
			if (!db_clienti.exists()) success = db_ristoratori.createNewFile();
			return success;
		} catch (IOException ignored) {
			return false;
		}
	}

	//region WRITERS
	public static void insertClient(String name, String surname, String city, String province, String email, String nickname, String password) {
		// Saving the Customer
		Client clt = new Client(name, surname, city, province, email, nickname, password);
		try {
			// Before saving the new customer we need to extract the old customers
			ArrayList<Client> entries = (ArrayList<Client>) readFile(client_db);
			entries.add(clt);

			File file = new File(client_db);
			FileOutputStream fOut = new FileOutputStream(file);
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);

			oOut.writeObject(entries);
			oOut.close();
			fOut.close();
		} catch (IOException e) { e.printStackTrace(); }
		System.out.println("Client succesfully inserted.");
	}

	public static void insertRestaurant(String name, Integer phoneNumber, String qualifier, String street, Integer civicNumber, String city, String province, Integer CAP, String url, Restaurant.types type) {
		// Saving the EatAdvisor

		Restaurant rst = new Restaurant(name, qualifier, street, civicNumber, city, province, CAP, phoneNumber, url, type, new ArrayList<Judgement>());
		try {
			// Before saving the new restaurant we need to extract the old restaurant data
			ArrayList<Restaurant> entries = (ArrayList<Restaurant>) readFile(restaurant_db);
			entries.add(rst);

			File file = new File(restaurant_db);
			FileOutputStream fOut = new FileOutputStream(file);
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);

			oOut.writeObject(entries);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Restaurant succesfully inserted.");
	}

	public static void insertJudgment(String username, String restaurantName, Integer rating, String judgement) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Finding the restaurant by restaurantName
		int restInt = getIndexByRestaurantName(restaurantArrayList, restaurantName);
		// Getting the list of judgements
		ArrayList<Judgement> judgementList = restaurantArrayList.get(restInt).judgement;
		// Updating the judjement list with the new judgement
		Judgement jdg = new Judgement(username, restaurantName, rating, judgement);
		judgementList.add(jdg);
		// Creating the new restaurant object
		Restaurant newRestaurant = restaurantArrayList.get(restInt);

		// Overriding the restuarant element in order to save the new list of judgements
		ArrayList<Restaurant> entries = (ArrayList<Restaurant>) readFile(restaurant_db);
		entries.set(restInt, newRestaurant);

		// Saving to file
		try {
			File file = new File(restaurant_db);
			FileOutputStream fOut = new FileOutputStream(file);
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);

			oOut.writeObject(entries);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Judgement successfully inserted.");
	}
	//endregion

	//region GETTER
	public static boolean checkClient(String fieldData) throws IOException, ClassNotFoundException {
		// Return true if fieldData exists in any field of clients
		for (Client client : getClients()) if (client.toString().contains(fieldData)) return true;
		return false;
	}

	public static List<Client> getClients() throws IOException, ClassNotFoundException {
		// Returning every client in the file
		File file = new File(client_db);
		FileInputStream fIn = new FileInputStream(file);
		ObjectInputStream oIn = new ObjectInputStream(fIn);

		// Getting data
		Object data = oIn.readObject();
		List<Client> list = new ArrayList<>();
		if (data instanceof List) list = (List<Client>) data;
		return list;
	}

	public static List<Restaurant> getRestaurants() throws IOException, ClassNotFoundException {
		// Returning every restaurant in the file
		File file = new File(restaurant_db);
		FileInputStream fIn = new FileInputStream(file);
		ObjectInputStream oIn = new ObjectInputStream(fIn);

		// Getting data
		Object data = oIn.readObject();
		List<?> list = new ArrayList<>();
		if (data instanceof List) {
			list = ((List<?>) data).stream()
					.filter(x -> x instanceof Restaurant)
					.map(x -> (Restaurant) x)
					.collect(Collectors.toList());
		}
		return (List<Restaurant>) list;
	}

	// Single elements
	public static Client getClient(String nickname) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Client> clientArrayList = getClients();
		// Finding the restaurant by restaurantName
		int restInt = getIndexByClientNickname(clientArrayList, nickname);
		// Getting the list of judgements
		return clientArrayList.get(restInt);
	}

	public static Restaurant getRestaurant(String restaurantName) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Finding the restaurant by restaurantName
		int restInt = getIndexByRestaurantName(restaurantArrayList, restaurantName);
		// Getting the list of judgements
		return restaurantArrayList.get(restInt);
	}

	public static List<Judgement> getJudgement(String restaurantName) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Finding the restaurant by restaurantName
		int restInt = getIndexByRestaurantName(restaurantArrayList, restaurantName);
		// Getting the list of judgements
		return restaurantArrayList.get(restInt).judgement;
	}
	//endregion

	// SEARCH
	public static List<Restaurant> getRestaurantByCity(String city) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Building the list of restaurants based on city name
		List<Restaurant> result = new ArrayList<>();
		for (Restaurant restaurant :
				restaurantArrayList) {
			if (restaurant.city.equals(city)) {
				result.add(restaurant);
			}
		}
		return result;
	}

	public static List<Restaurant> getRestaurantByCategory(Restaurant.types resType) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Building the list of restaurants based on city name
		List<Restaurant> result = new ArrayList<>();
		for (Restaurant restaurant :
				restaurantArrayList) {
			if (restaurant.type.equals(resType)) {
				result.add(restaurant);
			}
		}
		return result;
	}


	//region HELPER FUNCT
	// Reads the file and returns all entries in a list
	public static ArrayList<?> readFile(String filename) {
		AtomicReference<ArrayList<?>> persistedEntries = new AtomicReference<>();
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			persistedEntries.set((ArrayList<?>) objIn.readObject());
			objIn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return persistedEntries.get();
	}

	// Get the index of an element in an array based on specific propert
	private static int getIndexByRestaurantName(List<Restaurant> restList, String name) {
		for (int index = 0; index < restList.size(); index++) {
			Restaurant restaurant = restList.get(index);
			if (restaurant.name.equals(name)) {
				return index;
			}
		}
		return -1;// not there is list
	}

	private static int getIndexByRestaurantCity(List<Restaurant> restList, String city) {
		for (int index = 0; index < restList.size(); index++) {
			Restaurant restaurant = restList.get(index);
			if (restaurant.city.equals(city)) {
				return index;
			}
		}
		return -1;// not there is list
	}

	private static int getIndexByClientNickname(List<Client> restList, String nickname) {
		for (int index = 0; index < restList.size(); index++) {
			Client client = restList.get(index);
			if (client.nickname.equals(nickname)) {
				return index;
			}
		}
		return -1;// not there is list
	}

	// RecordType is the type of record that will be inserted into the db
	public enum recordType {
		CLIENTE,
		RECENSIONE,
		RISTORANTE
	}

	// This types are typically used to insert debug messages into the db
	public enum data_types {
		CLIENTS_DATA,
		INFO,
		LOGGING,
		RESTAURANT_DATA
	}
	//endregion

	//region REGEX
	public static boolean regexStandard(String text) {
		return text.matches("^[a-zA-Z]+(([' ][a-zA-Z])?[a-zA-Zàèéìòù]*)*$");
	}

	public static boolean regexProvince(String text) {
		return text.matches("^[a-zA-Z]{2}$");
	}

	public static boolean regexEmail(String text) {
		return text.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+");
	}

	public static boolean regexNickname(String text) {
		return text.matches("^[a-z0-9_-]{3,15}$");
	}

	public static boolean regexPassword(String text) { return text.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$"); }

	public static boolean regexNumber(String text, String quantifier) { return text.matches("^[\\d]" + quantifier + "$"); }

	public static boolean regexPhone(String text) { return text.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$"); }

	public static boolean regexURL(String text) { return text.matches("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()!@:%_\\+.~#?&\\/\\/=]*)"); }
	//endregion
}