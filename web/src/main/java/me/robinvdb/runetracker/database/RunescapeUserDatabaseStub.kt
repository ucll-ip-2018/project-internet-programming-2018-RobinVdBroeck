package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.RunescapeUser

import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Consumer
import java.util.stream.Stream

class RunescapeUserDatabaseStub : RunescapeUserDatabase {
    private val users = HashMap<Int, RunescapeUser>()
    private val counter = AtomicInteger()

    override fun add(user: RunescapeUser) {
        if (user.id == null) {
            user.id = counter.getAndIncrement()
        }

        if (users.containsKey(user.id!!)) {
            throw DatabaseException("User already exists")
        }
        users[user.id!!] = user
    }

    override fun addAll(users: Collection<RunescapeUser>) {
        users.forEach { this.add(it) }
    }

    override fun delete(user: RunescapeUser) {
        users.remove(user.id)
    }

    override fun get(id: Int): Optional<RunescapeUser> {
        return Optional.ofNullable(users[id])
    }

    override fun all(): Stream<RunescapeUser> {
        return users.values.stream()
    }

    override fun update(user: RunescapeUser) {
        users[user.id!!] = user
    }
}
