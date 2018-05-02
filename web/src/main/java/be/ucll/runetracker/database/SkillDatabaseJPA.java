package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.Skill;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class SkillDatabaseJPA implements SkillDatabase {
    private final EntityManager entityManager;

    public SkillDatabaseJPA() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(
                "RuneTrackerPU"
        );
        entityManager = factory.createEntityManager();
    }
    @Override
    public void add(Skill skill) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(skill);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void addAll(Collection<Skill> skills) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.setFlushMode(FlushModeType.COMMIT);
            for (Skill skill: skills) {
                entityManager.persist(skill);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.setFlushMode(FlushModeType.AUTO);
        }
    }

    @Override
    public void delete(Skill skill) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.remove(skill);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public Optional<Skill> get(String name) {
        return Optional.ofNullable(entityManager.find(Skill.class, name));
    }

    @Override
    public List<Skill> all() {
        return entityManager.createQuery("SELECT skill FROM Skill skill", Skill.class).getResultList();
    }
}
