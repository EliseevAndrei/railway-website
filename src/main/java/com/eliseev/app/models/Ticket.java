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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NamedEntityGraph(name="fullTicket",
        attributeNodes = {
                @NamedAttributeNode("user"),
                @NamedAttributeNode(value="trainDate", subgraph = "train"),
                @NamedAttributeNode(value="depTrainRoutePiece", subgraph = "depStation"),
                @NamedAttributeNode(value="arrTrainRoutePiece", subgraph = "arrStation"),
                @NamedAttributeNode(value="place", subgraph = "carriage")},
        subgraphs = {
                @NamedSubgraph(name="depStation", attributeNodes = @NamedAttributeNode("startStation")),
                @NamedSubgraph(name="arrStation", attributeNodes = @NamedAttributeNode("endStation")),
                @NamedSubgraph(name="carriage", attributeNodes = @NamedAttributeNode("carriage")),
                @NamedSubgraph(name="train", attributeNodes = @NamedAttributeNode("train"))
        })
public class Ticket extends AbstractEntity {

    @NotBlank(message = "surname is required")
    private String surname;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "passportNumber is required")
    @Column(name="passport_number")
    private String passportNumber;

    @Column(name="dep_time")
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depTime;
    @Column(name="arr_time")
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id")
    private User user;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="train_id")
    private Train train;*/

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="train_date_id")
    private TrainDate trainDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="dep_train_route_piece_id")
    private TrainRoutePiece depTrainRoutePiece;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="arr_train_route_piece_id")
    private TrainRoutePiece arrTrainRoutePiece;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="place_id")
    private Place place;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="carriage_id")
    private Carriage carriage;*/

}
