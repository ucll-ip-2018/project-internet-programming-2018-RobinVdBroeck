package me.robinvdb.runetracker.web.controller;

import me.robinvdb.runetracker.database.DatabaseService;
import me.robinvdb.runetracker.domain.DataPoint;
import me.robinvdb.runetracker.domain.RunescapeUser;
import me.robinvdb.runetracker.web.ResourceNotFoundException;
import me.robinvdb.runetracker.services.HighScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api/user")
public class RunescapeUserRestController {
    private final DatabaseService databaseService;
    private final HighScoresService highScoresService;

    public RunescapeUserRestController(@Autowired DatabaseService databaseService, @Autowired HighScoresService highScoresService) {
        this.databaseService = databaseService;
        this.highScoresService = highScoresService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<RunescapeUser> getUsers() {
        return databaseService.getAllUsers().collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RunescapeUser createUser(@RequestBody RunescapeUser user) {
        databaseService.addUser(user);

        return databaseService.getUser(user.getId()).orElseThrow(ResourceNotFoundException::new);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public RunescapeUser getUser(@PathVariable int id) {
        return databaseService.getUser(id).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @ResponseBody
    public RunescapeUser updateUser(@PathVariable int id, @RequestBody RunescapeUser user) {
        if (user.getId() != null && id != user.getId()) {
            // TODO: throw the right error
            throw new RuntimeException();
        }
        databaseService.updateUser(user);

        return databaseService.getUser(user.getId()).orElseThrow(ResourceNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable int id) {
        databaseService.deleteUser(databaseService.getUser(id).orElseThrow(ResourceNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/createpoint")
    @ResponseBody
    public DataPoint stats(@PathVariable int id) {
        RunescapeUser user = databaseService.getUser(id).orElseThrow(ResourceNotFoundException::new);

        DataPoint dataPoint = DataPoint.createWithUserAndStats(
                user,
                highScoresService.getStats(user.getDisplayName())
        );

        databaseService.addDatapoint(dataPoint);

        return dataPoint;
    }
}
