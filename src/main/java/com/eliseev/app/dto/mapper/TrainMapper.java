package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.TrainDto;
import com.eliseev.app.models.Train;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class TrainMapper extends AbstractMapper<Train, TrainDto> {

    private final ModelMapper mapper;

    public TrainMapper(ModelMapper mapper) {
        super(Train.class, TrainDto.class);
        this.mapper = mapper;
    }

}