package me.robinvdb.runetracker.services

import me.robinvdb.runetracker.domain.DataPointEntry
import me.robinvdb.runetracker.domain.Skill

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.logging.Logger
import kotlin.streams.toList

class HighScoresServiceImpl : HighScoresService {
    private val skillOrder = listOf(
            Skill.TOTAL,
            Skill.ATTACK,
            Skill.DEFENCE,
            Skill.STRENGTH,
            Skill.HITPOINTS,
            Skill.RANGED,
            Skill.PRAYER,
            Skill.MAGIC,
            Skill.COOKING,
            Skill.WOODCUTTING,
            Skill.FLETCHING,
            Skill.FISHING,
            Skill.FIREMAKING,
            Skill.CRAFTING,
            Skill.SMITHING,
            Skill.MINING,
            Skill.HERBLORE,
            Skill.AGILITY,
            Skill.THIEVING,
            Skill.SLAYER,
            Skill.FARMING,
            Skill.RUNECRAFTING,
            Skill.HUNTER,
            Skill.CONSTRUCTION
    )

    override fun getStats(username: String): List<DataPointEntry> {
        try {
            val url = URL("http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=$username")
            val connection = url.openConnection()
            logger.info("Fetching stats of $username")
            BufferedReader(InputStreamReader(
                    connection.getInputStream())).use { `in` ->
                val atomicInteger = AtomicInteger()

                return `in`.lines()
                        .limit(skillOrder.size.toLong())
                        .map { line -> line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() }
                        .map { it.asList() }
                        .map { this.convertStringsToIntegers(it) }
                        .map { parsed -> DataPointEntry(null, skillOrder[atomicInteger.getAndIncrement()], parsed[0], parsed[1].toShort(), parsed[2]) }
                        .toList()

            }
        } catch (e: IOException) {
            throw HighscoreServiceException(e)
        }

    }


    private fun convertStringsToIntegers(strings: Collection<String>): List<Int> {
        val parsed = ArrayList<Int>(strings.size)
        for (string in strings) {
            val parsedInt = Integer.parseInt(string)
            parsed.add(parsedInt)
        }
        return parsed
    }

    companion object {
        private val logger = Logger.getLogger(HighScoresServiceImpl::class.java.toString())
    }
}
