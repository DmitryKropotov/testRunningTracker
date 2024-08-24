package running.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import running.entity.UserEntity;
import running.entity.enums.Sex;
import running.model.UserStatistics;
import running.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(String firstName, String lastName, Date birthDate, Sex sex) {
        userRepository.save(new UserEntity(firstName, lastName, birthDate, sex));
    }

    @Override
    public void editUser(int userId, Optional<String> firstName, Optional<String> lastName, Optional<Date> birthDate,
                         Optional<Sex> sex) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) {
            return;
        }
        UserEntity normalUserEntity = userEntity.get();
        firstName.ifPresent(normalUserEntity::setFirstName);
        lastName.ifPresent(normalUserEntity::setLastName);
        birthDate.ifPresent(normalUserEntity::setBirthDate);
        sex.ifPresent(normalUserEntity::setSex);
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

    @Override
    public UserStatistics getUserStatistics(int userId, Date startDateTimeFrom, Date startDateTimeTo) {
        return null;
    }
}
