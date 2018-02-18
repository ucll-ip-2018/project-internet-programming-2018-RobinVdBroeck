package be.ucll.runetracker.database;

public class UserDatabaseFactory {
    public static UserDatabase create() {
        return new UserDatabaseStub();
    }
}
