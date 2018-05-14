package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.DataPoint;
import be.ucll.runetracker.domain.RunescapeUser;
import be.ucll.runetracker.domain.Skill;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DatabaseService {
    private RunescapeUserDatabase runescapeUserDatabase;
    private DataPointDatabase dataPointDatabase;

    private DatabaseService() {
    }

    public DatabaseService(RunescapeUserDatabase runescapeUserDatabase, DataPointDatabase dataPointDatabase) {
        setRunescapeUserDatabase(runescapeUserDatabase);
        setDataPointDatabase(dataPointDatabase);
    }

    private RunescapeUserDatabase getRunescapeUserDatabase() {
        return runescapeUserDatabase;
    }

    private void setRunescapeUserDatabase(RunescapeUserDatabase runescapeUserDatabase) {
        this.runescapeUserDatabase = runescapeUserDatabase;
    }

    private DataPointDatabase getDataPointDatabase() {
        return dataPointDatabase;
    }

    private void setDataPointDatabase(DataPointDatabase dataPointDatabase) {
        this.dataPointDatabase = dataPointDatabase;
    }

    // USERS
    public Optional<RunescapeUser> getUser(int id) {
        return runescapeUserDatabase.get(id);
    }

    public Stream<RunescapeUser> getAllUsers() {
        return runescapeUserDatabase.all();
    }

    public void addUser(RunescapeUser user) {
        runescapeUserDatabase.add(user);
    }

    public void deleteUser(RunescapeUser user) {
        runescapeUserDatabase.delete(user);
    }

    public void updateUser(RunescapeUser user) {
        runescapeUserDatabase.update(user);
    }

    // DATAPOINTS
    public Optional<DataPoint> getDatapoint(int id) {
        return dataPointDatabase.get(id);
    }

    public Stream<DataPoint> getAllDatapoints() {
        return dataPointDatabase.all();
    }

    public void addDatapoint(DataPoint dataPoint) {
        dataPointDatabase.add(dataPoint);
    }

    public void deletedDataPoint(DataPoint dataPoint) {
        dataPointDatabase.delete(dataPoint);
    }
}
