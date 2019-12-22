package com.eliseev.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CarriageDto extends AbstractDto{

    private Integer number;
    private String type;
    private List<PlaceDto> places;
    private Long trainId;

}
