package com.eliseev.app.models;

import com.eliseev.app.utils.CustomRestDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Route {

    @ManyToOne
    @JoinColumn(name="train_id")
    private Train train;
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dep_time")
    private Date depTime;
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    @Column(name="arr_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrTime;
    @Column(name="coupe_places_amount")
    private int coupePlacesAmount;
    @Column(name="lying_places_amount")
    private int lyingPlacesAmount;
    @Column(name="common_places_amount")
    private int commonPlacesAmount;

    public Route(Train train, int coupePlacesAmount, int lyingPlacesAmount, int commonPlacesAmount) {
        this.train = train;
        this.coupePlacesAmount = coupePlacesAmount;
        this.lyingPlacesAmount = lyingPlacesAmount;
        this.commonPlacesAmount = commonPlacesAmount;
    }
}
