package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.DataPoint;

import javax.persistence.*;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class DataPointDatabaseJPA implements DataPointDatabase {
    private EntityManager entityManager;
    private final static Logger logger = Logger.getLogger(DataPointDatabaseJPA.class.toGenericString());

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
            logger.severe(e.toString());
            transaction.rollback();
        }
    }

    @Override
    public void addAll(Collection<DataPoint> dataPoints) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            for(DataPoint dataPoint: dataPoints) {
                entityManager.persist(dataPoint);
            }
            transaction.commit();
        } catch(Exception e) {
            logger.severe(e.toString());
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
        } catch(Exception e) {
            logger.severe(e.toString());
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
