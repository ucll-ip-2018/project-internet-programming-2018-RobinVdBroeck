package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.DataPoint
import java.util.Optional
import java.util.stream.Stream

interface DataPointDatabase {
    fun add(dataPoint: DataPoint)
    fun addAll(dataPoints: Collection<DataPoint>)
    fun delete(dataPoint: DataPoint)
    operator fun get(id: Int?): Optional<DataPoint>
    fun all(): Stream<DataPoint>
}
