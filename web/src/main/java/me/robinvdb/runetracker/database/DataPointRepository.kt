package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.DataPoint
import org.springframework.data.repository.CrudRepository

interface DataPointRepository : CrudRepository<DataPoint, Int>
