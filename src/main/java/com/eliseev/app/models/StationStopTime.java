package com.eliseev.app.models;

import com.eliseev.app.utils.CustomRestDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name="station_stop_time")
public class StationStopTime extends AbstractEntity {

    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    @NotNull(message = "arrive date  is wrong")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="arr_time")
    private Date arriveTime;
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @NotNull(message = "departure date  is wrong")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dep_time")
    private Date departureTime;
    private int coupe_places_amount;
    @Digits(integer = 4, fraction = 0, message = "Кол-во плацкарта должно быть целым числом!")
    @Column(name="lying_places_amount")
    private int lying_places_amount;
    @Digits(integer = 4, fraction = 0, message = "Кол-во общих должно быть целым числом!")
    @Column(name="common_places_amount")
    private int common_places_amount;

    @ManyToOne
    @JoinColumn(name="train_route_piece_id")
    private TrainRoutePiece trainRoutePiece;

    @ManyToOne
    @JoinColumn(name="train_date_id")
    private TrainDate trainDate;

    public StationStopTime(@NotNull(message = "arrive date  is wrong") Date arriveTime,
                           @NotNull(message = "departure date  is wrong") Date departureTime,
                           int coupe_places_amount,
                           @Digits(integer = 4, fraction = 0, message = "Кол-во плацкарта должно быть целым числом!") int lying_places_amount,
                           @Digits(integer = 4, fraction = 0, message = "Кол-во общих должно быть целым числом!") int common_places_amount,
                           TrainRoutePiece trainRoutePiece,
                           TrainDate trainDate) {
        this.arriveTime = arriveTime;
        this.departureTime = departureTime;
        this.coupe_places_amount = coupe_places_amount;
        this.lying_places_amount = lying_places_amount;
        this.common_places_amount = common_places_amount;
        this.trainRoutePiece = trainRoutePiece;
        this.trainDate = trainDate;
    }
}
