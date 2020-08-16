package database;

import database.objects.Client;
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

    private static final String restaurant_db = "./EatAdvisor.dati";
    private static final String client_db = "./Utenti.dati";

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

    public static Boolean init() throws IOException {
        // Initialize the database
        // Creating database if not exists
        File db_ristoratori = new File(restaurant_db);
        File db_clienti = new File(client_db);
        // Forcing the os to create the files
        try {
            if (!db_ristoratori.exists()) {
                db_clienti.createNewFile();
            }
            if(!db_clienti.exists()) {
                db_ristoratori.createNewFile();
            }
            return true;
        } catch (IOException ignored){
            return false;
        }
    }

    /*      WRITERS       */
    public static void insertClient(String name, String surname, String cityName, String province, String emailAddress, String nickName, String password){
        Client cli = new Client(name, surname, cityName, province, emailAddress, nickName, password);
        // Saving the Customer
        try {
            File file = new File(client_db);
            FileOutputStream f = new FileOutputStream(file, true);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(cli);
            o.close();
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /*      GETTER       */
    public static Object[] getClients() throws IOException, ClassNotFoundException {
        // Returning every restaurant in the file
        File file = new File(restaurant_db);
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Reading objects from file
        ArrayList<Client> clients = new ArrayList<>();

        while(oi.readObject() != null){
            clients.add((Client) oi.readObject());
        }
        oi.close();
        fi.close();
        return clients.toArray();
    }

    public static Object[] getRestaurants() throws IOException, ClassNotFoundException {
        // Returning every restaurant in the file
        File file = new File(restaurant_db);
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream oi = new ObjectInputStream(fi);

        // Reading objects from file
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        while(oi.readObject() != null){
            restaurants.add((Restaurant) oi.readObject());
        }
        oi.close();
        fi.close();
        return restaurants.toArray();
    }


    public static void write(Integer id, data_types type, String content) throws IOException {
        // Appending to file the content at id
        FileOutputStream fos = new FileOutputStream(restaurant_db, true);

        String payload =
                "# DATA" + "\n" +
                        "id:" + id.toString() + "\n" +
                        "type:" + type + "\n" +
                        "content:" + content + "\n";

        fos.write(payload.getBytes());
        fos.close();
    }

    public static void read(Integer id) throws IOException {
        System.out.println(id);
        FileReader fr = new FileReader(restaurant_db);
        StreamTokenizer st = new StreamTokenizer(fr);
        while(st.nextToken() != StreamTokenizer.TT_EOF) {
            if(st.ttype == StreamTokenizer.TT_NUMBER) {
                System.out.println("Number: "+st.nval);
            } else if(st.ttype == StreamTokenizer.TT_WORD) {
                System.out.println("Word: "+st.sval);
            } else if(st.ttype == StreamTokenizer.TT_EOL) {
                System.out.println("--End of Line--");
            }
        }
    }
}
