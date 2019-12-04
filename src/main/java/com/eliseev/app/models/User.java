package com.eliseev.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends AbstractEntity implements Serializable {


    @NotBlank(message = "Surname is required")
    private String surname;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Email must be formatted like sometext@mail.ru")
    private String email;
    private String login;
    private String pass;

    @OneToMany(mappedBy = "user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Ticket> trainStationList = new ArrayList<>();

    public User(@NotBlank(message = "Surname is required") String surname, @NotBlank(message = "Name is required") String name,
                @Email(message = "Email must be formatted like sometext@mail.ru") String email,
                @NotBlank(message = "Login is required") String login, @NotBlank(message = "Password is required") String pass) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.login = login;
        this.pass = pass;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(
                    name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name="role_id", referencedColumnName = "id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Role> roles = new ArrayList<>();

}
