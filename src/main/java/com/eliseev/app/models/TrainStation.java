package com.eliseev.app.models;

import com.eliseev.app.utils.CustomRestDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class TrainStation extends AbstractEntity implements Serializable {

    /*@Transient
    private long idTrain;*/

    @ManyToOne
    @JoinColumn(name="train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name="station_id")
    private Station station;


    /*@NotBlank(message = "station is required")
    private String station;*/
    /*@Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])\\s(00|0[0-9]|1[0-9]|2[0-3]):(0[0-9]|[0-5][0-9])$")*/
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
    @Column(name="station_serial_number")
    private long stationSerialNumber;
    @Digits(integer = 4, fraction = 0, message = "Кол-во купе должно быть целым числом!")
    @Column(name="coupe_places_amount")
    private int coupe_places_amount;
    @Digits(integer = 4, fraction = 0, message = "Кол-во плацкарта должно быть целым числом!")
    @Column(name="lying_places_amount")
    private int lying_places_amount;
    @Digits(integer = 4, fraction = 0, message = "Кол-во общих должно быть целым числом!")
    @Column(name="common_places_amount")
    private int common_places_amount;

    public TrainStation(long id, Train train, Station station,
                        Date arriveTime, Date departureTime,
                        long stationSerialNumber,
                        int coupe_places_amount,
                        int lying_places_amount,
                        int common_places_amount) {
        super.id = id;
        this.train = train;
        this.station = station;
       /* this.idTrain = idTrain;*/
/*        this.station = station;*/
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.stationSerialNumber = stationSerialNumber;
        this.coupe_places_amount = coupe_places_amount;
        this.lying_places_amount = lying_places_amount;
        this.common_places_amount = common_places_amount;
    }

    public TrainStation(Train train, Station station,
                        Date arriveTime, Date departureTime,
                        long stationSerialNumber,
                        int coupe_places_amount,
                        int lying_places_amount,
                        int common_places_amount) {
        this.train = train;
        this.station = station;
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.stationSerialNumber = stationSerialNumber;
        this.coupe_places_amount = coupe_places_amount;
        this.lying_places_amount = lying_places_amount;
        this.common_places_amount = common_places_amount;
    }

    public TrainStation() {
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Station getStation() {
        return station;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Train getTrain() {
        return train;
    }


    /*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Moscow")*/
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

   /* @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Moscow")*/
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public void setStationSerialNumber(long stationSerialNumber) {
        this.stationSerialNumber = stationSerialNumber;
    }

    public void setCoupe_places_amount(int coupe_places_amount) {
        this.coupe_places_amount = coupe_places_amount;
    }

    public void setLying_places_amount(int lying_places_amount) {
        this.lying_places_amount = lying_places_amount;
    }

    public void setCommon_places_amount(int common_places_amount) {
        this.common_places_amount = common_places_amount;
    }



   /* public String getStation() {
        return station;
    }*/

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    public Date getArriveTime() {
        return arriveTime;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    public Date getDepartureTime() {
        return departureTime;
    }

    public long getStationSerialNumber() {
        return stationSerialNumber;
    }

    public int getCoupe_places_amount() {
        return coupe_places_amount;
    }

    public int getLying_places_amount() {
        return lying_places_amount;
    }

    public int getCommon_places_amount() {
        return common_places_amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainStation that = (TrainStation) o;
        return /*idTrain == that.idTrain &&*/
                stationSerialNumber == that.stationSerialNumber &&
                Objects.equals(station, that.station) &&
                Objects.equals(arriveTime, that.arriveTime) &&
                Objects.equals(departureTime, that.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(/*idTrain,*/ station, arriveTime, departureTime, stationSerialNumber);
    }

    @Override
    public String toString() {
        return "TrainStation{" +
                /*"idTrain=" + idTrain +*/
                ", station='" + station + '\'' +
                ", arriveTime=" + arriveTime +
                ", departureTime=" + departureTime +
                ", stationSerialNumber=" + stationSerialNumber +
                ", coupe_places_amount=" + coupe_places_amount +
                ", lying_places_amount=" + lying_places_amount +
                ", common_places_amount=" + common_places_amount +
                ", id=" + id +
                '}';
    }
}
