package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.RunescapeUser;

import javax.persistence.*;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class RunescapeUserDatabaseJPA implements RunescapeUserDatabase {
    private final EntityManager entityManager;

    public RunescapeUserDatabaseJPA() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(
                "RuneTrackerPU"
        );
        entityManager = factory.createEntityManager();
    }

    @Override
    public void add(RunescapeUser RunescapeUser) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(RunescapeUser);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void addAll(Collection<RunescapeUser> users) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            for (RunescapeUser RunescapeUser : users) {
                entityManager.persist(RunescapeUser);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
        }
    }

    @Override
    public void delete(RunescapeUser RunescapeUser) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.remove(RunescapeUser);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Optional<RunescapeUser> get(int id) {
        return Optional.ofNullable(entityManager.find(RunescapeUser.class, id));
    }

    @Override
    public Stream<RunescapeUser> all() {
        return entityManager.createQuery("SELECT u FROM RunescapeUser u", RunescapeUser.class).getResultStream();
    }

    @Override
    public void update(RunescapeUser user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.merge(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
