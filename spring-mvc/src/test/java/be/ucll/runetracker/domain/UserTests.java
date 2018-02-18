package be.ucll.runetracker.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


public class UserTests {
    @Test
    public void testGetterAndSetterForEmail() {
        var user = new User(null);
        var email = "test@localhost.dev";

        user.setEmail(email);

        assertThat(user.getEmail()).matches(email);
    }

    @Test
    public void testAddDisplayNameAddsDisplayName() {
        var user = new User("test@localhost.dev");
        var robin = new DisplayName("Robin", LocalDateTime.now());

        user.addDisplayName(robin);

        assertThat(user.getDisplayNames()).contains(robin);
    }

    @Test
    public void testCurrentDisplayNameIsEqualToTheDisplayWithTheLatestCreationDate() {
        var user = new User("test@localhost.dev");
        var zezima = new DisplayName("Zezima", LocalDateTime.now());
        var suomi = new DisplayName("S U  O  M I", LocalDateTime.MIN);

        user.addDisplayName(zezima);
        user.addDisplayName(suomi);

        assertThat(user.getCurrentDisplayName()).contains(zezima);
    }
}
