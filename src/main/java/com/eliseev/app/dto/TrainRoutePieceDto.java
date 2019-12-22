package com.eliseev.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrainRoutePieceDto extends AbstractDto{

    private Long trainId;
    private StationDto startStation;
    private StationDto endStation;
    @Column(name="serial_number")
    private Integer serialNumber;
    private Integer distance;

}
