package org.ucll.runetracker.database;

import org.ucll.runetracker.domain.Skill;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SkillDatabase {
    void add(Skill skill);
    void addAll(Collection<Skill> skills);
    void delete(Skill skill);
    Optional<Skill> get(String name);
    List<Skill> all();
}
