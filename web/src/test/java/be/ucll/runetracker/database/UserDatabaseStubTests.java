package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDatabaseStubTests {
    @Test
    public void testAddAddsAUserToTheDatabaseIfItDoesNotYetExist() {
        UserDatabase database = new UserDatabaseStub();
        User user = mock(User.class);

        database.add(user);

        assertThat(database.all()).contains(user);
    }

    @Test
    public void testsAddThrowsExceptionWhenUserAlreadyExists() {
        UserDatabase database = new UserDatabaseStub();
        User user = mock(User.class);

        database.add(user);

        assertThatExceptionOfType(DatabaseException.class).isThrownBy(() -> database.add(user))
                .withMessage("User already exists");
    }

    @Test
    public void testDeleteDeletesTheUser() {
        UserDatabase database = new UserDatabaseStub();
        User user = mock(User.class);

        database.add(user);
        database.delete(user);

        assertThat(database.all()).doesNotContain(user);
    }

    @Test
    public void testGetUserReturnsTheCorrectUserObject() {
        UserDatabase database = new UserDatabaseStub();

        User robin = mock(User.class);
        when(robin.getEmail()).thenReturn("robin");
        User joris = mock(User.class);
        when(joris.getEmail()).thenReturn("joris");

        database.add(robin);
        database.add(joris);

        assertThat(database.get("robin")).contains(robin);
    }

    @Test
    public void testAddAllAddsThemAllToTheDatabase() {
        UserDatabase database = new UserDatabaseStub();

        Collection<User> users = new ArrayList<>();
        final int iterations = 500;
        for (int i = 0; i < iterations; i++) {
            User user = mock(User.class);
            when(user.getEmail()).thenReturn(i + "@localhost");
            users.add(user);
        }
        database.addAll(users);

        assertThat(database.all()).containsAll(users);
    }
}
