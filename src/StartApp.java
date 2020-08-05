import com.DataBase;

import java.io.IOException;

public class StartApp {
    public static void main(String[] args) throws IOException {
        System.out.println("App started");

        DataBase.init();

        // Starting the ui
        com.login.Login.main(args);

    }
}
