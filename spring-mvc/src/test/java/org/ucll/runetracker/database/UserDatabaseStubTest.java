package org.ucll.runetracker.database;

import org.junit.Test;
import org.ucll.runetracker.domain.User;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDatabaseStubTest {
    @Test
    public void testAddAddsAuserToTheDabaseIfItDoesNotYetExist() {
        var database = new UserDatabaseStub();
        var user = mock(User.class);

        database.add(user);

        assertThat(database.all()).contains(user);
    }

    @Test
    public void testsAddThrowsExceptionWhenuserAlreadyExists() {
        var database = new UserDatabaseStub();
        var user = mock(User.class);

        database.add(user);

        assertThatExceptionOfType(DatabaseException.class).isThrownBy(() -> database.add(user))
                .withMessage("User already exists");
    }

    @Test
    public void testDeleteDeletesTheuser() {
        var database = new UserDatabaseStub();
        var user = mock(User.class);

        database.add(user);
        database.delete(user);

        assertThat(database.all()).doesNotContain(user);
    }

    @Test
    public void testGetuserReturnsTheCorrectuserObject() {
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

        // Create the users and add them to the database
        var users = new ArrayList<User>();
        final var iterations = 500;
        for(var i = 0; i < iterations; i++) {
            var user = mock(User.class);
            when(user.getEmail()).thenReturn(i + "@localhost");
            users.add(user);
        }
        database.addAll(users);

        assertThat(database.all()).containsAll(users);
    }
}
