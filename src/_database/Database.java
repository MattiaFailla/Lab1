package _database;

import _database.objects.Customer;
import _database.objects.Judgement;
import _database.objects.Restaurant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Database is the class responsible for correct communication with the
 * * database file.
 * * <p>
 * * The data is saved into an array of class stored in stream file.
 * * Custom classes are provided to manage and store data as Clienti, Ristoranti and Giudizi.
 * * <p>
 * * In this class you can find helper functions to find, insert, update and delete data
 * * from the database. Since this class is in common between clienti and ristoratori our
 * * priority is to make a powerful yet simple helper class to achieve a single point of
 * * junction between these two applications.
 */
public class Database {
	private static final String restaurant_db = "./src/_database/db/EatAdvisor.dati";
	private static final String client_db = "./src/_database/db/Utenti.dati";

	/**
	 * Initializes the files for the database. This method is implicitly called when a search is issued.
	 *
	 * @return void
	 */
	public static boolean init() {
		// Initialize the database
		// Creating database if not exists
		File db_clienti = new File(client_db);
		File db_ristoratori = new File(restaurant_db);
		// Forcing the os to create the files
		try {
			boolean success = true;
			if (!db_ristoratori.exists() || !db_clienti.exists()) {
				// Ensuring the existence of the directory
				success = db_clienti.getParentFile().mkdirs();
				success = db_clienti.createNewFile();
				success = db_ristoratori.createNewFile();
			}
			return success;
		} catch (Exception e) {
			System.out.println("Errore:" + e.toString());
			return false;
		}
	}

	//region WRITERS
	/**
	 * This function inserts a customer in the database.
	 *
	 * @param name     The name of the client
	 * @param surname  The surname of the client
	 * @param city     The city of the client
	 * @param province The province of client
	 * @param email    The email of the client
	 * @param nickname The unique nickname of the client
	 * @param password The password of the client
	 */
	public static void insertClient(String name, String surname, String city, String province, String email, String nickname, String password) {
		// Saving the Customer
		Customer clt = new Customer(name, surname, city, province, email, nickname, password);
		try {
			// Before saving the new customer we need to extract the old customers
			ArrayList<Customer> entries = (ArrayList<Customer>) ReadObjectFromFile(client_db);
			assert entries != null;
			entries.add(clt);

			File file = new File(client_db);
			FileOutputStream fOut = new FileOutputStream(file);
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);

			oOut.writeObject(entries);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Customer succesfully inserted.");
	}

	/**
	 * This function inserts a restaurant in the database
	 *
	 * @param owner       The name of the owner
	 * @param name        The name of the restaurant
	 * @param phoneNumber The phone number of the restaurant
	 * @param qualifier   The qualifier of the restaurant (via or piazza)
	 * @param street      The street of the restaurant
	 * @param civicNumber The civic number of the restuaurant
	 * @param city        The city of the restaurant
	 * @param province    The province of the restaurant
	 * @param CAP         The cap of the restaurant
	 * @param url         The url of the website of the restaurant
	 * @param type        The type of restaurant
	 */
	public static void insertRestaurant(String owner, String name, Long phoneNumber, String qualifier, String street, Integer civicNumber, String city, String province, Integer CAP, String url, Restaurant.types type) {
		// Saving the Restaurant
		Restaurant rst = new Restaurant(owner, name, qualifier, street, civicNumber, city, province, CAP, phoneNumber, url, type, new ArrayList<>());
		try {
			// Before saving the new restaurant we need to extract the old restaurant data
			ArrayList<Restaurant> entries = (ArrayList<Restaurant>) ReadObjectFromFile(restaurant_db);
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

	/**
	 * This function inserts a judgment in the database
	 *
	 * @param username       The username of the user who insert the judgement
	 * @param restaurantName The restaurant name
	 * @param rating         The rating expressed as integer between 0 and 5
	 * @param judgement      The content of the judgement
	 * @throws IOException            This exception will be thrown if the file is empty
	 * @throws ClassNotFoundException This exception will be thrown if the class is not found
	 */
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
		ArrayList<Restaurant> entries = (ArrayList<Restaurant>) ReadObjectFromFile(restaurant_db);
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
	/// Get all objects
	public static boolean checkCustomer(String fieldData) throws IOException, ClassNotFoundException {
		// Return true if fieldData exists in any field of customers
		for (Customer customer : getCustomers()) if (customer.toString().contains(fieldData)) return true;
		return false;
	}

	/**
	 * @return A list of Customers
	 * @throws IOException            This exception will be thrown if the file is empty
	 * @throws ClassNotFoundException This exception will be thrown if the class is not found
	 */
	public static List<Customer> getCustomers() throws IOException, ClassNotFoundException {
		// Returning every customer in the file
		System.out.println("Getting customers from database");
		File file = new File(client_db);

		FileInputStream fIn = new FileInputStream(file);
		if (fIn.available() <= 0) {
			return new ArrayList<>();
		}
		ObjectInputStream oIn = new ObjectInputStream(fIn);

		// Getting data
		Object data = oIn.readObject();
		List<Customer> list = new ArrayList<>();
		if (data instanceof List) list = (List<Customer>) data;
		System.out.println(list);
		return list;
	}

	/**
	 * @return A list of Restuarants
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Restaurant> getRestaurants() throws IOException, ClassNotFoundException {
		// Returning every restaurant in the file
		System.out.println("Getting restaurants from database");
		File file = new File(restaurant_db);

		FileInputStream fIn = new FileInputStream(file);
		if (fIn.available() <= 0) {
			return new ArrayList<>();
		}
		ObjectInputStream oIn = new ObjectInputStream(fIn);
		if (fIn.available() <= 0) {
			return new ArrayList<>();
		}

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

	/// Get single object
	/**
	 * @param nickname The unique nickname of the customer
	 * @return The Customer
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws DatabaseExceptions     This exception is thrown when the database cannot find the customer
	 */
	public static Customer getCustomer(String nickname) throws IOException, ClassNotFoundException, DatabaseExceptions {
		// Getting the list of customers
		List<Customer> customerArrayList = getCustomers();
		// Finding the restaurant by restaurantName
		int restInt = getIndexByCustomerNickname(customerArrayList, nickname);
		if (restInt == -1) {
			throw new _database.DatabaseExceptions("The customer does not exists.");
		}
		// Getting the customer
		return customerArrayList.get(restInt);
	}

	/**
	 * @param restaurantName The unique identifier of the restaurant
	 * @return A single Restaurant
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws DatabaseExceptions
	 */
	public static Restaurant getRestaurant(String restaurantName) throws IOException, ClassNotFoundException, DatabaseExceptions {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Finding the restaurant by restaurantName
		int restInt = getIndexByRestaurantName(restaurantArrayList, restaurantName);
		if (restInt == -1) {
			throw new _database.DatabaseExceptions("The restaurant does not exists.");
		}
		// Getting the list of judgements
		return restaurantArrayList.get(restInt);
	}

	/**
	 * @param restaurantName The unique identifier of the Restaurant
	 * @return A list of Judgments
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws DatabaseExceptions
	 */
	public static List<Judgement> getJudgement(String restaurantName) throws IOException, ClassNotFoundException, DatabaseExceptions {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Finding the restaurant by restaurantName
		int restInt = getIndexByRestaurantName(restaurantArrayList, restaurantName);
		if (restInt == -1) {
			throw new _database.DatabaseExceptions("The restaurant does not exists.");
		}
		// Getting the list of judgements
		return restaurantArrayList.get(restInt).judgement;
	}
	//endregion

	/**
	 * @param name The name of the restaurant
	 * @return A list of restaurants with the same name
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	//region SEARCH
	public static List<Restaurant> getRestaurantByName(String name) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Building the list of restaurants based on name
		List<Restaurant> result = new ArrayList<>();
		for (Restaurant restaurant : restaurantArrayList) {
			if (restaurant.name.equals(name)) result.add(restaurant);
		}
		return result;
	}

	/**
	 * @param city The city of the restaurant
	 * @return A list of restaurants with the same city
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Restaurant> getRestaurantByCity(String city) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Building the list of restaurants based on city
		List<Restaurant> result = new ArrayList<>();
		for (Restaurant restaurant : restaurantArrayList) {
			if (restaurant.city.equals(city)) result.add(restaurant);
		}
		return result;
	}

	/**
	 * @param resType The custom type of restaurant
	 * @return A list of restaurants with the same custom type
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Restaurant> getRestaurantByCategory(Restaurant.types resType) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Building the list of restaurants based on typology
		List<Restaurant> result = new ArrayList<>();
		for (Restaurant restaurant : restaurantArrayList) {
			if (restaurant.type.equals(resType)) result.add(restaurant);
		}
		return result;
	}

	/**
	 * @param city    The city of restaurant
	 * @param resType The custom type of restaurant
	 * @return A list of restaurants with the same data
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Restaurant> getRestaurantByCityAndType(String city, Restaurant.types resType) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Building the list of restaurants based on city and typology
		List<Restaurant> result = new ArrayList<>();
		for (Restaurant restaurant : restaurantArrayList) {
			if (restaurant.city.equals(city) && restaurant.type.equals(resType)) result.add(restaurant);
		}
		return result;
	}

	public static List<Restaurant> getRestaurantByOwner(String owner) throws IOException, ClassNotFoundException {
		// Getting the list of restaurants
		List<Restaurant> restaurantArrayList = getRestaurants();
		// Building the list of restaurants based on owner
		List<Restaurant> result = new ArrayList<>();
		for (Restaurant restaurant : restaurantArrayList) {
			if(restaurant.owner.equals(owner)) result.add(restaurant);
		}
		return result;
	}

	/**
	 * @param restaurants A list of restaurants
	 * @param index       The index of the array
	 * @return A single restaurant
	 */
	public static Restaurant getRestaurantByIndex(ArrayList<Restaurant> restaurants, int index) {
		return restaurants.get(index);
	}
	//endregion

	//region HELPER FUNCT
	/*private static ArrayList<?> readObjectFromFile(String filename) {
		// Reads the file and returns all entries in a list
		AtomicReference<ArrayList<?>> persistedEntries = new AtomicReference<>();
		try {
			init();
			FileInputStream fileIn = new FileInputStream(filename);
			if (fileIn.available() > 0) {
				ObjectInputStream objIn = new ObjectInputStream(fileIn);
				System.out.println("FILEOBJ: "+objIn);
				var obj = objIn.readObject();
				if (obj == null) {
					obj = new ArrayList();
				}
				persistedEntries.set((ArrayList<?>) obj);
				objIn.close();
			}
			return new ArrayList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return persistedEntries.get();
	}*/

	public static Object ReadObjectFromFile(String filepath) {
		try {
			FileInputStream fileIn = new FileInputStream(filepath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			Object obj = objectIn.readObject();
			System.out.println("The Object has been read from the file");
			objectIn.close();
			return obj;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * @param restList The list of restaurants
	 * @param name     The name of the restaurant
	 * @return The index of the position of the restaurant in the array
	 */
	private static int getIndexByRestaurantName(List<Restaurant> restList, String name) {
		// Get the index of an element in an array based on specific propert
		for (int index = 0; index < restList.size(); index++) {
			Restaurant restaurant = restList.get(index);
			if (restaurant.name.equals(name)) {
				return index;
			}
		}
		return -1; // Element not found
	}

	/**
	 * @param restList The list of restaurants
	 * @param city     the city
	 * @return An index of the first restaurant with the same city
	 */
	private static int getIndexByRestaurantCity(List<Restaurant> restList, String city) {
		for (int index = 0; index < restList.size(); index++) {
			Restaurant restaurant = restList.get(index);
			if (restaurant.city.equals(city)) {
				return index;
			}
		}
		return -1;  // Element not found
	}

	/**
	 * @param customerList The list of customers
	 * @param nickname     The nickname of the customer
	 * @return The first customer with the same nickname
	 */
	private static int getIndexByCustomerNickname(List<Customer> customerList, String nickname) {
		for (int index = 0; index < customerList.size(); index++) {
			Customer customer = customerList.get(index);
			if (customer.nickname.equals(nickname)) {
				return index;
			}
		}
		return -1; // Element not found
	}

	/**
	 * Custom enum struct to handle the recordType of the database. (Used for debug only)
	 */
	// RecordType is the type of record that will be inserted into the db
	public enum recordType {
		CLIENTE,
		RECENSIONE,
		RISTORANTE
	}

	/**
	 * Enum of data_types used for debug messages in the database.
	 */
	// This types are typically used to insert debug messages into the db
	public enum data_types {
		CLIENTS_DATA,
		INFO,
		LOGGING,
		RESTAURANT_DATA
	}
	//endregion

	//region REGEX
	public static boolean regexStandard(String text) { return text.matches("^[a-zA-Z]+(([' ][a-zA-Z ])?[a-zA-Zàèéìòù]*)*$"); } // Regular phrase

	public static boolean regexProvince(String text) { return text.toUpperCase().matches("^[A-Z]{2}$"); } // uppercase letters {2}

	public static boolean regexEmail(String text) { return text.matches("^([a-z0-9]+[\\.]?)+@[a-z0-9]+\\.[a-z0-9]{2,3}$"); } // (lowercase letters or number + .?) more times + @ + letters + . + lower letters {2,3}

	public static boolean regexNickname(String text) { return text.matches("^[a-z0-9_-]{3,15}$"); } // lowercase letters or number or _ or - {3,15}

	public static boolean regexPassword(String text) { return text.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[\\.-_^*#!$&]).{8,15}$"); } // Must insert, at least: upper/lower -case letter, number, special char {8,15}

	public static boolean regexNumber(String text, String quantifier) { return text.matches("^[0-9]" + quantifier + "$"); } // numbers (quantifier set the length)

	public static boolean regexPhone(String text) { return text.matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$"); } // Regular italian phone number

	public static boolean regexURL(String text) { return text.matches("^(www\\.)?[a-z0-9]+([\\.]?[a-z0-9]+){1,2}/?$"); }  // www.? + lowercase letters or number + (.? + lowercase letters or number) {1,2} + /?
	//endregion
}