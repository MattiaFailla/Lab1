package database;

import database.objects.Client;
import database.objects.Judgement;
import database.objects.Restaurant;

import java.io.*;
import java.util.ArrayList;

/**
 * DataBase is the class responsible for correct communication with the
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

    private static final String debug_db = "./debug.txt";
    private static final String restaurant_db = "./EatAdvisor.dati";
    private static final String client_db = "./Utenti.dati";

    public static Boolean init() {
        // Initialize the database
        // Creating database if not exists
        File db_ristoratori = new File(restaurant_db);
        File db_clienti = new File(client_db);
        // Forcing the os to create the files
        try {
            boolean success = true;
            if (!db_ristoratori.exists()) {
                success = db_clienti.createNewFile();
            }
            if (!db_clienti.exists()) {
                success = db_ristoratori.createNewFile();
            }
            return success;
        } catch (IOException ignored) {
            return false;
        }
    }

    /*      WRITERS       */
    public static void insertClient(String name, String surname, String cityName, String province, String emailAddress, String nickName, String password) {
        Client cli = new Client(name, surname, cityName, province, emailAddress, nickName, password);
        // Saving the Customer
        try {
            // Before saving the new customer we need to extract the old customers
            ArrayList entries = readFile(client_db);
            entries.add(cli);
            System.out.println(entries);

            File file = new File(client_db);
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(entries);
            o.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Utente inserito con successo.");
    }

    public static void insertRestaurant(String name, String qualifier, String streetName, Integer civicNumber, String city, String province, Integer CAP, Integer phoneNumber, String url, Restaurant.types type) {
        Restaurant res = new Restaurant(name, qualifier, streetName, civicNumber, city, province, CAP, phoneNumber, url, type);
        try {
            File file = new File(restaurant_db);
            FileOutputStream f = new FileOutputStream(file, true);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(res);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception. File not accessible?");
        }
    }

    public static void insertJudgment(String username, String restaurantName, Integer rating, String judgement) {
        Judgement jud = new Judgement(username, restaurantName, rating, judgement);
        try {
            File file = new File(restaurant_db);
            FileOutputStream f = new FileOutputStream(file, true);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(jud);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Exception. File not accessible?");
        }
    }

    /*      GETTER       */
    public static Object[] getClients() throws IOException, ClassNotFoundException {
        // Returning every client in the file
        File file = new File(client_db);
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Reading objects from file
        ArrayList<Client> clients = new ArrayList<>();

        while(true){
            try {
                Object obj = oi.readObject();
                if (obj instanceof ArrayList) {
                    clients = (ArrayList<Client>) obj;
                }
            } catch (EOFException e){
                return clients.toArray();
            }
        }
    }

    public static Object[] getRestaurants() throws IOException, ClassNotFoundException {
        // Returning every restaurant in the file
        File file = new File(restaurant_db);
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Reading objects from file
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        while(true){
            try {
                Object obj = oi.readObject();
                if (obj instanceof Restaurant) {
                    restaurants.add((Restaurant) obj);
                }
            } catch (EOFException e){
                return restaurants.toArray();
            }
        }
    }

    public static Object[] getJudgments() throws IOException, ClassNotFoundException {
        // Returning every jud in the file
        File file = new File(restaurant_db);
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Reading objects from file
        ArrayList<Judgement> judgements = new ArrayList<>();

        while(true){
            try {
                Object obj = oi.readObject();
                if (obj instanceof Judgement) {
                    judgements.add((Judgement) obj);
                }
            } catch (EOFException e){
                return judgements.toArray();
            }
        }
    }

    // HELPER FUNCT
    //Reads the file and returns all entries in a list
    public static ArrayList<?> readFile (String filename)
    {
        ArrayList<?> persistedEntries = new ArrayList<>();

        FileInputStream fileIn;
        ObjectInputStream objIn;
        try
        {
            fileIn = new FileInputStream(filename);
            objIn = new ObjectInputStream(fileIn);
            persistedEntries = (ArrayList<?>) objIn.readObject();
            objIn.close();
        }
        catch(IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }

        return persistedEntries;
    }

    // Normal write function for debug purposes
    public static void write(Integer id, data_types type, String content) throws IOException {
        // Appending to file the content at id
        FileOutputStream fos = new FileOutputStream(debug_db, true);

        String payload =
                "# DATA" + "\n" +
                        "id:" + id.toString() + "\n" +
                        "type:" + type + "\n" +
                        "content:" + content + "\n";

        fos.write(payload.getBytes());
        fos.close();
    }

    public enum data_types {
        INFO,
        LOGGING,
        RESTAURANT_DATA,
        CLIENTS_DATA
    }

    public enum recordType {
        RISTORANTE,
        CLIENTE,
        RECENSIONE
    }
}
