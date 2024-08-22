package running.controller;

import running.entity.enums.Sex;
import running.entity.UserEntity;
import running.model.UserStatistics;

import java.util.Date;
import java.util.List;

public interface UserController {
    boolean addUser(String FIRST_NAME,String LAST_NAME, Date BIRTH_DATE, Sex SEX);
    boolean editUser(int userId, String FIRST_NAME,String LAST_NAME, Date BIRTH_DATE, Sex SEX);
    boolean deleteUser(int userId);
    UserEntity getUser(int userId);
    List<UserEntity> getAllUsers();
    UserStatistics getUserStatistics(int userId, Date startDateTimeFrom, Date startDateTimeTo);
}
