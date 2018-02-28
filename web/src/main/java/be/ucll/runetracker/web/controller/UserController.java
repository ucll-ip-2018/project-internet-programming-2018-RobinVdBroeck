package be.ucll.runetracker.web.controller;

import be.ucll.runetracker.database.DataPointService;
import be.ucll.runetracker.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private DataPointService dataPointService;

    public UserController(@Autowired DataPointService dataPointService) {
        this.dataPointService = dataPointService;
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("user/index", "users", dataPointService.getAllUsers());
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("user/create", "user", new User());
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult result) {
        if(result.hasErrors()) {
            return "user/create";
        }
        dataPointService.addUser(user);
        return "redirect:/user/";
    }
}
