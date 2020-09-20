package _database;

/**
 * This class is responsible for managing custom database errors.
 * DatabaseException is a function called when the result of a search or a file is empty.
 */
public class DatabaseExceptions extends Throwable {

    private static final long serialVersionUID = 8534172284432314098L;

    public DatabaseExceptions(String message) {
        super(message);
    }
}
