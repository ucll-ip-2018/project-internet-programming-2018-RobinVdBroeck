package me.robinvdb.runetracker.web.controller

import me.robinvdb.runetracker.database.DatabaseService
import me.robinvdb.runetracker.domain.DataPoint
import me.robinvdb.runetracker.domain.RunescapeUser
import me.robinvdb.runetracker.web.ResourceNotFoundException
import me.robinvdb.runetracker.services.HighScoresService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

import java.time.LocalDateTime
import kotlin.streams.toList

@Controller
@RequestMapping("/api/user")
class RunescapeUserRestController(
        @param:Autowired private val databaseService: DatabaseService,
        @param:Autowired private val highScoresService: HighScoresService
) {
    @GetMapping
    @ResponseBody
    fun getUsers(): List<RunescapeUser> {
        return databaseService.allUsers.toList()
    }


    @PostMapping
    @ResponseBody
    fun createUser(@RequestBody user: RunescapeUser): RunescapeUser {
        databaseService.addUser(user)

        return databaseService.getUser(user.id!!).orElseThrow {ResourceNotFoundException()}
    }


    @GetMapping("/{id}")
    @ResponseBody
    fun getUser(@PathVariable id: Int): RunescapeUser {
        return databaseService.getUser(id).orElseThrow {ResourceNotFoundException()}
    }

    @PutMapping("/{id}")
    @ResponseBody
    fun updateUser(@PathVariable id: Int, @RequestBody user: RunescapeUser): RunescapeUser {
        if (user.id != null && id != user.id) {
            // TODO: throw the right error
            throw RuntimeException()
        }
        databaseService.updateUser(user)

        return databaseService.getUser(user.id!!).orElseThrow {ResourceNotFoundException()}
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    fun deleteUser(@PathVariable id: Int) {
        databaseService.deleteUser(databaseService.getUser(id).orElseThrow { ResourceNotFoundException() })
    }

    @GetMapping("/{id}/createpoint")
    @ResponseBody
    fun stats(@PathVariable id: Int): DataPoint {
        val user = databaseService.getUser(id).orElseThrow { ResourceNotFoundException() }

        val stats = highScoresService.getStats(user.displayName)
        val dataPoint = DataPoint(null, LocalDateTime.now(), user, stats)

        databaseService.addDatapoint(dataPoint)

        return dataPoint
    }
}
