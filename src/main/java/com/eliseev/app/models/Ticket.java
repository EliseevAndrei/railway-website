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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name="train_date_id")
    private TrainDate trainDate;

    @ManyToOne
    @JoinColumn(name="dep_train_route_piece_id")
    private TrainRoutePiece depTrainRoutePiece;

    @ManyToOne
    @JoinColumn(name="arr_train_route_piece_id")
    private TrainRoutePiece arrTrainRoutePiece;

    @ManyToOne
    @JoinColumn(name="place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name="carriage_id")
    private Carriage carriage;
}
