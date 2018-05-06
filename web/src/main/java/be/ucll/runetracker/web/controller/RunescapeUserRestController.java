package be.ucll.runetracker.web.controller;

import be.ucll.runetracker.database.DataPointService;
import be.ucll.runetracker.domain.RunescapeUser;
import be.ucll.runetracker.web.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/user")
public class RunescapeUserRestController {
    private final DataPointService service;

    public RunescapeUserRestController(@Autowired DataPointService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<RunescapeUser> getUsers() {
        return service.getAllUsers();
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
        if(user.getId() != null && id != user.getId()) {
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

}
