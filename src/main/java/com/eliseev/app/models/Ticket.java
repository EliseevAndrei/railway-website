package com.eliseev.app.models;

import java.util.Date;

public class Ticket {
    private Train train;
    private User user;
    private String depStation;
    private String arrStation;
    private String date;
    private String seatType;

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
