package be.ucll.runetracker.web.controller;


import be.ucll.runetracker.DataPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/datapoint")
public class DataPointController {
    private final DataPointService dataPointService;

    public DataPointController(@Autowired DataPointService dataPointService) {
        this.dataPointService = dataPointService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getDataPoints() {
        var dataPoints = dataPointService.getAll();
        return new ModelAndView("datapoint", "datapoint", dataPoints);
    }
}

