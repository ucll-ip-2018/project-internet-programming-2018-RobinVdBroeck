package me.robinvdb.runetracker.services

import me.robinvdb.runetracker.domain.Stat
import me.robinvdb.runetracker.domain.Skill
import org.springframework.stereotype.Service

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.concurrent.atomic.AtomicInteger
import java.util.logging.Logger
import kotlin.streams.toList

@Service("highscoresService")
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

    override fun getStats(username: String): Set<Stat> {
        try {
            val url = URL("http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=$username")
            val connection = url.openConnection()
            logger.info("Fetching stats of $username")
            BufferedReader(InputStreamReader(
                    connection.getInputStream())).use { `in` ->
                val atomicInteger = AtomicInteger()

                return `in`.lines()
                        .limit(skillOrder.size.toLong())
                        .map { splitWhileNotEmpty(it, ",") }
                        .map { this.convertStringsToIntegers(it) }
                        .map { parsed -> Stat(null, skillOrder[atomicInteger.getAndIncrement()], parsed[0], parsed[1].toShort(), parsed[2]) }
                        .toList()
                        .toSet()
            }
        } catch (e: IOException) {
            throw HighscoreServiceException(e)
        }
    }

    private fun splitWhileNotEmpty(line: String, delimiter: String) = line.split(delimiter).dropLastWhile { it.isEmpty() }
    private fun convertStringsToIntegers(strings: Collection<String>) = strings.map { Integer.parseInt(it) }
    
    companion object {
        private val logger = Logger.getLogger(HighScoresServiceImpl::class.java.toString())
    }
}


