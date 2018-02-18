package be.ucll.runetracker.database;

public class DataPointDatabaseFactory {
    private DataPointDatabaseFactory() {
    }

    public static DataPointDatabase create() {
        return new DataPointDatabaseStub();
    }
}
