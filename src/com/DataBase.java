package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataBase {
    private static final String filename = "./database.db";

    public enum data_types {
        INFO,
        LOGGING,
        USER_DATA,
        INTERNAL_DATA
    }

    public static void init() throws IOException {
        // Initialize the database
        // Creating database if not exists
        File db_ristoratori = new File(filename);
        // Forcing the os to create the files
        db_ristoratori.createNewFile();
    }

    public static void write(Integer id, data_types type, String content) throws IOException {
        // Appending to file the content at id
        FileOutputStream fos = new FileOutputStream(filename, true);
        String payload = "# DATA" + "\n" +
                "id:" + id.toString() + "\n" +
                "type:" + type + "\n" +
                "content:" + content + "\n";
        fos.write(payload.getBytes());
        fos.close();
    }


}
