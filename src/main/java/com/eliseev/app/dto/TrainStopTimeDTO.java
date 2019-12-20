package com.eliseev.app.dto;

import com.eliseev.app.models.StationStopTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TrainStopTimeDTO implements Serializable {

    private StationStopTime firstStation;
    private StationStopTime lastStation;

}
