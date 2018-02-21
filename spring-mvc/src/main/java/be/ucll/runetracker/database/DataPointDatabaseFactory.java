package be.ucll.runetracker.database;

public class DataPointDatabaseFactory {
    private DataPointDatabaseFactory() {
    }

    public static DataPointDatabase create(DatabaseType type) {
        switch (type) {
            case STUB:
                return new DataPointDatabaseStub();
            case RELATIONAL:
                throw new DatabaseException("Not yet implemented");
            default:
                throw new IllegalStateException("Unknown database type");
        }
    }
}
