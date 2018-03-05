package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.RunescapeUser;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RunescapeUserDatabase {
    void add(RunescapeUser user);

    void addAll(Collection<RunescapeUser> users);

    void delete(RunescapeUser user);

    Optional<RunescapeUser> get(int id);

    List<RunescapeUser> all();
}
