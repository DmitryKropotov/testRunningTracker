package running.service;

import running.entity.UserEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(String firstName, String lastName, LocalDate birthDate, Character sex);
    void editUser(int userId, Optional<String> firstName, Optional<String> lastName, Optional<String> birthDate, Optional<Character> sex);
    void deleteUser(int userId);
    Optional<UserEntity> getUserById(int userId);
    List<UserEntity> getAllUsers();
}
