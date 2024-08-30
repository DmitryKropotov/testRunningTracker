package running.service;

import running.entity.User;
import running.entity.enums.Sex;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(String firstName, String lastName, LocalDate birthDate, Sex sex);
    void editUser(int userId, Optional<String> firstName, Optional<String> lastName, Optional<LocalDate> birthDate, Optional<Sex> sex);
    void deleteUser(int userId);
    Optional<User> getUserById(int userId);
    List<User> getAllUsers();
}
