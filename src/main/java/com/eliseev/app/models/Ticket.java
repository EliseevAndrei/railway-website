package com.eliseev.app.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

public class Ticket extends AbstractEntity {

    private Train train;
    private User user;
    private String depStation;
    private String arrStation;
    private Date date;
    private String seatType;

    public Ticket() { }

    public Ticket(long id, Train train, User user, String depStation,
                  String arrStation, Date date, String seatType) {
        super.id = id;
        this.train = train;
        this.user = user;
        this.depStation = depStation;
        this.arrStation = arrStation;
        this.date = date;
        this.seatType = seatType;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDepStation(String depStation) {
        this.depStation = depStation;
    }

    public void setArrStation(String arrStation) {
        this.arrStation = arrStation;
    }

    public Train getTrain() {
        return train;
    }

    public User getUser() {
        return user;
    }

    public String getDepStation() {
        return depStation;
    }

    public String getArrStation() {
        return arrStation;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "train=" + train +
                ", user=" + user +
                ", depStation='" + depStation + '\'' +
                ", arrStation='" + arrStation + '\'' +
                ", date=" + date +
                ", seatType='" + seatType + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        return new EqualsBuilder()
                .append(train, ticket.train)
                .append(user, ticket.user)
                .append(depStation, ticket.depStation)
                .append(arrStation, ticket.arrStation)
                .append(date, ticket.date)
                .append(seatType, ticket.seatType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(train)
                .append(user)
                .append(depStation)
                .append(arrStation)
                .append(date)
                .append(seatType)
                .toHashCode();
    }
}
