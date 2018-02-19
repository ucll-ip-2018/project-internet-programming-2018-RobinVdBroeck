package be.ucll.runetracker;

import be.ucll.runetracker.database.DataPointDatabase;
import be.ucll.runetracker.database.DataPointDatabaseFactory;
import be.ucll.runetracker.domain.DataPoint;

import java.util.List;

public class DataPointService {
    DataPointDatabase dataPointDatabase;

    public DataPointService() {
        dataPointDatabase = DataPointDatabaseFactory.create();
    }

    public List<DataPoint> getAll() {
        return dataPointDatabase.all();
    }
}
