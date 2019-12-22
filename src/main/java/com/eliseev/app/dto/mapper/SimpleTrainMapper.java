package com.eliseev.app.dto.mapper;


import com.eliseev.app.dto.SimpleTrainDto;
import com.eliseev.app.models.Train;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleTrainMapper extends AbstractMapper<Train, SimpleTrainDto> {

    @Autowired
    public SimpleTrainMapper(ModelMapper mapper) {
        super(Train.class, SimpleTrainDto.class);
        this.mapper = mapper;
    }

}
