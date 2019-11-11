package com.eliseev.app.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Ticket extends AbstractEntity implements Serializable {


    private String train;
    private long userId;
    @NotBlank(message = "depStation is required")
    private String depStation;
    @NotBlank(message = "arrStation is required")
    private String arrStation;
    @NotBlank(message = "seatType is required")
    private String seatType;
    @NotBlank(message = "surname is required")
    private String surname;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "passportNumber is required")
    private String passportNumber;
    @NotNull(message = "arrTime is required")
    private Date arrTime;
    @NotNull(message = "depTime is required")
    private Date depTime;



    public Ticket() { }

    public Ticket(long id, String train, long userId,String depStation, String arrStation, String seatType,
                  String surname, String name, String passportNumber, Date depTime, Date arrTime) {
        super.id = id;
        this.userId = userId;
        this.train = train;
        this.depStation = depStation;
        this.arrStation = arrStation;
        this.seatType = seatType;
        this.surname = surname;
        this.name = name;
        this.passportNumber = passportNumber;

        this.depTime = depTime;
        this.arrTime = arrTime;
    }

    public void setArrTime(Date arrTime) {
        this.arrTime = arrTime;
    }

    public void setDepTime(Date depTime) {
        this.depTime = depTime;
    }

    public Date getArrTime() {
        return arrTime;
    }

    public Date getDepTime() {
        return depTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public void setDepStation(String depStation) {
        this.depStation = depStation;
    }

    public void setArrStation(String arrStation) {
        this.arrStation = arrStation;
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

    public String getTrain() {
        return train;
    }

    public String getDepStation() {
        return depStation;
    }

    public String getArrStation() {
        return arrStation;
    }

    public String getSeatType() {
        return seatType;
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
                "train=" + train +
                ", depStation='" + depStation + '\'' +
                ", arrStation='" + arrStation + '\'' +
                ", seatType='" + seatType + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return new EqualsBuilder()
                .append(train, ticket.train)
                .append(depStation, ticket.depStation)
                .append(arrStation, ticket.arrStation)
                .append(seatType, ticket.seatType)
                .append(surname, ticket.surname)
                .append(name, ticket.name)
                .append(passportNumber, ticket.passportNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(train)
                .append(depStation)
                .append(arrStation)
                .append(seatType)
                .append(surname)
                .append(name)
                .append(passportNumber)
                .toHashCode();
    }
}
