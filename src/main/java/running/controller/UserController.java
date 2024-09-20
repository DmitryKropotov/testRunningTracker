package running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import running.entity.User;
import running.model.UserStatistics;
import running.service.RunService;
import running.service.UserService;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RunService runService;

    @PostMapping("/addUser")
    public void addUser(String firstName, @RequestParam(required = false) String lastName, @RequestParam String birthDate, @RequestParam Character sex) {
        System.out.println("addUser method " + firstName + " " + lastName);
        //userService.addUser(firstName, lastName, birthDate, sex);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser")
    public void editUser(int userId, @RequestParam(required = false) String firstName,
                         @RequestParam(required = false) String lastName, @RequestParam(required = false) String birthDate,
                         @RequestParam(required = false) Character sex) {
        System.out.println("editUser method");
        //userService.editUser(userId, Optional.of(firstName), Optional.of(lastName), Optional.of(birthDate), Optional.of(sex));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteUser")
    public void deleteUser(int userId) {
        System.out.println("deleteUser");
        //userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserById")
    public Optional<User> getUserById(int userId) {
        System.out.println("getUserById");
        //return userService.getUserById(userId);
        return Optional.empty();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllUsers")
    public List<User> getAllUsers() {
        System.out.println("getAllUsers");
        //return userService.getAllUsers();
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserStatistics")
    public UserStatistics getUserStatistics(int userId, @RequestParam(required = false) String startDateTimeFrom, @RequestParam(required = false) String startDateTimeTo) {
        System.out.println("getUserStatistics");
        //return runService.getUserStatistics(userId, Optional.of(startDateTimeFrom), Optional.of(startDateTimeTo));
        return null;
    }
}
