package me.robinvdb.runetracker.web.controller


import me.robinvdb.runetracker.database.DataPointRepository
import me.robinvdb.runetracker.web.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView


@Controller
@RequestMapping("/datapoint")
class DataPointController(private val dataPointRepository: DataPointRepository) {

    @RequestMapping(method = [(RequestMethod.GET)])
    fun index(): ModelAndView {
        return ModelAndView("datapoint/index", "datapoints", dataPointRepository.findAll())
    }

    @RequestMapping(path = ["/{id}"], method = [(RequestMethod.GET)])
    fun show(@PathVariable id: Long): ModelAndView {
        return ModelAndView("datapoint/show", "dataPoint", dataPointRepository.findById(id))
    }
}

