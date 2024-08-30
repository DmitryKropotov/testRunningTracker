package running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import running.entity.User;
import running.entity.enums.Sex;
import running.model.UserStatistics;
import running.service.RunService;
import running.service.UserService;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RunService runService;

    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam LocalDate birthDate, @RequestParam Sex sex) {
        userService.addUser(firstName, lastName, birthDate, sex);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void editUser(@RequestParam int userId, @RequestParam(required = false) String firstName,
                         @RequestParam(required = false) String lastName, @RequestParam(required = false) LocalDate birthDate,
                         @RequestParam(required = false) Sex sex) {
        userService.editUser(userId, Optional.of(firstName), Optional.of(lastName), Optional.of(birthDate), Optional.of(sex));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUser(@RequestParam int userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Optional<User> getUserById(@RequestParam int userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserStatistics getUserStatistics(@RequestParam int userId, @RequestParam(required = false) Instant startDateTimeFrom, @RequestParam(required = false) Instant startDateTimeTo) {
        return runService.getUserStatistics(userId, Optional.of(startDateTimeFrom), Optional.of(startDateTimeTo));
    }
}
