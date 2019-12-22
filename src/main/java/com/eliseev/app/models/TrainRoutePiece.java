package com.eliseev.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name="train_route_piece")
@NamedEntityGraph(name="fullTrainRoutePiece",
        attributeNodes = {
                @NamedAttributeNode("startStation"),
                @NamedAttributeNode("endStation"),
                @NamedAttributeNode("train")
        })
public class TrainRoutePiece extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="train_id")
    private Train train;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="start_station_id")
    private Station startStation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="end_station_id")
    private Station endStation;

    @Column(name="serial_number")
    private Integer serialNumber;

    private Integer distance;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "trainRoutePiece", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<StationStopTime> stationStopTimes = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "depTrainRoutePiece", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Ticket> startTrainRouteTicket = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "arrTrainRoutePiece", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Ticket> endTrainRouteTicket = new ArrayList<>();

    public TrainRoutePiece(Train train, Station startStation, Station endStation, Integer serialNumber, Integer distance) {
        this.train = train;
        this.startStation = startStation;
        this.endStation = endStation;
        this.serialNumber = serialNumber;
        this.distance = distance;
    }
}
