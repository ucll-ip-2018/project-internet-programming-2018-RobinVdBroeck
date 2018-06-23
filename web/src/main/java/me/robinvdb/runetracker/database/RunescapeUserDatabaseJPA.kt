package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.RunescapeUser

import javax.persistence.*
import java.util.Optional
import java.util.stream.Stream

class RunescapeUserDatabaseJPA : RunescapeUserDatabase {
    private val entityManager: EntityManager

    init {
        val factory = Persistence.createEntityManagerFactory(
                "RuneTrackerPU"
        )
        entityManager = factory.createEntityManager()
    }

    override fun add(user: RunescapeUser) {
        val transaction = entityManager.transaction
        transaction.begin()
        try {
            entityManager.persist(user)
            transaction.commit()
        } catch (e: Exception) {
            transaction.rollback()
        }

    }

    override fun addAll(users: Collection<RunescapeUser>) {
        val transaction = entityManager.transaction
        transaction.begin()
        try {
            entityManager.flushMode = FlushModeType.COMMIT
            for (RunescapeUser in users) {
                entityManager.persist(RunescapeUser)
            }
            transaction.commit()
        } catch (e: Exception) {
            transaction.rollback()
        } finally {
            entityManager.flushMode = FlushModeType.AUTO
        }
    }

    override fun delete(user: RunescapeUser) {
        val transaction = entityManager.transaction
        transaction.begin()
        try {
            entityManager.remove(user)
            transaction.commit()
        } catch (e: Exception) {
            transaction.rollback()
        }

    }

    override fun get(id: Int): Optional<RunescapeUser> {
        return Optional.ofNullable(entityManager.find(RunescapeUser::class.java, id))
    }

    override fun all(): Stream<RunescapeUser> {
        return entityManager.createQuery("SELECT u FROM RunescapeUser u", RunescapeUser::class.java).resultStream
    }

    override fun update(user: RunescapeUser) {
        val transaction = entityManager.transaction
        transaction.begin()
        try {
            entityManager.merge(user)
            transaction.commit()
        } catch (e: Exception) {
            transaction.rollback()
        }

    }
}
