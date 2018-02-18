package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.User;

import java.util.*;

class UserDatabaseStub implements UserDatabase {
    private Map<String, User> users = new HashMap<>();

    @Override
    public void add(User user) {
        if(users.containsKey(user.getEmail())) {
            throw new DatabaseException("User already exists");
        }
        users.put(user.getEmail(), user);
    }

    @Override
    public void addAll(Collection<User> users) {
        users.forEach(this::add);
    }

    @Override
    public void delete(User user) {
        users.remove(user.getEmail());
   }

    @Override
    public Optional<User> get(String email) {
        return Optional.ofNullable(users.get(email));
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(users.values());
    }
}
