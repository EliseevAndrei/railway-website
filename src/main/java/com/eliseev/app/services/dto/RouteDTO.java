package com.eliseev.app.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RouteDTO {

    private Long trainId;
    private String trainName;
    private Long trainDateId;

    private Date depTime;
    private Date arrTime;

    private Long startRoutePieceId;
    private Long endRoutePieceId;
    private int startRoutePieceSerialNumber;
    private int endRoutePieceSerialNumber;

    private int commonPlacesAmount;
    private int lyingPlacesAmount;
    private int coupePlacesAmount;

}