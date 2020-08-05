import com.DataBase;

import java.io.IOException;
import java.sql.DatabaseMetaData;

public class StartApp {
    public static void main(String[] args) throws IOException {
        System.out.println("App started");

        DataBase.init();

        DataBase.write(1, DataBase.data_types.INFO, "App started");

        // Starting the ui
        com.login.Login.main(args);

    }
}
