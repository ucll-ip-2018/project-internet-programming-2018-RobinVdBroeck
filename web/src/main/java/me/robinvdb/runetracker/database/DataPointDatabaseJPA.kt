package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.DataPoint
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.persistence.*
import java.util.Optional
import java.util.stream.Stream

class DataPointDatabaseJPA : DataPointDatabase {
    private val entityManager: EntityManager

    init {
        val factory = Persistence.createEntityManagerFactory(
                "RuneTrackerPU"
        )
        entityManager = factory.createEntityManager()
    }

    override fun add(dataPoint: DataPoint) {
        val transaction = entityManager.transaction
        transaction.begin()
        try {
            entityManager.persist(dataPoint)
            transaction.commit()
        } catch (e: RollbackException) {
            logger.error(e.toString())
            transaction.rollback()
        }

    }

    override fun addAll(dataPoints: Collection<DataPoint>) {
        val transaction = entityManager.transaction
        transaction.begin()
        try {
            entityManager.flushMode = FlushModeType.COMMIT
            for (dataPoint in dataPoints) {
                entityManager.persist(dataPoint)
            }
            transaction.commit()
        } catch (e: Exception) {
            logger.error(e.toString())
            transaction.rollback()
        } finally {
            entityManager.flushMode = FlushModeType.AUTO
        }
    }

    override fun delete(dataPoint: DataPoint) {
        val transaction = entityManager.transaction
        transaction.begin()
        try {
            entityManager.remove(dataPoint)
            transaction.commit()
        } catch (e: Exception) {
            logger.error(e.toString())
            transaction.rollback()
        }

    }

    override fun get(id: Int?): Optional<DataPoint> {
        return Optional.ofNullable(entityManager.find(DataPoint::class.java, id))
    }

    override fun all(): Stream<DataPoint> {
        return entityManager.createQuery("SELECT datapoint FROM DataPoint datapoint", DataPoint::class.java).resultStream
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DataPointDatabaseJPA::class.java)
    }
}
