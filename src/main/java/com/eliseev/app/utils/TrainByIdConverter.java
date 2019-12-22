package com.eliseev.app.utils;

import com.eliseev.app.dto.SimpleTrainDto;
import com.eliseev.app.services.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TrainByIdConverter implements Converter<String, SimpleTrainDto> {

    private TrainService service;

    @Autowired
    public TrainByIdConverter(TrainService service) {
        this.service = service;
    }

    @Override
    public SimpleTrainDto convert(String id) {
        return service.get(Integer.parseInt(id));
    }

}
