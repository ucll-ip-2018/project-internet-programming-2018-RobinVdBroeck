package org.ucll.runetracker.domain;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class UserTests {
    @Test
    public void testGetterAndSetterForEmail() {
        var user = new User(null);
        user.setEmail("test@localhost.dev");
        Assert.assertNotNull(user.getEmail());
    }

    @Test
    public void testAddDisplayNameAddsDisplayName() {
        var user = new User("test@localhost.dev");
        var robin = new DisplayName("Robin", LocalDateTime.now());

        user.addDisplayName(robin);

        assert user.getDisplayNames().contains(robin);
    }

    @Test
    public void testCurrentDisplayNameIsEqualToTheDisplayWithTheLatestCreationDate() {
        var user = new User("test@localhost.dev");
        var zezima = new DisplayName("Zezima", LocalDateTime.now());
        var suomi = new DisplayName("S U  O  M I", LocalDateTime.MIN);

        user.addDisplayName(zezima);
        user.addDisplayName(suomi);

        Assert.assertEquals(zezima, user.getCurrentDisplayName().get());
    }
}
