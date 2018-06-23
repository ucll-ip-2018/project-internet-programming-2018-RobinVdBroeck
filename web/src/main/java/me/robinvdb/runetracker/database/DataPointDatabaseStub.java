package me.robinvdb.runetracker.database;

import me.robinvdb.runetracker.domain.DataPoint;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class DataPointDatabaseStub implements DataPointDatabase {
    private Map<Integer, DataPoint> dataPoints;
    private final AtomicInteger counter;


    public DataPointDatabaseStub() {
        dataPoints = new HashMap<>();
        counter = new AtomicInteger();
    }

    @Override
    public void add(DataPoint dataPoint) {
        if (dataPoint.getId() == null) {
            dataPoint.setId(counter.getAndIncrement());
        }

        if (dataPoints.containsKey(dataPoint.getId())) {
            throw new DatabaseException("DataPoint with id " + dataPoint.getId() + " already exists");
        }

        dataPoints.put(dataPoint.getId(), dataPoint);
    }

    @Override
    public void addAll(Collection<DataPoint> dataPoints) {
        dataPoints.forEach(this::add);
    }

    @Override
    public void delete(DataPoint dataPoint) {
        dataPoints.remove(dataPoint.getId());
    }

    @Override
    public Optional<DataPoint> get(Integer id) {
        return Optional.ofNullable(dataPoints.get(id));
    }

    @Override
    public Stream<DataPoint> all() {
        return dataPoints.values().stream();
    }
}
