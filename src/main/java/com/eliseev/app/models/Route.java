package com.eliseev.app.models;

public class Route {

    private Train train;
    private TrainStation depStation;
    private TrainStation arrStation;

    public Route(Train train, TrainStation depStation, TrainStation arrStation) {
        this.train = train;
        this.depStation = depStation;
        this.arrStation = arrStation;
    }

    public Route() {}

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setDepStation(TrainStation depStation) {
        this.depStation = depStation;
    }

    public void setArrStation(TrainStation arrStation) {
        this.arrStation = arrStation;
    }

    public Train getTrain() {
        return train;
    }

    public TrainStation getDepStation() {
        return depStation;
    }

    public TrainStation getArrStation() {
        return arrStation;
    }

    @Override
    public String toString() {
        return "Route{" +
                "train=" + train +
                ", depStation=" + depStation +
                ", arrStation=" + arrStation +
                '}';
    }
}
