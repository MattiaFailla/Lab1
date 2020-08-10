package database;

import java.io.*;

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
public class DataBase {

    private static final String filename = "./database.db";

    public enum data_types {
        INFO,
        LOGGING,
        RESTAURANT_DATA,
        CLIENTS_DATA
    }

    public static Boolean init() throws IOException {
        // Initialize the database
        // Creating database if not exists
        File db_ristoratori = new File(filename);
        // Forcing the os to create the files
        return db_ristoratori.createNewFile();
    }

    public static void write(Integer id, data_types type, String content) throws IOException {

    }

    /*
    public static void write(@NotNull Integer id, data_types type, String content) throws IOException {
>>>>>>> Stashed changes
        // Appending to file the content at id
        FileOutputStream fos = new FileOutputStream(filename, true);

        String payload =
                "# DATA" + "\n" +
                        "id:" + id.toString() + "\n" +
                        "type:" + type + "\n" +
                        "content:" + content + "\n";

        fos.write(payload.getBytes());
        fos.close();
    }

     */

    public static void read(Integer id) throws IOException {
        System.out.println(id);
        FileReader fr = new FileReader(filename);
        StreamTokenizer st = new StreamTokenizer(fr);
        while(st.nextToken() != StreamTokenizer.TT_EOF) {
            if(st.ttype == StreamTokenizer.TT_NUMBER) {
                System.out.println("Number: "+st.nval);
            } else if(st.ttype == StreamTokenizer.TT_WORD) {
                System.out.println("Word: "+st.sval);
            }else if(st.ttype == StreamTokenizer.TT_EOL) {
                System.out.println("--End of Line--");
            }
        }
    }


}
