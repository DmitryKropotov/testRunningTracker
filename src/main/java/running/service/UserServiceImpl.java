package running.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import running.entity.User;
import running.entity.enums.Sex;
import running.model.UserStatistics;
import running.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(String firstName, String lastName, LocalDate birthDate, Sex sex) {
        userRepository.save(new User(firstName, lastName, birthDate, sex.toString().charAt(0)));
    }

    @Override
    public void editUser(int userId, Optional<String> firstName, Optional<String> lastName, Optional<LocalDate> birthDate,
                         Optional<Sex> sex) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return;
        }
        User normalUserEntity = user.get();
        firstName.ifPresent(normalUserEntity::setFirstName);
        lastName.ifPresent(normalUserEntity::setLastName);
        birthDate.ifPresent(normalUserEntity::setBirthdate);
        sex.ifPresent(value -> normalUserEntity.setSex(value.toString().charAt(0)));
        userRepository.save(normalUserEntity);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userEntities = new ArrayList();
        userRepository.findAll().forEach(userEntities::add);
        return userEntities;
    }
}
