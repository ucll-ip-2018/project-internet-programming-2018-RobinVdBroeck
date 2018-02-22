package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.DataPoint;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataPointDatabaseStubTests {
    @Test
    public void testAddAddsADataPointToTheDatabaseIfItDoesNotYetExist() {
        var database = new DataPointDatabaseStub();
        var point = mock(DataPoint.class);

        database.add(point);

        assertThat(database.all()).contains(point);
    }

    @Test
    public void testsAddThrowsExceptionWhenDataPointAlreadyExists() {
        var database = new DataPointDatabaseStub();
        var point = mock(DataPoint.class);
        when(point.getId()).thenReturn(0);
        database.add(point);

        assertThatExceptionOfType(DatabaseException.class).isThrownBy(() -> database.add(point))
                .withMessage("DataPoint with id 0 already exists");
    }

    @Test
    public void testDeleteDeletesTheDataPoint() {
        var database = new DataPointDatabaseStub();
        var point = mock(DataPoint.class);
        database.add(point);

        database.delete(point);

        assertThat(database.all()).doesNotContain(point);

    }

    @Test
    public void testGetSkillReturnsTheCorrectSkillObject() {
        var database = new DataPointDatabaseStub();
        var point = mock(DataPoint.class);
        var id = 0;

        when(point.getId()).thenReturn(id);
        database.add(point);

        assertThat(database.get(id)).contains(point);
    }

    @Test
    public void testAddAllAddsThemAllToTheDatabase() {
        var database = new DataPointDatabaseStub();

        var dataPoints = new ArrayList<DataPoint>();
        final var iterations = 500;
        for(var i = 0; i < iterations; i++) {
            var dataPoint = mock(DataPoint.class);
            when(dataPoint.getId()).thenReturn(i);
            dataPoints.add(dataPoint);
        }
        database.addAll(dataPoints);

        assertThat(database.all()).containsAll(dataPoints);
    }
}
