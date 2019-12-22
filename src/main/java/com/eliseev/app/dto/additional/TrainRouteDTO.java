package com.eliseev.app.dto.additional;

import com.eliseev.app.models.Train;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TrainRouteDTO {

    private Train train;
    private Long trainDateId;

    private Date depTime;
    private Date arrTime;

    private Long startRoutePieceId;
    private Long endRoutePieceId;
    private int startRoutePieceSerialNumber;
    private int endRoutePieceSerialNumber;

    private Map<String, Integer> freePlaces = new LinkedHashMap<>();

}
