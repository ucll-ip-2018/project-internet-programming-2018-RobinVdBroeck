package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.DataPoint;
import be.ucll.runetracker.domain.User;

import java.util.List;

public class DataPointService {
    private UserDatabase userDatabase;
    private DataPointDatabase dataPointDatabase;

    private DataPointService() {

    }

    public DataPointService(UserDatabase userDatabase, DataPointDatabase dataPointDatabase) {
        setUserDatabase(userDatabase);
        setDataPointDatabase(dataPointDatabase);
    }

    private UserDatabase getUserDatabase() {
        return userDatabase;
    }

    private void setUserDatabase(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    private DataPointDatabase getDataPointDatabase() {
        return dataPointDatabase;
    }

    private void setDataPointDatabase(DataPointDatabase dataPointDatabase) {
        this.dataPointDatabase = dataPointDatabase;
    }


    public List<User> getAllUsers() {
        return userDatabase.all();
    }

    public void addUser(User user) {
        userDatabase.add(user);
    }

    public List<DataPoint> getAllDatapoints() {
        return dataPointDatabase.all();
    }
}
