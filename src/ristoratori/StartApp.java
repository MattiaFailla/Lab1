package ristoratori;

import database.DataBase;
import ristoratori.login.Login;

import java.io.IOException;

/**
 * StartApp is the entry point for the app ristoratori
 */
public class StartApp {
    /**
     * This is the entry-point for the app ristoratori.
     *
     * @param args The args from the jvm
     */
    public static void main(String[] args) {
        // Starting the app
        // Init the database
        try {
            DataBase.init();
            // Writing some log
            //DataBase.write(1, DataBase.data_types.INFO, "App started");
            // @todo: remove this testing line
            DataBase.read(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("App started");

        // Starting the ui
        Login.main(args);
    }
}
