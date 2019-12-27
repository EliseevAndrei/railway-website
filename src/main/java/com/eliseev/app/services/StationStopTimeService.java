package com.eliseev.app.services;

import com.eliseev.app.dto.StationStopTimeDto;
import com.eliseev.app.dto.mapper.StationStopTimeMapper;
import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.repository.custom.StationStopTimeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationStopTimeService extends AbstractService<StationStopTime, StationStopTimeDto, StationStopTimeDAO> {

    private StationStopTimeMapper stationStopTimeMapper;

    @Autowired
    public StationStopTimeService(StationStopTimeDAO dao,
                                  StationStopTimeMapper stationStopTimeMapper) {
        super(dao, stationStopTimeMapper);
        this.stationStopTimeMapper = stationStopTimeMapper;
    }


    @Transactional(readOnly = true)
    public List<StationStopTimeDto> findStationsStopTimeByTrainDateId(Long id) {
        return stationStopTimeMapper.toDto(
                dao.findStationsStopTimeByTrainDateId(id, "fullStationStopTime"),
                new ArrayList<>()
        );
    }

    @Transactional(readOnly = true)
    public StationStopTimeDto getStopTimeByTrainRouteIdAndTrainDateId(long trainRoutePieceId, long trainDateId) {
        return stationStopTimeMapper.toDto(
                super.dao.getStopTimeByTrainRouteIdAndTrainDateId(trainRoutePieceId, trainDateId, "fullStationStopTime"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<StationStopTimeDto> list() {
        return stationStopTimeMapper.toDto(
                super.dao.findAll("fullStationStopTime"),
                new ArrayList<>()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public StationStopTimeDto get(long id) {
        return stationStopTimeMapper.toDto(super.dao.findOne(id, "fullStationStopTime"));
    }
}
