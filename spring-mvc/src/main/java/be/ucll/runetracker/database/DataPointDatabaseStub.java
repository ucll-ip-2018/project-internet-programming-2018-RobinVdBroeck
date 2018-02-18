package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.DataPoint;

import java.util.*;

class DataPointDatabaseStub implements DataPointDatabase {
    private Map<Integer, DataPoint> dataPoints;

    DataPointDatabaseStub() {
        dataPoints = new HashMap<>();
    }

    @Override
    public void add(DataPoint dataPoint) {
        if(dataPoints.containsKey(dataPoint.getId())) {
            throw new DatabaseException("DataPoint already exists");
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
    public List<DataPoint> all() {
        return new ArrayList<>(dataPoints.values());
    }
}
