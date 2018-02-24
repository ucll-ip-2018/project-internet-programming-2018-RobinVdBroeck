package be.ucll.runetracker.web.controller;


import be.ucll.runetracker.domain.DataPoint;
import be.ucll.runetracker.domain.DataPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/datapoint")
public class DataPointController {
    private final DataPointService dataPointService;

    public DataPointController(@Autowired DataPointService dataPointService) {
        this.dataPointService = dataPointService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getDataPoints() {
        List<DataPoint> dataPoints = dataPointService.getAllDatapoints();
        return new ModelAndView("datapoint/index", "datapoint", dataPoints);
    }
}

