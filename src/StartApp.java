import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StartApp {
    public static void main(String[] args) throws IOException {
        System.out.println("App started");


        // Creating database if not exists
        File db_ristoratori = new File("./ristoratori.db");
        // Forcing the os to create the files
        db_ristoratori.createNewFile();
        FileOutputStream oFile = new FileOutputStream(db_ristoratori, true);

        // Starting the ui
        com.login.Login.main(args);

    }
}
