package com.eliseev.app.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
public class Ticket extends AbstractEntity implements Serializable {


    @NotBlank(message = "surname is required")
    private String surname;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "passportNumber is required")
    @Column(name="passport_number")
    private String passportNumber;
    @NotBlank(message = "seatType is required")
    @Column(name="seat_type")
    private String seatType;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private Route route;

    public Ticket() { }

    public Ticket(long id,
                  User user,
                  String surname,
                  String name,
                  String passportNumber,
                  String seatType,
                  Route route) {
        super.id = id;
        this.user = user;
        this.surname = surname;
        this.name = name;
        this.passportNumber = passportNumber;
        this.route = route;
        this.seatType = seatType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }


    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }


    @Override
    public String toString() {
        return "Ticket{" +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", route=" + route +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        return new EqualsBuilder()
                .append(surname, ticket.surname)
                .append(name, ticket.name)
                .append(passportNumber, ticket.passportNumber)
                .append(route, ticket.route)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(surname)
                .append(name)
                .append(passportNumber)
                .append(route)
                .toHashCode();
    }
}
