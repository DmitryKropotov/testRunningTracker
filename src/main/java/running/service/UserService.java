package running.service;

import running.entity.UserEntity;
import running.entity.enums.Sex;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(String firstName, String lastName, Date birthDate, Sex sex);
    void editUser(int userId, Optional<String> firstName, Optional<String> lastName, Optional<Date> birthDate, Optional<Sex> sex);
    void deleteUser(int userId);
    Optional<UserEntity> getUserById(int userId);
    List<UserEntity> getAllUsers();
}
