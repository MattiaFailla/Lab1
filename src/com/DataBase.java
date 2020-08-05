package com;

import java.io.File;
import java.io.IOException;

public class DataBase {
    private static final String filename = "database.db";

    public static void init() throws IOException {
        // Initialize the database
        // Creating database if not exists
        File db_ristoratori = new File(filename);
        // Forcing the os to create the files
        db_ristoratori.createNewFile();
    }


}
