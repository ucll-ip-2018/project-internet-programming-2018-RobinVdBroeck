package me.robinvdb.runetracker.web.controller

import me.robinvdb.runetracker.database.DataPointRepository
import me.robinvdb.runetracker.database.RunescapeUserRepository
import me.robinvdb.runetracker.domain.DataPoint
import me.robinvdb.runetracker.domain.RunescapeUser
import me.robinvdb.runetracker.web.ResourceNotFoundException
import me.robinvdb.runetracker.services.HighScoresService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

import java.time.LocalDateTime

@Controller
@RequestMapping("/api/user")
class RunescapeUserRestController(
        private val runescapeUserRepository: RunescapeUserRepository,
        private val dataPointRepository: DataPointRepository,
        private val highScoresService: HighScoresService
) {
    @GetMapping
    @ResponseBody
    fun getUsers(): Iterable<RunescapeUser> {
        return runescapeUserRepository.findAll()
    }


    @PostMapping
    @ResponseBody
    fun createUser(@RequestBody user: RunescapeUser): RunescapeUser {
        return runescapeUserRepository.save(user)
    }


    @GetMapping("/{id}")
    @ResponseBody
    fun getUser(@PathVariable id: Int): RunescapeUser {
        return runescapeUserRepository.findById(id).orElseThrow {ResourceNotFoundException()}
    }

    @PutMapping("/{id}")
    @ResponseBody
    fun updateUser(@PathVariable id: Int, @RequestBody user: RunescapeUser): RunescapeUser {
        if (user.id != null && id != user.id) {
            // TODO: throw the right error
            throw RuntimeException()
        }
        return runescapeUserRepository.save(user)
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    fun deleteUser(@PathVariable id: Int) {
        runescapeUserRepository.deleteById(id)
    }

    @GetMapping("/{id}/createpoint")
    @ResponseBody
    fun stats(@PathVariable id: Int): DataPoint {
        val user = runescapeUserRepository.findById(id).orElseThrow { ResourceNotFoundException() }

        val stats = highScoresService.getStats(user.displayName)
        val dataPoint = DataPoint(null, LocalDateTime.now(), user, stats)

        dataPointRepository.save(dataPoint)

        return dataPoint
    }
}
