package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.RunescapeUser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RunescapeUserDatabaseStubTests {
    @Test
    public void testAddAddsAUserToTheDatabaseIfItDoesNotYetExist() {
        RunescapeUserDatabase database = new RunescapeUserDatabaseStub();
        RunescapeUser RunescapeUser = mock(RunescapeUser.class);

        database.add(RunescapeUser);

        assertThat(database.all()).contains(RunescapeUser);
    }

    @Test
    public void testsAddThrowsExceptionWhenUserAlreadyExists() {
        RunescapeUserDatabase database = new RunescapeUserDatabaseStub();
        RunescapeUser RunescapeUser = mock(RunescapeUser.class);

        database.add(RunescapeUser);

        assertThatExceptionOfType(DatabaseException.class).isThrownBy(() -> database.add(RunescapeUser))
                .withMessage("RunescapeUser already exists");
    }

    @Test
    public void testDeleteDeletesTheUser() {
        RunescapeUserDatabase database = new RunescapeUserDatabaseStub();
        RunescapeUser RunescapeUser = mock(RunescapeUser.class);

        database.add(RunescapeUser);
        database.delete(RunescapeUser);

        assertThat(database.all()).doesNotContain(RunescapeUser);
    }

    @Test
    public void testGetUserReturnsTheCorrectUserObject() {
        RunescapeUserDatabase database = new RunescapeUserDatabaseStub();

        RunescapeUser robin = mock(RunescapeUser.class);
        when(robin.getId()).thenReturn(0);
        RunescapeUser joris = mock(RunescapeUser.class);
        when(joris.getId()).thenReturn(1);

        database.add(robin);
        database.add(joris);

        assertThat(database.get(0)).contains(robin);
    }

    @Test
    public void testAddAllAddsThemAllToTheDatabase() {
        RunescapeUserDatabase database = new RunescapeUserDatabaseStub();

        Collection<RunescapeUser> users = new ArrayList<>();
        final int iterations = 500;
        for (int i = 0; i < iterations; i++) {
            RunescapeUser RunescapeUser = mock(RunescapeUser.class);
            when(RunescapeUser.getId()).thenReturn(i);
            users.add(RunescapeUser);
        }
        database.addAll(users);

        assertThat(database.all()).containsAll(users);
    }
}
