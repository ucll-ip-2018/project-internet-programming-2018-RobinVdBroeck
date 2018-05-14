package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.RunescapeUser;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface RunescapeUserDatabase {
    void add(RunescapeUser user);

    void addAll(Collection<RunescapeUser> users);

    void delete(RunescapeUser user);

    Optional<RunescapeUser> get(int id);

    Stream<RunescapeUser> all();

    void update(RunescapeUser user);
}
