package be.ucll.runetracker.database;


import be.ucll.runetracker.domain.RunescapeUser;
import org.junit.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class DataPointServiceSmokeTest {
    @Test
    public void smokeTest() {
        RunescapeUserDatabase runescapeUserDatabase = new RunescapeUserDatabaseStub();
        DataPointService service = new DataPointService(runescapeUserDatabase, mock(DataPointDatabase.class), mock(SkillDatabase.class));

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
