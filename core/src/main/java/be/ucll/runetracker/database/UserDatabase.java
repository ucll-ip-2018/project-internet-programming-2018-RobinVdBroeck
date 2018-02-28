package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserDatabase {
    void add(User user);
    void addAll(Collection<User> users);
    void delete(User user);
    Optional<User> get(int id);
    List<User> all();
}
