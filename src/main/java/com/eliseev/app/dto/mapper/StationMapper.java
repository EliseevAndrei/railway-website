package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.StationDto;
import com.eliseev.app.models.Station;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StationMapper extends AbstractMapper<Station, StationDto> {

    @Autowired
    public StationMapper(ModelMapper mapper) {
        super(Station.class, StationDto.class);
        this.mapper = mapper;
    }

}
