package com.eliseev.app.services;

import com.eliseev.app.dto.StationDto;
import com.eliseev.app.dto.mapper.StationMapper;
import com.eliseev.app.models.Station;
import com.eliseev.app.repository.custom.StationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService extends AbstractService<Station, StationDto, StationDAO> {

    private StationMapper stationMapper;

    @Autowired
    public StationService(StationDAO dao, StationMapper stationMapper) {
        super(dao, stationMapper);
        this.stationMapper = stationMapper;
    }


}
