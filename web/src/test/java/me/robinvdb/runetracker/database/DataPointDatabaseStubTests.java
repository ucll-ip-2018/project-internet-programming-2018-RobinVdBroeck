package me.robinvdb.runetracker.database;

import me.robinvdb.runetracker.domain.DataPoint;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataPointDatabaseStubTests {
    @Test
    public void testAddAddsADataPointToTheDatabaseIfItDoesNotYetExist() {
        DataPointDatabase database = new DataPointDatabaseStub();
        DataPoint point = mock(DataPoint.class);

        database.add(point);

        assertThat(database.all()).contains(point);
    }

    @Test
    public void testsAddThrowsExceptionWhenDataPointAlreadyExists() {
        DataPointDatabase database = new DataPointDatabaseStub();
        DataPoint point = mock(DataPoint.class);
        when(point.getId()).thenReturn(0);
        database.add(point);

        assertThatExceptionOfType(DatabaseException.class).isThrownBy(() -> database.add(point))
                .withMessage("DataPoint with id 0 already exists");
    }

    @Test
    public void testDeleteDeletesTheDataPoint() {
        DataPointDatabase database = new DataPointDatabaseStub();
        DataPoint point = mock(DataPoint.class);
        database.add(point);

        database.delete(point);

        assertThat(database.all()).doesNotContain(point);

    }

    @Test
    public void testGetSkillReturnsTheCorrectSkillObject() {
        DataPointDatabase database = new DataPointDatabaseStub();
        DataPoint point = mock(DataPoint.class);
        int id = 0;

        when(point.getId()).thenReturn(id);
        database.add(point);

        assertThat(database.get(id)).contains(point);
    }

    @Test
    public void testAddAllAddsThemAllToTheDatabase() {
        DataPointDatabase database = new DataPointDatabaseStub();

        Collection<DataPoint> dataPoints = new ArrayList<>();
        int iterations = 500;
        for (int i = 0; i < iterations; i++) {
            DataPoint dataPoint = mock(DataPoint.class);
            when(dataPoint.getId()).thenReturn(i);
            dataPoints.add(dataPoint);
        }
        database.addAll(dataPoints);

        assertThat(database.all()).containsAll(dataPoints);
    }
}
