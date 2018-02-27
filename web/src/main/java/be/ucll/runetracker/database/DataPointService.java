package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.DataPoint;
import be.ucll.runetracker.domain.User;

import java.util.List;

public class DataPointService {
    private UserDatabase userDatabase;
    private DataPointDatabase dataPointDatabase;

    public DataPointService() {

    }

    public DataPointService(UserDatabase userDatabase, DataPointDatabase dataPointDatabase) {
        setUserDatabase(userDatabase);
        setDataPointDatabase(dataPointDatabase);
    }

    public UserDatabase getUserDatabase() {
        return userDatabase;
    }

    public void setUserDatabase(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public DataPointDatabase getDataPointDatabase() {
        return dataPointDatabase;
    }

    public void setDataPointDatabase(DataPointDatabase dataPointDatabase) {
        this.dataPointDatabase = dataPointDatabase;
    }


    public List<User> getAllUsers() {
        return getUserDatabase().all();
    }

    public List<DataPoint> getAllDatapoints() {
        return getDataPointDatabase().all();
    }
}
