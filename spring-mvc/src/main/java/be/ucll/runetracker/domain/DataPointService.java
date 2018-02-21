package be.ucll.runetracker.domain;

import be.ucll.runetracker.database.*;

import java.util.List;

public class DataPointService {
    private UserDatabase userDatabase;
    private DataPointDatabase dataPointDatabase;

    public DataPointService(DatabaseType type) {
        dataPointDatabase = DataPointDatabaseFactory.create(type);
        userDatabase = UserDatabaseFactory.create(type);
    }

    public List<User> getAllUsers() {
        return userDatabase.all();
    }

    public List<DataPoint> getAllDatapoints() {
        return dataPointDatabase.all();
    }
}
