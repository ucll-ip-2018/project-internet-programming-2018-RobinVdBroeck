package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.DataPoint;
import be.ucll.runetracker.domain.RunescapeUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    private final RunescapeUserDatabase runescapeUserDatabase;
    private final DataPointDatabase dataPointDatabase;

    public DatabaseService(RunescapeUserDatabase runescapeUserDatabase, DataPointDatabase dataPointDatabase) {
        this.runescapeUserDatabase = runescapeUserDatabase;
        this.dataPointDatabase = dataPointDatabase;
    }

    // USERS
    public Optional<RunescapeUser> getUser(int id) {
        logger.info("Getting user with id: " + id);
        Optional<RunescapeUser> user = runescapeUserDatabase.get(id);
        if (user.isPresent()) {
            String debugString = datapointsToString(user.get().getDataPoints());
            logger.info(debugString);
        } else {
            logger.info("Could not find user with id: " + id);
        }

        return user;
    }

    private String datapointsToString(Collection<DataPoint> dataPoints) {
        logger.info("" + dataPoints.size());

        if (dataPoints.isEmpty()) {
            return "No datapoints available";
        }

        return dataPoints
                .stream()
                .map(DataPoint::toString)
                .collect(Collectors.joining("\n", "Datapoints: \n", ""));
    }

    public Stream<RunescapeUser> getAllUsers() {
        logger.debug("Getting all users");
        return runescapeUserDatabase.all();
    }

    public void addUser(RunescapeUser user) {
        logger.info("Adding user");
        runescapeUserDatabase.add(user);
    }

    public void deleteUser(RunescapeUser user) {
        logger.info("Deleting user with id: " + user.getId());
        runescapeUserDatabase.delete(user);
    }

    public void updateUser(RunescapeUser user) {
        logger.info("Updating user with id: " + user.getId());
        runescapeUserDatabase.update(user);
    }

    // DATAPOINTS
    public Optional<DataPoint> getDatapoint(int id) {
        logger.debug("Getting datapoint with id: " + id);
        return dataPointDatabase.get(id);
    }

    public Stream<DataPoint> getAllDatapoints() {
        logger.debug("Getting all datapoints");
        return dataPointDatabase.all();
    }

    public void addDatapoint(DataPoint dataPoint) {
        logger.info("Adding datapoint");
        dataPointDatabase.add(dataPoint);
    }

    public void deleteDatapoint(DataPoint dataPoint) {
        logger.info("Deleting datapoint with id: " + dataPoint.getId());
        dataPointDatabase.delete(dataPoint);
    }
}
