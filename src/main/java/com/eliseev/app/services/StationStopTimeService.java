package com.eliseev.app.services;

import com.eliseev.app.dto.StationStopTimeDto;
import com.eliseev.app.dto.mapper.StationStopTimeMapper;
import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.repository.custom.StationStopTimeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationStopTimeService extends AbstractService<StationStopTime, StationStopTimeDto, StationStopTimeDAO> {

    private StationStopTimeMapper stationStopTimeMapper;

    @Autowired
    public StationStopTimeService(StationStopTimeDAO dao,
                                  StationStopTimeMapper stationStopTimeMapper) {
        super(dao, stationStopTimeMapper);
        this.stationStopTimeMapper = stationStopTimeMapper;
    }


    public List<StationStopTimeDto> findStationsStopTimeByTrainDateId(Long id) {

        return dao.findStationsStopTimeByTrainDateId(id, "fullStationStopTime")
                .stream()
                .map(e -> stationStopTimeMapper.toDto(e))
                .collect(Collectors.toList());
    }

    public StationStopTimeDto getStopTimeByTrainRouteIdAndTrainDateId(long trainRoutePieceId, long trainDateId) {
        return stationStopTimeMapper.toDto(
                super.dao.getStopTimeByTrainRouteIdAndTrainDateId(trainRoutePieceId, trainDateId, "fullStationStopTime"));
    }

    @Override
    public List<StationStopTimeDto> list() {
        return super.dao.findAll("fullStationStopTime").stream()
                .map(e -> stationStopTimeMapper.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public StationStopTimeDto get(long id) {
        return stationStopTimeMapper.toDto(super.dao.findOne(id, "fullStationStopTime"));
    }
}
