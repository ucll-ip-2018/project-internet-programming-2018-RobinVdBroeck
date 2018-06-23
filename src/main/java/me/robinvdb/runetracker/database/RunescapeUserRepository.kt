package me.robinvdb.runetracker.database

import me.robinvdb.runetracker.domain.RunescapeUser
import org.springframework.data.repository.CrudRepository

interface RunescapeUserRepository : CrudRepository<RunescapeUser, Int>
