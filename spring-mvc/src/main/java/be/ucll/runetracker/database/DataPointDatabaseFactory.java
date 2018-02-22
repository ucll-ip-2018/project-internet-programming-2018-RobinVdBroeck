package be.ucll.runetracker.database;

public class DataPointDatabaseFactory {
    private static DataPointDatabaseStub stub;

    private DataPointDatabaseFactory() {
    }

    public static DataPointDatabase create(DatabaseType type) {
        switch (type) {
            case STUB:
                return getSharedInstance();
            case RELATIONAL:
                throw new DatabaseException("Not yet implemented");
            default:
                throw new IllegalStateException("Unknown database type");
        }
    }

    public synchronized static DataPointDatabaseStub getSharedInstance() {
        if(stub == null) {
            stub = new DataPointDatabaseStub();
        }
        return stub;
    }
}
