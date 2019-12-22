package com.eliseev.app.dto;

import com.eliseev.app.utils.CustomRestDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StationStopTimeDto extends AbstractDto {

    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ", timezone = "Europe/Moscow")
    @NotNull(message = "arrive date  is wrong")
    private Date arriveTime;
    @JsonDeserialize(using = CustomRestDateDeserializer.class)
    @NotNull(message = "departure date  is wrong")
    private Date departureTime;
    private Long trainRoutePieceId;
    private Long trainDateId;


}
