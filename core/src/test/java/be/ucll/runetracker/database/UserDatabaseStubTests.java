package be.ucll.runetracker.database;

import org.junit.Test;
import be.ucll.runetracker.domain.User;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDatabaseStubTests {
    @Test
    public void testAddAddsAUserToTheDatabaseIfItDoesNotYetExist() {
        var database = new UserDatabaseStub();
        var user = mock(User.class);

        database.add(user);

        assertThat(database.all()).contains(user);
    }

    @Test
    public void testsAddThrowsExceptionWhenUserAlreadyExists() {
        var database = new UserDatabaseStub();
        var user = mock(User.class);

        database.add(user);

        assertThatExceptionOfType(DatabaseException.class).isThrownBy(() -> database.add(user))
                .withMessage("User already exists");
    }

    @Test
    public void testDeleteDeletesTheUser() {
        var database = new UserDatabaseStub();
        var user = mock(User.class);

        database.add(user);
        database.delete(user);

        assertThat(database.all()).doesNotContain(user);
    }

    @Test
    public void testGetUserReturnsTheCorrectUserObject() {
        var database = new UserDatabaseStub();

        var robin = mock(User.class);
        when(robin.getEmail()).thenReturn("robin");
        var joris = mock(User.class);
        when(joris.getEmail()).thenReturn("joris");

        database.add(robin);
        database.add(joris);

        assertThat(database.get("robin")).contains(robin);
    }

    @Test
    public void testAddAllAddsThemAllToTheDatabase() {
        var database = new UserDatabaseStub();

        var users = new ArrayList<User>();
        final var iterations = 500;
        for (var i = 0; i < iterations; i++) {
            var user = mock(User.class);
            when(user.getEmail()).thenReturn(i + "@localhost");
            users.add(user);
        }
        database.addAll(users);

        assertThat(database.all()).containsAll(users);
    }
}
