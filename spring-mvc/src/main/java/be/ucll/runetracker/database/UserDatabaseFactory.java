package be.ucll.runetracker.database;

public class UserDatabaseFactory {
    private UserDatabaseFactory() {
    }

    public static UserDatabase create() {
        return new UserDatabaseStub();
    }
}
