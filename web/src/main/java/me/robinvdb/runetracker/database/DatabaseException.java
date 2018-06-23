package me.robinvdb.runetracker.database;

public class DatabaseException extends RuntimeException {
    public DatabaseException() {
        super();
    }

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public DatabaseException(Throwable throwable) {
        super(throwable);
    }
}
