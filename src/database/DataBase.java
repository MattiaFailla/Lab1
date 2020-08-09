package database;

import java.io.*;

public class DataBase {
    private static final String filename = "./database.db";

    public enum data_types {
        INFO,
        LOGGING,
        USER_DATA,
        INTERNAL_DATA
    }

    public static Boolean init() throws IOException {
        // Initialize the database
        // Creating database if not exists
        File db_ristoratori = new File(filename);
        // Forcing the os to create the files
        Boolean created = db_ristoratori.createNewFile();
        return created;
    }

    public static void write(Integer id, data_types type, String content) throws IOException {
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

    public static void read(Integer id) throws IOException {
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
