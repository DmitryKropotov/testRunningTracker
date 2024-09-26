package running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import running.entity.UserEntity;
import running.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public UserEntity addUser(String firstName, @RequestParam(required = false) String lastName, @RequestParam String birthDate, @RequestParam Character sex) {
        return userService.addUser(firstName, lastName, LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd.MM.yyyy")), sex);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser")
    public void editUser(int userId, @RequestParam(required = false) String firstName,
                         @RequestParam(required = false) String lastName, @RequestParam(required = false) String birthDate,
                         @RequestParam(required = false) Character sex) {
        userService.editUser(userId, Optional.of(firstName), Optional.of(lastName), Optional.of(birthDate), Optional.of(sex));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteUser")
    public void deleteUser(int userId) {
        userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserById")
    public Optional<UserEntity> getUserById(int userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
}
