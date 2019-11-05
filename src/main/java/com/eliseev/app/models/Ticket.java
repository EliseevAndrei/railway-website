package com.eliseev.app.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

public class Ticket extends AbstractEntity implements Serializable {

    private Train train;
    private long userId;
    private String depStation;
    private String arrStation;
    private Date date;
    private String seatType;
    private String surname;
    private String name;
    private String passportNumber;

    public Ticket() { }

    public Ticket(long id, Train train, long userId,String depStation, String arrStation, Date date, String seatType,
                  String surname, String name, String passportNumber) {
        super.id = id;
        this.userId = userId;
        this.train = train;
        this.depStation = depStation;
        this.arrStation = arrStation;
        this.date = date;
        this.seatType = seatType;
        this.surname = surname;
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setDepStation(String depStation) {
        this.depStation = depStation;
    }

    public void setArrStation(String arrStation) {
        this.arrStation = arrStation;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Train getTrain() {
        return train;
    }

    public String getDepStation() {
        return depStation;
    }

    public String getArrStation() {
        return arrStation;
    }

    public Date getDate() {
        return date;
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
                ", date=" + date +
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
                .append(date, ticket.date)
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
                .append(date)
                .append(seatType)
                .append(surname)
                .append(name)
                .append(passportNumber)
                .toHashCode();
    }
}
