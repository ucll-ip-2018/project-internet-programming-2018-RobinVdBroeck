package me.robinvdb.runetracker.database;


import me.robinvdb.runetracker.domain.RunescapeUser;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class DatabaseServiceSmokeTest {
    @Test
    public void smokeTest() {
        RunescapeUserDatabase runescapeUserDatabase = new RunescapeUserDatabaseStub();
        DatabaseService service = new DatabaseService(runescapeUserDatabase, mock(DataPointDatabase.class));

        assertThat(service.getAllUsers())
                .hasSize(0);

        RunescapeUser user = new RunescapeUser();
        service.addUser(user);

        assertThat(service.getAllUsers())
                .hasSize(1)
                .contains(user);

        service.deleteUser(user);

        assertThat(service.getAllUsers())
                .hasSize(0)
                .doesNotContain(user);
    }
}