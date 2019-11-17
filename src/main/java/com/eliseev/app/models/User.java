package com.eliseev.app.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity implements Serializable {


    @NotBlank(message = "Surname is required")
    private String surname;
    @NotBlank(message = "Name is required")
    private String name;
    /*@Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            message = "Email must be formatted like sometext@mail.ru")*/
    @Email(message = "Email must be formatted like sometext@mail.ru")
    private String email;
    @NotBlank(message = "Login is required")
    private String login;
    @NotBlank(message = "Password is required")
    private String pass;

    @OneToMany(mappedBy = "user")
    private List<Ticket> trainStationList = new ArrayList<>();

    public User() { }

    public User(long id, String surname, String name, String email, String login, String pass) {
        super.id = id;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.login = login;
        this.pass = pass;
    }

    public void setTrainStationList(List<Ticket> trainStationList) {
        this.trainStationList = trainStationList;
    }

    public List<Ticket> getTrainStationList() {
        return trainStationList;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(surname, user.surname)
                .append(name, user.name)
                .append(email, user.email)
                .append(login, user.login)
                .append(pass, user.pass)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(surname)
                .append(name)
                .append(email)
                .append(login)
                .append(pass)
                .toHashCode();
    }
}
