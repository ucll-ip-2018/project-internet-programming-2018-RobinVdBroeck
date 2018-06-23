package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.RunescapeUser
import java.util.Optional
import java.util.stream.Stream

interface RunescapeUserDatabase {
    fun add(user: RunescapeUser)

    fun addAll(users: Collection<RunescapeUser>)

    fun delete(user: RunescapeUser)

    operator fun get(id: Int): Optional<RunescapeUser>

    fun all(): Stream<RunescapeUser>

    fun update(user: RunescapeUser)
}
