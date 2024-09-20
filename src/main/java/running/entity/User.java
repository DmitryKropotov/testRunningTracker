package running.entity;

import jakarta.persistence.*;
import running.entity.enums.Sex;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany
    private List<Run> runs;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "sex")
    private char sex;

    public Integer getId() {
        return id;
    }

//    public User(String firstName, String lastName, LocalDate birthdate, char sex) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.birthdate = birthdate;
//        this.sex = sex;
//    }
//
    public void setId(Integer id) {
        this.id = id;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public void setRuns(List<Run> runs) {
        this.runs = runs;
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