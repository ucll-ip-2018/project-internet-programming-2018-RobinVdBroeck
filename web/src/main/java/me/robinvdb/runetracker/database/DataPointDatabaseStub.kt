package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.DataPoint

import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Stream

class DataPointDatabaseStub : DataPointDatabase {
    private val dataPoints: MutableMap<Int, DataPoint>
    private val counter: AtomicInteger

    init {
        dataPoints = HashMap()
        counter = AtomicInteger()
    }

    override fun add(dataPoint: DataPoint) {
        if (dataPoint.id == null) {
            dataPoint.id = counter.getAndIncrement()
        }

        if (dataPoints.containsKey(dataPoint.id!!)) {
            throw DatabaseException("DataPoint with id " + dataPoint.id + " already exists")
        }

        dataPoints[dataPoint.id!!] = dataPoint
    }

    override fun addAll(dataPoints: Collection<DataPoint>) {
        dataPoints.forEach { this.add(it) }
    }

    override fun delete(dataPoint: DataPoint) {
        dataPoints.remove(dataPoint.id)
    }

    override fun get(id: Int?): Optional<DataPoint> {
        return Optional.ofNullable(dataPoints[id])
    }

    override fun all(): Stream<DataPoint> {
        return dataPoints.values.stream()
    }
}
