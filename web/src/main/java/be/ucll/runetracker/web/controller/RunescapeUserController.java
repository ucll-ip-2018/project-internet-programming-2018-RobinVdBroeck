package be.ucll.runetracker.web.controller;

import be.ucll.runetracker.database.DataPointService;
import be.ucll.runetracker.domain.RunescapeUser;
import be.ucll.runetracker.web.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class RunescapeUserController {
    private DataPointService dataPointService;

    public RunescapeUserController(@Autowired DataPointService dataPointService) {
        this.dataPointService = dataPointService;
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("user/index", "users", dataPointService.getAllUsers());
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("user/create", "runescapeUser", new RunescapeUser());
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String save(@Valid RunescapeUser user, BindingResult result) {
        if(result.hasErrors()) {
            return "user/create";
        }
        dataPointService.addUser(user);
        return "redirect:/user/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable int id) {
        Optional<RunescapeUser> user = dataPointService.getUser(id);
        if(user.isPresent()) {
            return new ModelAndView("user/show", "user", user.get());
        }
        throw new ResourceNotFoundException();
    }
}