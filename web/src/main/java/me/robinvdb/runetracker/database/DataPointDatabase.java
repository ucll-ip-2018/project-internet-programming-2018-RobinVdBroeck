package me.robinvdb.runetracker.database;

import me.robinvdb.runetracker.domain.DataPoint;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface DataPointDatabase {
    void add(DataPoint dataPoint);
    void addAll(Collection<DataPoint> dataPoints);
    void delete(DataPoint dataPoint);
    Optional<DataPoint> get(Integer id);
    Stream<DataPoint> all();
}