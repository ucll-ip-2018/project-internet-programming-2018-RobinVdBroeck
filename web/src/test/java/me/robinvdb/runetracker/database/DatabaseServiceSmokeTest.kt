package me.robinvdb.runetracker.database


import me.robinvdb.runetracker.domain.RunescapeUser
import org.junit.Test


import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DatabaseServiceSmokeTest {
    @Test
    fun smokeTest() {
        val runescapeUserDatabase = RunescapeUserDatabaseStub()
        val service = DatabaseService(runescapeUserDatabase, mock(DataPointDatabase::class.java))

        assertThat(service.allUsers)
                .hasSize(0)

        val user = mock(RunescapeUser::class.java)
        `when`<Int>(user.id).thenReturn(0)

        service.addUser(user)

        assertThat(service.allUsers)
                .hasSize(1)
                .contains(user)

        service.deleteUser(user)

        assertThat(service.allUsers)
                .hasSize(0)
                .doesNotContain(user)
    }
}
