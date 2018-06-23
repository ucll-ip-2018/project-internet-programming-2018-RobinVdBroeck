package me.robinvdb.runetracker.database;

import me.robinvdb.runetracker.domain.DataPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class DataPointDatabaseJPA implements DataPointDatabase {
    private EntityManager entityManager;
    private final static Logger logger = LoggerFactory.getLogger(DataPointDatabaseJPA.class);

    public DataPointDatabaseJPA() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(
                "RuneTrackerPU"
        );
        entityManager = factory.createEntityManager();
    }

    @Override
    public void add(DataPoint dataPoint) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(dataPoint);
            transaction.commit();
        } catch (RollbackException e) {
            logger.error(e.toString());
            transaction.rollback();
        }
    }

    @Override
    public void addAll(Collection<DataPoint> dataPoints) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            for (DataPoint dataPoint : dataPoints) {
                entityManager.persist(dataPoint);
            }
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.toString());
            transaction.rollback();
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
        }
    }

    @Override
    public void delete(DataPoint dataPoint) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.remove(dataPoint);
            transaction.commit();
        } catch (Exception e) {
            logger.error(e.toString());
            transaction.rollback();
        }
    }

    @Override
    public Optional<DataPoint> get(Integer id) {
        return Optional.ofNullable(entityManager.find(DataPoint.class, id));
    }

    @Override
    public Stream<DataPoint> all() {
        return entityManager.createQuery("SELECT datapoint FROM DataPoint datapoint", DataPoint.class).getResultStream();
    }
}
