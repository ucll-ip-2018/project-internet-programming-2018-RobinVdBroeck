package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDatabaseStub implements UserDatabase {
    private Map<Integer, User> users = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    public UserDatabaseStub() {
    }

    @Override
    public void add(User user) {
        if(user.getId() == null) {
            user.setId(counter.getAndIncrement());
        }

        if (users.containsKey(user.getId())) {
            throw new DatabaseException("User already exists");
        }
        users.put(user.getId(), user);
    }

    @Override
    public void addAll(Collection<User> users) {
        users.forEach(this::add);
    }

    @Override
    public void delete(User user) {
        users.remove(user.getId());
    }

    @Override
    public Optional<User> get(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(users.values());
    }
}
