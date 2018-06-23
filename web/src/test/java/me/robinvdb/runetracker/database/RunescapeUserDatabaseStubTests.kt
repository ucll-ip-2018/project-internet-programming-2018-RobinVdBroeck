package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.RunescapeUser
import org.junit.Test

import java.util.ArrayList

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class RunescapeUserDatabaseStubTests {
    @Test
    fun testAddAddsAUserToTheDatabaseIfItDoesNotYetExist() {
        val database = RunescapeUserDatabaseStub()
        val user = mock(RunescapeUser::class.java)

        database.add(user)

        assertThat(database.all()).contains(user)
    }

    @Test
    fun testsAddThrowsExceptionWhenUserAlreadyExists() {
        val database = RunescapeUserDatabaseStub()
        val user = mock(RunescapeUser::class.java)

        database.add(user)

        assertThatExceptionOfType(DatabaseException::class.java).isThrownBy { database.add(user) }
                .withMessage("User already exists")
    }

    @Test
    fun testDeleteDeletesTheUser() {
        val database = RunescapeUserDatabaseStub()
        val runescapeUser = mock(RunescapeUser::class.java)

        database.add(runescapeUser)
        database.delete(runescapeUser)

        assertThat(database.all()).doesNotContain(runescapeUser)
    }

    @Test
    fun testGetUserReturnsTheCorrectUserObject() {
        val database = RunescapeUserDatabaseStub()

        val robin = mock(RunescapeUser::class.java)
        `when`<Int>(robin.id).thenReturn(0)
        val joris = mock(RunescapeUser::class.java)
        `when`<Int>(joris.id).thenReturn(1)

        database.add(robin)
        database.add(joris)

        assertThat(database[0]).contains(robin)
    }

    @Test
    fun testAddAllAddsThemAllToTheDatabase() {
        val database = RunescapeUserDatabaseStub()

        val users = ArrayList<RunescapeUser>()
        val iterations = 500
        for (i in 0 until iterations) {
            val user = mock(RunescapeUser::class.java)
            `when`(user.id).thenReturn(i)
            users.add(user)
        }
        database.addAll(users)

        assertThat(database.all()).containsAll(users)
    }
}
