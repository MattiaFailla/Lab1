import ristoratori.DataBase;
import ristoratori.login.Login;

import java.io.IOException;

public class StartApp {
    public static void main(String[] args) throws IOException {
        System.out.println("App started");

        DataBase.init();

        DataBase.write(1, DataBase.data_types.INFO, "App started");

        DataBase.read(1);

        // Starting the ui
        Login.main(args);

    }
}
