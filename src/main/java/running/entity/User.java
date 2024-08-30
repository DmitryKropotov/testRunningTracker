package running.entity;

import running.entity.enums.Sex;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "firstname")
    private String firstName;

    @Lob
    @Column(name = "lastname")
    private String lastName;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Lob
    @Column(name = "sex")
    private char sex;

    public Integer getId() {
        return id;
    }

    public User(String firstName, String lastName, LocalDate birthdate, char sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.sex = sex;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}