package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.Skill;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface SkillDatabase {
    void add(Skill skill);
    void addAll(Collection<Skill> skills);
    void delete(Skill skill);
    Optional<Skill> get(String name);
    Stream<Skill> all();
}
