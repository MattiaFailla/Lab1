package _database;

/**
 * This class is responsible for managing custom database errors
 */
public class DatabaseExceptions extends Throwable {

    private static final long serialVersionUID = 8534172284432314098L;

    public DatabaseExceptions(String message) {
        super(message);
    }
}
