package running.entity;

import running.entity.enums.Sex;

import java.util.Date;

public class UserEntity {
    private final int ID;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Sex sex;

    public UserEntity(int ID, String firstName, String lastName, Date birthDate, Sex sex) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Sex getSex() {
        return sex;
    }
}
