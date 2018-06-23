package me.robinvdb.runetracker.services

import me.robinvdb.runetracker.domain.DataPointEntry

interface HighScoresService {
    fun getStats(username: String): List<DataPointEntry>
}
