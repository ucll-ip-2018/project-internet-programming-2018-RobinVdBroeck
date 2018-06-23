package me.robinvdb.runetracker.web.controller

import me.robinvdb.runetracker.database.DataPointRepository
import me.robinvdb.runetracker.database.RunescapeUserRepository
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
        private val runescapeUserRepository: RunescapeUserRepository,
        private val dataPointRepository: DataPointRepository,
        private val highScoresService: HighScoresService
) {
    @GetMapping
    fun index(): ModelAndView {
        return ModelAndView("user/index", "users", dataPointRepository.findAll())
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
        runescapeUserRepository.save(user)
        return "redirect:/user/"
    }

    @GetMapping("/{id}")
    fun show(@PathVariable id: Int): ModelAndView {
        val user = runescapeUserRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        return ModelAndView("user/show", "runescapeUser", user)
    }

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable id: Int): ModelAndView {
        val user = runescapeUserRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        return ModelAndView("user/edit", "runescapeUser", user)
    }

    @PostMapping("/{id}/edit")
    fun editPost(@PathVariable id: Int, @Valid user: RunescapeUser, result: BindingResult): String {
        if (result.hasErrors()) {
            return "user/" + user.id + "/edit"
        }
        runescapeUserRepository.save(user)
        return "redirect:/user/${user.id!!}"
    }


    @GetMapping("{id}/delete")
    fun delete(@PathVariable id: Int): String {
        runescapeUserRepository.deleteById(id)
        return "redirect:/user/"
    }

    @GetMapping("{id}/createDatapoint")
    fun createDatapoint(@PathVariable id: Int): String {
        val user = runescapeUserRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        val stats = highScoresService.getStats(user.displayName)
        val dataPoint = DataPoint(null, LocalDateTime.now(), user, stats)
        dataPointRepository.save(dataPoint)
        return "redirect:/datapoint/${dataPoint.id}"
    }
}
