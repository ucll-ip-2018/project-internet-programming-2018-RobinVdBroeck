package me.robinvdb.runetracker.web.controller

import me.robinvdb.runetracker.database.DatabaseService
import me.robinvdb.runetracker.domain.DataPoint
import me.robinvdb.runetracker.domain.RunescapeUser
import me.robinvdb.runetracker.services.HighScoresService
import me.robinvdb.runetracker.web.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import javax.validation.Valid
import java.time.LocalDateTime

@Controller
@RequestMapping("/user")
class RunescapeUserController(
        @param:Autowired private val databaseService: DatabaseService,
        @param:Autowired private val highScoresService: HighScoresService
) {
    @GetMapping
    fun index(): ModelAndView {
        return ModelAndView("user/index", "users", databaseService.allUsers.toArray())
    }

    @GetMapping("/create")
    fun create(): ModelAndView {
        return ModelAndView("user/create", "runescapeUser", RunescapeUser())
    }

    @PostMapping("/create")
    fun save(@Valid user: RunescapeUser, result: BindingResult): String {
        if (result.hasErrors()) {
            return "user/create"
        }
        databaseService.addUser(user)
        return "redirect:/user/"
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Int): ModelAndView {
        val user = databaseService.getUser(id)
        if (user.isPresent) {
            return ModelAndView("user/show", "runescapeUser", user.get())
        }
        throw ResourceNotFoundException()
    }

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable id: Int): ModelAndView {
        return databaseService.getUser(id)
                .map { user -> ModelAndView("user/edit", "runescapeUser", user) }
                .orElseThrow { ResourceNotFoundException() }
    }

    @PostMapping("/{id}/edit")
    fun editPost(@PathVariable id: Int, @Valid user: RunescapeUser, result: BindingResult): String {
        if (result.hasErrors()) {
            return "user/" + user.id + "/edit"
        }
        databaseService.updateUser(user)
        return "redirect:/user/${user.id!!}"
    }


    @GetMapping("{id}/delete")
    fun delete(@PathVariable id: Int): String {
        return databaseService.getUser(id)
                .map { user ->
                    databaseService.deleteUser(user)
                    "redirect:/user/"
                }
                .orElseThrow { ResourceNotFoundException() }
    }

    @GetMapping("{id}/createDatapoint")
    fun createDatapoint(@PathVariable id: Int): String {
        return databaseService.getUser(id)
                .map { user ->
                    val stats = highScoresService.getStats(user.displayName)
                    val dataPoint = DataPoint(null, LocalDateTime.now(), user, stats)
                    databaseService.addDatapoint(dataPoint)
                    "redirect:/datapoint/${dataPoint.id!!}"
                }
                .orElseThrow { ResourceNotFoundException() }
    }
}
