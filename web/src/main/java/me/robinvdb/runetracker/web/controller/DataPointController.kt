package me.robinvdb.runetracker.web.controller


import me.robinvdb.runetracker.database.DatabaseService
import me.robinvdb.runetracker.web.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView


@Controller
@RequestMapping("/datapoint")
class DataPointController(@param:Autowired private val databaseService: DatabaseService) {

    @RequestMapping(method = [(RequestMethod.GET)])
    fun index(): ModelAndView {
        return ModelAndView(
                "datapoint/index",
                "datapoints",
                databaseService.allDatapoints.toArray()
        )
    }

    @RequestMapping(path = ["/{id}"], method = [(RequestMethod.GET)])
    fun show(@PathVariable id: Int): ModelAndView {
        return databaseService.getDatapoint(id)
                .map { dataPoint -> ModelAndView("datapoint/show", "dataPoint", dataPoint) }
                .orElseThrow { ResourceNotFoundException() }
    }
}

