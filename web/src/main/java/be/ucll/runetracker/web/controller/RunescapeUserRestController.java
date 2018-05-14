package be.ucll.runetracker.web.controller;

import be.ucll.runetracker.database.DatabaseService;
import be.ucll.runetracker.domain.DataPointEntry;
import be.ucll.runetracker.domain.RunescapeUser;
import be.ucll.runetracker.web.ResourceNotFoundException;
import be.ucll.runetracker.web.config.HighScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(value = "/api/user")
public class RunescapeUserRestController {
    private final DatabaseService service;

    public RunescapeUserRestController(@Autowired DatabaseService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<RunescapeUser> getUsers() {
        return service.getAllUsers().collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RunescapeUser createUser(@RequestBody RunescapeUser user) {
        service.addUser(user);

        return service.getUser(user.getId()).orElseThrow(ResourceNotFoundException::new);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public RunescapeUser getUser(@PathVariable int id) {
        return service.getUser(id).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseBody
    public RunescapeUser updateUser(@PathVariable int id, @RequestBody RunescapeUser user) {
        if (user.getId() != null && id != user.getId()) {
            // TODO: throw the right error
            throw new RuntimeException();
        }
        service.updateUser(user);

        return service.getUser(user.getId()).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable int id) {
        service.deleteUser(service.getUser(id).orElseThrow(ResourceNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}/stats")
    public List<DataPointEntry> stats(@PathVariable int id) {
        RunescapeUser user = service.getUser(id).orElseThrow(ResourceNotFoundException::new);
        HighScoresService highScoresService = new HighScoresService();

        List<DataPointEntry> stats =  highScoresService.getStats(user.getDisplayName());
        return stats;
    }

}
