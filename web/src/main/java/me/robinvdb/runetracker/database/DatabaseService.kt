package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.DataPoint
import me.robinvdb.runetracker.domain.RunescapeUser
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.Optional
import java.util.stream.Collectors
import java.util.stream.Stream

class DatabaseService(private val runescapeUserDatabase: RunescapeUserDatabase, private val dataPointDatabase: DataPointDatabase) {

    val allUsers: Stream<RunescapeUser>
        get() {
            logger.debug("Getting all users")
            return runescapeUserDatabase.all()
        }

    val allDatapoints: Stream<DataPoint>
        get() {
            logger.debug("Getting all datapoints")
            return dataPointDatabase.all()
        }

    // USERS
    fun getUser(id: Int): Optional<RunescapeUser> {
        logger.info("Getting user with id: $id")
        val user = runescapeUserDatabase.get(id)
        if (user.isPresent) {
            val debugString = datapointsToString(user.get().dataPoints)
            logger.info(debugString)
        } else {
            logger.info("Could not find user with id: $id")
        }

        return user
    }

    private fun datapointsToString(dataPoints: Collection<DataPoint>): String {
        logger.info(dataPoints.size.toString(10))

        if (dataPoints.isEmpty()) {
            return "No datapoints available"
        } else {
            return dataPoints.joinToString("\n", "Datapoints: \n", "") { it.toString() }
        }
    }

    fun addUser(user: RunescapeUser) {
        logger.info("Adding user")
        runescapeUserDatabase.add(user)
    }

    fun deleteUser(user: RunescapeUser) {
        logger.info("Deleting user with id: " + user.id!!)
        runescapeUserDatabase.delete(user)
    }

    fun updateUser(user: RunescapeUser) {
        logger.info("Updating user with id: " + user.id!!)
        runescapeUserDatabase.update(user)
    }

    // DATAPOINTS
    fun getDatapoint(id: Int): Optional<DataPoint> {
        logger.debug("Getting datapoint with id: $id")
        return dataPointDatabase.get(id)
    }

    fun addDatapoint(dataPoint: DataPoint) {
        logger.info("Adding datapoint")
        dataPointDatabase.add(dataPoint)
        val user = dataPoint.user
        user!!.addDataPoint(dataPoint)
        runescapeUserDatabase.update(user)
    }

    fun deleteDatapoint(dataPoint: DataPoint) {
        logger.info("Deleting datapoint with id: " + dataPoint.id!!)
        dataPointDatabase.delete(dataPoint)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DatabaseService::class.java)
    }
}
