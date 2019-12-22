package com.eliseev.app.dto;

import com.eliseev.app.utils.CustomRestDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TicketDto extends AbstractDto {

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

    private Long userId;
    private Long trainDateId;
    private Long depTrainRoutePieceId;
    private Long arrTrainRoutePieceId;

    private SimpleTrainDto train;
    private StationDto depStation;
    private StationDto arrStation;
    private SimpleCarriageDto carriage;

    private PlaceDto place;
}
