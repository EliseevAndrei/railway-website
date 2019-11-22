package com.eliseev.app.models;

import com.eliseev.app.utils.CustomRestDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="station_stop_time")
public class StationStopTime extends AbstractEntity {

    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @NotNull(message = "arrive date  is wrong")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="arr_time")
    private Date arriveTime;
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @NotNull(message = "departure date  is wrong")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dep_time")
    private Date departureTime;
    @Digits(integer = 4, fraction = 0, message = "Кол-во купе должно быть целым числом!")
    @Column(name="coupe_places_amount")
    private int coupe_places_amount;
    @Digits(integer = 4, fraction = 0, message = "Кол-во плацкарта должно быть целым числом!")
    @Column(name="lying_places_amount")
    private int lying_places_amount;
    @Digits(integer = 4, fraction = 0, message = "Кол-во общих должно быть целым числом!")
    @Column(name="common_places_amount")
    private int common_places_amount;

    @ManyToOne
    @JoinColumn(name="train_station_id")
    private TrainStation trainStation;

    @ManyToOne
    @JoinColumn(name="train_date_id")
    private TrainDate trainDate;


    public StationStopTime() {
    }

    public StationStopTime(@NotNull(message = "arrive date  is wrong") Date arriveTime,
                           @NotNull(message = "departure date  is wrong") Date departureTime,
                           @Digits(integer = 4, fraction = 0, message = "Кол-во купе должно быть целым числом!") int coupe_places_amount,
                           @Digits(integer = 4, fraction = 0, message = "Кол-во плацкарта должно быть целым числом!") int lying_places_amount,
                           @Digits(integer = 4, fraction = 0, message = "Кол-во общих должно быть целым числом!") int common_places_amount,
                           TrainStation trainStation,
                           TrainDate trainDate) {
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.coupe_places_amount = coupe_places_amount;
        this.lying_places_amount = lying_places_amount;
        this.common_places_amount = common_places_amount;
        this.trainStation = trainStation;
        this.trainDate = trainDate;
    }
    public StationStopTime(long id, @NotNull(message = "arrive date  is wrong") Date arriveTime,
                           @NotNull(message = "departure date  is wrong") Date departureTime,
                           @Digits(integer = 4, fraction = 0, message = "Кол-во купе должно быть целым числом!") int coupe_places_amount,
                           @Digits(integer = 4, fraction = 0, message = "Кол-во плацкарта должно быть целым числом!") int lying_places_amount,
                           @Digits(integer = 4, fraction = 0, message = "Кол-во общих должно быть целым числом!") int common_places_amount,
                           TrainStation trainStation,
                           TrainDate trainDate) {
        super.id = id;
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.coupe_places_amount = coupe_places_amount;
        this.lying_places_amount = lying_places_amount;
        this.common_places_amount = common_places_amount;
        this.trainStation = trainStation;
        this.trainDate = trainDate;
    }

    public TrainDate getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(TrainDate trainDate) {
        this.trainDate = trainDate;
    }

    public TrainStation getTrainStation() {
        return trainStation;
    }

    public void setTrainStation(TrainStation trainStation) {
        this.trainStation = trainStation;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public int getCoupe_places_amount() {
        return coupe_places_amount;
    }

    public void setCoupe_places_amount(int coupe_places_amount) {
        this.coupe_places_amount = coupe_places_amount;
    }

    public int getLying_places_amount() {
        return lying_places_amount;
    }

    public void setLying_places_amount(int lying_places_amount) {
        this.lying_places_amount = lying_places_amount;
    }

    public int getCommon_places_amount() {
        return common_places_amount;
    }

    public void setCommon_places_amount(int common_places_amount) {
        this.common_places_amount = common_places_amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof StationStopTime)) return false;

        StationStopTime that = (StationStopTime) o;

        return new EqualsBuilder()
                .append(coupe_places_amount, that.coupe_places_amount)
                .append(lying_places_amount, that.lying_places_amount)
                .append(common_places_amount, that.common_places_amount)
                .append(arriveTime, that.arriveTime)
                .append(departureTime, that.departureTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(arriveTime)
                .append(departureTime)
                .append(coupe_places_amount)
                .append(lying_places_amount)
                .append(common_places_amount)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "StationStopTime{" +
                "arriveTime=" + arriveTime +
                ", departureTime=" + departureTime +
                ", coupe_places_amount=" + coupe_places_amount +
                ", lying_places_amount=" + lying_places_amount +
                ", common_places_amount=" + common_places_amount +
                ", trainStation=" + trainStation +
                ", trainDate=" + trainDate +
                ", id=" + id +
                '}';
    }

}
