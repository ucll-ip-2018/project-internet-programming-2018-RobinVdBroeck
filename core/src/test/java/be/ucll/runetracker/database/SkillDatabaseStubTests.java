package be.ucll.runetracker.database;


import org.junit.Test;
import be.ucll.runetracker.domain.Skill;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SkillDatabaseStubTests {

    @Test
    public void testAddAddsASkillToTheDatabaseIfItDoesNotYetExist() {
        var database = new SkillDatabaseStub();
        var skill = mock(Skill.class);

        database.add(skill);

        assertThat(database.all()).contains(skill);
    }

    @Test
    public void testsAddThrowsExceptionWhenSkillAlreadyExists() {
        var database = new SkillDatabaseStub();
        var skill = mock(Skill.class);

        database.add(skill);

        assertThatExceptionOfType(DatabaseException.class).isThrownBy(() -> database.add(skill))
                .withMessage("Skill already exists");
    }

    @Test
    public void testDeleteDeletesTheSkill() {
        var database = new SkillDatabaseStub();
        var skill = mock(Skill.class);

        database.add(skill);
        database.delete(skill);

        assertThat(database.all()).doesNotContain(skill);
    }

    @Test
    public void testGetSkillReturnsTheCorrectSkillObject() {
        var database = new SkillDatabaseStub();
        var woodcutting = new Skill("woodcutting");
        var mining = new Skill("mining");

        database.add(woodcutting);
        database.add(mining);

        assertThat(database.get("mining")).contains(mining);
    }

    @Test
    public void testAddAllAddsThemAllToTheDatabase() {
        var database = new SkillDatabaseStub();

        // Create the skills and add them to the database
        var skills = new ArrayList<Skill>();
        final var iterations = 500;
        for (var i = 0; i < iterations; i++) {
            var skill = mock(Skill.class);
            when(skill.getName()).thenReturn(String.valueOf(i));
            skills.add(skill);
        }
        database.addAll(skills);

        assertThat(database.all()).containsAll(skills);
    }
}
