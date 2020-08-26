package database;

import database.objects.Client;
import database.objects.Judgement;
import database.objects.Restaurant;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Database is the class responsible for correct communication with the
 * database.
 * <p>
 * The data are saved in the following format:
 * --- TODO ---
 * <p>
 * In this class you can find helper functions to find, insert, update and delete data
 * from the database. Since this class is in common between clienti and ristoratori our
 * priority is to make a powerful yet simple helper class to achieve a single point of
 * junction between these two applications.
 */
public class Database {
    private static final String restaurant_db = "./EatAdvisor.dati";
    private static final String client_db = "./Utenti.dati";

    public static Boolean init() {
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

    /*      WRITERS       */
    public static void insertClient(String name, String surname, String city, String province, String email, String nickname, String password) {
        Client clt = new Client(name, surname, city, province, email, nickname, password);
        // Saving the Customer
        try {
            // Before saving the new customer we need to extract the old customers
            ArrayList<Client> entries = (ArrayList<Client>) readFile(client_db);
            entries.add(clt);

            File file = new File(client_db);
            FileOutputStream fOut = new FileOutputStream(file);
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);

            /*
            FileWriter fileWriter = new FileWriter(client_db);
            fileWriter.append(clt.toString());
            */

            oOut.writeObject(entries);
            oOut.close();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Utente inserito con successo.");
    }

    public static void insertRestaurant(String name, Integer phoneNumber, String qualifier, String street, Integer civicNumber, String city, String province, Integer CAP, String url, Restaurant.types type) {
        Restaurant rst = new Restaurant(name, qualifier, street, civicNumber, city, province, CAP, phoneNumber, url, type);
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

    public static void insertJudgment(String username, String restaurantName, Integer rating, String judgement) {
        Judgement jdg = new Judgement(username, restaurantName, rating, judgement);
        try {
            // Before saving the new restaurant we need to extract the old restaurant data
            ArrayList<Judgement> entries = (ArrayList<Judgement>) readFile(restaurant_db);
            entries.add(jdg);

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

    /*      GETTER       */
    public static Boolean checkClient(String fieldData) throws IOException, ClassNotFoundException {
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

    public static List<Judgement> getJudgments() throws IOException, ClassNotFoundException {
        // Returning every judgement in the file
        File file = new File(restaurant_db);
        FileInputStream fIn = new FileInputStream(file);
        ObjectInputStream oIn = new ObjectInputStream(fIn);
        
        // Getting data
        Object data = oIn.readObject();
        List<?> list = new ArrayList<>();
        if (data instanceof List) {
            list = ((List<?>) data).stream()
                    .filter(x -> x instanceof Judgement)
                    .map(x -> (Judgement) x)
                    .collect(Collectors.toList());
        }
        return (List<Judgement>) list;
    }

    // HELPER FUNCT
    //Reads the file and returns all entries in a list
    public static ArrayList<?> readFile(String filename) {
        AtomicReference<ArrayList<?>> persistedEntries = new AtomicReference<ArrayList<?>>();
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

    /**
     * This types are typically used to insert debug messages into the database
     */
    public enum data_types {
        CLIENTS_DATA,
        INFO,
        LOGGING,
        RESTAURANT_DATA

    }

    /**
     * RecordType is the type of record that will be inserted into the database
     */
    public enum recordType {
        CLIENTE,
        RECENSIONE,
        RISTORANTE

    }
}
