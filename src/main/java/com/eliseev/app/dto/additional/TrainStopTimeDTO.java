package com.eliseev.app.dto.additional;

import com.eliseev.app.dto.StationStopTimeDto;
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

    private StationStopTimeDto firstStation;
    private StationStopTimeDto lastStation;

}
