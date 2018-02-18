package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.DataPoint;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DataPointDatabase {
    void add(DataPoint dataPoint);
    void addAll(Collection<DataPoint> dataPoints);
    void delete(DataPoint dataPoint);
    Optional<DataPoint> get(Integer id);
    List<DataPoint> all();
}
