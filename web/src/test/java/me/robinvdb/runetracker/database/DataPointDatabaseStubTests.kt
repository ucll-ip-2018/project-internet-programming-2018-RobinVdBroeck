package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.DataPoint
import org.junit.Test

import java.util.ArrayList

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DataPointDatabaseStubTests {
    @Test
    fun testAddAddsADataPointToTheDatabaseIfItDoesNotYetExist() {
        val database = DataPointDatabaseStub()
        val point = mock(DataPoint::class.java)

        database.add(point)

        assertThat(database.all()).contains(point)
    }

    @Test
    fun testsAddThrowsExceptionWhenDataPointAlreadyExists() {
        val database = DataPointDatabaseStub()
        val point = mock(DataPoint::class.java)
        `when`<Int>(point.id).thenReturn(0)
        database.add(point)

        assertThatExceptionOfType(DatabaseException::class.java).isThrownBy { database.add(point) }
                .withMessage("DataPoint with id 0 already exists")
    }

    @Test
    fun testDeleteDeletesTheDataPoint() {
        val database = DataPointDatabaseStub()
        val point = mock(DataPoint::class.java)
        database.add(point)

        database.delete(point)

        assertThat(database.all()).doesNotContain(point)

    }

    @Test
    fun testGetSkillReturnsTheCorrectSkillObject() {
        val database = DataPointDatabaseStub()
        val point = mock(DataPoint::class.java)
        val id = 0

        `when`<Int>(point.id).thenReturn(id)
        database.add(point)

        assertThat(database[id]).contains(point)
    }

    @Test
    fun testAddAllAddsThemAllToTheDatabase() {
        val database = DataPointDatabaseStub()

        val dataPoints = ArrayList<DataPoint>()
        val iterations = 500
        for (i in 0 until iterations) {
            val dataPoint = mock(DataPoint::class.java)
            `when`<Int>(dataPoint.id).thenReturn(i)
            dataPoints.add(dataPoint)
        }
        database.addAll(dataPoints)

        assertThat(database.all()).containsAll(dataPoints)
    }
}
