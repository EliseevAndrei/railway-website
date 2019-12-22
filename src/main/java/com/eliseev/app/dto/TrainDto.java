package com.eliseev.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrainDto extends AbstractDto {

    @NotBlank(message = "{train.name.notBlank}")
    private String name;
    private List<TrainRoutePieceDto> trainRoutePieceList;
    private List<CarriageDto> carriages;

}
