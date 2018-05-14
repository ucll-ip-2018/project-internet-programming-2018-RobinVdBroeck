package be.ucll.runetracker.web.controller;

import be.ucll.runetracker.database.DatabaseService;
import be.ucll.runetracker.domain.RunescapeUser;
import be.ucll.runetracker.web.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class RunescapeUserController {
    private DatabaseService databaseService;

    public RunescapeUserController(@Autowired DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("user/index", "users", databaseService.getAllUsers().toArray());
    }

    @GetMapping(value = "/create")
    public ModelAndView create() {
        return new ModelAndView("user/create", "runescapeUser", new RunescapeUser());
    }

    @PostMapping(value = "/create")
    public String save(@Valid RunescapeUser user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/create";
        }
        databaseService.addUser(user);
        return "redirect:/user/";
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable int id) {
        Optional<RunescapeUser> user = databaseService.getUser(id);
        if (user.isPresent()) {
            return new ModelAndView("user/show", "runescapeUser", user.get());
        }
        throw new ResourceNotFoundException();
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable int id) {
        return databaseService.getUser(id)
                .map((user) -> new ModelAndView("user/edit", "runescapeUser", user))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable int id, @Valid RunescapeUser user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/" + user.getId() + "/edit";
        }
        databaseService.updateUser(user);
        return "redirect:/user/" + user.getId();
    }


    @GetMapping("{id}/delete")
    public String delete(@PathVariable int id) {
        return databaseService.getUser(id)
                .map((user) -> {
                    databaseService.deleteUser(user);
                    return "redirect:/user/";
                })
                .orElseThrow(ResourceNotFoundException::new);
    }
}
