package be.ucll.runetracker.web.controller;

import be.ucll.runetracker.domain.DataPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    private DataPointService dataPointService;

    public UserController(@Autowired DataPointService dataPointService) {
        this.dataPointService = dataPointService;
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("users/index", "users", dataPointService.getAllUsers());
    }
}
