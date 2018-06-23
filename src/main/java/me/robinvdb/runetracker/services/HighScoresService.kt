package me.robinvdb.runetracker.services

import me.robinvdb.runetracker.domain.DataPointEntry
import org.springframework.stereotype.Service

interface HighScoresService {
    fun getStats(username: String): List<DataPointEntry>
}

