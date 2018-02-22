package be.ucll.runetracker.database;

public class UserDatabaseFactory {
    private UserDatabaseFactory() {
    }

    public static UserDatabase create(DatabaseType type) {
        switch (type) {
            case STUB:
                return new UserDatabaseStub();
            case RELATIONAL:
                throw new DatabaseException("Not yet implemented");
            default:
                throw new IllegalStateException("Unknown database type");
        }
    }
}
