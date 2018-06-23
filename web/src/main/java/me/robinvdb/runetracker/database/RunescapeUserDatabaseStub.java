package me.robinvdb.runetracker.database;

import me.robinvdb.runetracker.domain.RunescapeUser;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class RunescapeUserDatabaseStub implements RunescapeUserDatabase {
    private Map<Integer, RunescapeUser> users = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    public RunescapeUserDatabaseStub() {
    }

    @Override
    public void add(RunescapeUser user) {
        if(user.getId() == null) {
            user.setId(counter.getAndIncrement());
        }

        if (users.containsKey(user.getId())) {
            throw new DatabaseException("User already exists");
        }
        users.put(user.getId(), user);
    }

    @Override
    public void addAll(Collection<RunescapeUser> users) {
        users.forEach(this::add);
    }

    @Override
    public void delete(RunescapeUser user) {
        users.remove(user.getId());
    }

    @Override
    public Optional<RunescapeUser> get(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Stream<RunescapeUser> all() {
        return users.values().stream();
    }

    @Override
    public void update(RunescapeUser user) {
        users.put(user.getId(), user);
    }
}
