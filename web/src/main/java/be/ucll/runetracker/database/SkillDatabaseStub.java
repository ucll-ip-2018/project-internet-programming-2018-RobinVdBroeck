package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.Skill;

import java.util.*;

public class SkillDatabaseStub implements SkillDatabase {
    private Map<String, Skill> skills = new HashMap<>();

    public SkillDatabaseStub() {

    }

    @Override
    public void add(Skill skill) {
        if (skills.containsKey(skill.getName())) {
            throw new DatabaseException("Skill already exists");
        }
        skills.put(skill.getName(), skill);
    }

    @Override
    public void addAll(Collection<Skill> skills) {
        skills.forEach(this::add);
    }

    @Override
    public void delete(Skill skill) {
        skills.remove(skill.getName());
    }

    @Override
    public Optional<Skill> get(String name) {
        return Optional.ofNullable(skills.get(name));
    }

    @Override
    public List<Skill> all() {
        return new ArrayList<>(skills.values());
    }
}