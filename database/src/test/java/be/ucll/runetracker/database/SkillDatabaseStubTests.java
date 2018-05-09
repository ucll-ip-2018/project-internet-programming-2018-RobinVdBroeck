package be.ucll.runetracker.database;


import be.ucll.runetracker.domain.Skill;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SkillDatabaseStubTests {

    @Test
    public void testAddAddsASkillToTheDatabaseIfItDoesNotYetExist() {
        SkillDatabase database = new SkillDatabaseStub();
        Skill skill = mock(Skill.class);

        database.add(skill);

        assertThat(database.all()).contains(skill);
    }

    @Test
    public void testsAddThrowsExceptionWhenSkillAlreadyExists() {
        SkillDatabase database = new SkillDatabaseStub();
        Skill skill = mock(Skill.class);

        database.add(skill);

        assertThatExceptionOfType(DatabaseException.class).isThrownBy(() -> database.add(skill))
                .withMessage("Skill already exists");
    }

    @Test
    public void testDeleteDeletesTheSkill() {
        SkillDatabase database = new SkillDatabaseStub();
        Skill skill = mock(Skill.class);

        database.add(skill);
        database.delete(skill);

        assertThat(database.all()).doesNotContain(skill);
    }

    @Test
    public void testGetSkillReturnsTheCorrectSkillObject() {
        SkillDatabase database = new SkillDatabaseStub();
        Skill woodcutting = new Skill("woodcutting");
        Skill mining = new Skill("mining");

        database.add(woodcutting);
        database.add(mining);

        assertThat(database.get("mining")).contains(mining);
    }

    @Test
    public void testAddAllAddsThemAllToTheDatabase() {
        SkillDatabase database = new SkillDatabaseStub();

        // Create the skills and add them to the database
        Collection<Skill> skills = new ArrayList<>();
        final int iterations = 500;
        for (int i = 0; i < iterations; i++) {
            Skill skill = mock(Skill.class);
            when(skill.getName()).thenReturn(String.valueOf(i));
            skills.add(skill);
        }
        database.addAll(skills);

        assertThat(database.all()).containsAll(skills);
    }
}
