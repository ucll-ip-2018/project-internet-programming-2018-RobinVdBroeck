package be.ucll.runetracker.web.controller;


import be.ucll.runetracker.database.DatabaseService;
import be.ucll.runetracker.web.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/datapoint")
public class DataPointController {
    private final DatabaseService databaseService;

    public DataPointController(@Autowired DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView(
                "datapoint/index",
                "datapoints",
                databaseService.getAllDatapoints().toArray()
        );
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable int id) {
        return databaseService.getDatapoint(id)
                .map(dataPoint -> new ModelAndView("datapoint/show", "dataPoint", dataPoint))
                .orElseThrow(ResourceNotFoundException::new);
    }
}

