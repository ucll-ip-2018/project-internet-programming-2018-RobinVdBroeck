package me.robinvdb.runetracker.services

import me.robinvdb.runetracker.domain.Stat

interface HighScoresService {
    fun getStats(username: String): Set<Stat>
}

