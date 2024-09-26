package running.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import running.entity.UserEntity;
import running.entity.enums.Sex;
import running.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static int userId = 1;

    @Override
    public UserEntity addUser(String firstName, String lastName, LocalDate birthDate, Character sex) {
        if(Arrays.stream(Sex.values()).noneMatch(s->s.name().equals(String.valueOf(sex).toUpperCase()))) {
          throw new RuntimeException("You can send only M and W as sex. Sex " + sex + " doesn't exist");
        }
        return userRepository.save(new UserEntity(userId++, firstName, lastName, birthDate, sex.toString().toUpperCase().charAt(0)));
    }

    @Override
    public void editUser(int userId, Optional<String> firstName, Optional<String> lastName, Optional<String> birthDate,
                         Optional<Character> sex) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return;
        }
        UserEntity normalUserEntity = user.get();
        firstName.ifPresent(normalUserEntity::setFirstName);
        lastName.ifPresent(normalUserEntity::setLastName);
        birthDate.ifPresent(birthDateNotOptional -> {
            normalUserEntity.setBirthdate(LocalDate.parse(birthDateNotOptional));
        });
        if(Arrays.stream(Sex.values()).anyMatch(s->s.name().equals(String.valueOf(sex).toUpperCase()))) {
            sex.ifPresent(normalUserEntity::setSex);
        }
        userRepository.save(normalUserEntity);
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<UserEntity> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> userEntities = new ArrayList();
        userRepository.findAll().forEach(userEntities::add);
        return userEntities;
    }
}
