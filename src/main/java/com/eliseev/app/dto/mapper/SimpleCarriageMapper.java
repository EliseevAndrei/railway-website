package com.eliseev.app.dto.mapper;

import com.eliseev.app.dto.SimpleCarriageDto;
import com.eliseev.app.models.Carriage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleCarriageMapper extends AbstractMapper<Carriage, SimpleCarriageDto> {

    @Autowired
    public SimpleCarriageMapper(ModelMapper mapper) {
        super(Carriage.class, SimpleCarriageDto.class);
        this.mapper = mapper;
    }

}
