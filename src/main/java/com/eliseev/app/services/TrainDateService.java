package com.eliseev.app.services;

import com.eliseev.app.dto.StationDto;
import com.eliseev.app.dto.StationStopTimeDto;
import com.eliseev.app.dto.TrainDateDto;
import com.eliseev.app.dto.TrainRoutePieceDto;
import com.eliseev.app.dto.TrainRouteDTO;
import com.eliseev.app.dto.mapper.StationMapper;
import com.eliseev.app.dto.mapper.StationStopTimeMapper;
import com.eliseev.app.dto.mapper.TrainDateMapper;
import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.custom.TrainDateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainDateService extends AbstractService<TrainDate, TrainDateDto, TrainDateDAO> {

    private StationStopTimeService stationStopTimeService;
    private TrainService trainService;
    private TrainRoutePieceService trainRoutePieceService;
    private TrainDateMapper trainDateMapper;
    private StationMapper stationMapper;
    private StationStopTimeMapper stationStopTimeMapper;

    @Autowired
    public TrainDateService(TrainDateDAO dao,
                            StationStopTimeService stationStopTimeService,
                            TrainService trainService,
                            TrainRoutePieceService trainRoutePieceService,
                            TrainDateMapper trainDateMapper,
                            StationMapper stationMapper,
                            StationStopTimeMapper stationStopTimeMapper) {
        super(dao, trainDateMapper);
        this.stationStopTimeService = stationStopTimeService;
        this.trainService = trainService;
        this.trainRoutePieceService = trainRoutePieceService;
        this.trainDateMapper = trainDateMapper;
        this.stationMapper = stationMapper;
        this.stationStopTimeMapper = stationStopTimeMapper;
    }

    @Transactional(readOnly = true)
    public List<StationStopTimeDto[]> getDates(long trainId) {

        List<StationStopTimeDto[]> trainDateDTOS = new ArrayList<>();
        List<TrainDate> trainDates = dao.findDatesByTrainId(trainId, "fullTrainDate");
        StationStopTimeDto[] firstAndLastStations;

        for (TrainDate trainDate : trainDates) {
            List<StationStopTimeDto> stationStopTimes = stationStopTimeService.findStationsStopTimeByTrainDateId(trainDate.getId());

            if (stationStopTimes.size() >= 1) {
                firstAndLastStations = new StationStopTimeDto[2];
                firstAndLastStations[0] = stationStopTimes.get(0);
                firstAndLastStations[1] = stationStopTimes.get(stationStopTimes.size() - 1);

                trainDateDTOS.add(firstAndLastStations);
            }
        }
        return trainDateDTOS;
    }

    @Transactional
    public TrainDateDto create(List<StationStopTimeDto> stationStopTimeDTOs, long trainId) {

        Train train = new Train();
        train.setId(trainId);
        TrainDate trainDate = new TrainDate(train);

        List<StationStopTime> stationStopTimes =
                stationStopTimeMapper.toEntity(stationStopTimeDTOs, new ArrayList<>());

        stationStopTimes.forEach(e ->  e.setTrainDate(trainDate));

        trainDate.getStationStopTimes().addAll(stationStopTimes);
        return trainDateMapper.toDto(dao.save(trainDate));
    }


    public List<TrainDateDto> list(long trainId) {
        return trainDateMapper.toDto(
                dao.findDatesByTrainId(trainId, "fullTrainDate"),
                new ArrayList<>()
        );
    }

    @Transactional(readOnly = true)
    public List<TrainRouteDTO> getTrainDates(StationDto depStation, StationDto arrStation, Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String depDate = dateFormat.format(date);
        String depDateLeftBorder = depDate + " 00:00:00";
        String depDateRightBorder = depDate + " 23:59:59";

        logger.info("{} {}", depDateLeftBorder, depDateRightBorder);

        return super.dao.getTrainDates(stationMapper.toEntity(depStation), stationMapper.toEntity(arrStation),
                depDateLeftBorder, depDateRightBorder);
    }

    @Transactional
    public List<TrainRouteDTO> setFreePlacesForTrainDates(List<TrainRouteDTO> routesWithTrainDate, StationDto depStationObj, StationDto arrStationObj) {

        TrainRoutePieceDto depTrainRoutePiece, arrTrainRoutePiece;
        StationStopTimeDto depStationStopTime, arrStationStopTime;

        for (TrainRouteDTO trainRouteDTO : routesWithTrainDate) {

            depTrainRoutePiece = trainRoutePieceService.
                    findByTrainIdAndStartStationId(trainRouteDTO.getTrain().getId(), depStationObj.getId());
            arrTrainRoutePiece = trainRoutePieceService.
                    findByTrainIdAndEndStationId(trainRouteDTO.getTrain().getId(), arrStationObj.getId());
            depStationStopTime = stationStopTimeService.
                    getStopTimeByTrainRouteIdAndTrainDateId(depTrainRoutePiece.getId(), trainRouteDTO.getTrainDateId());
            arrStationStopTime = stationStopTimeService.
                    getStopTimeByTrainRouteIdAndTrainDateId(arrTrainRoutePiece.getId(), trainRouteDTO.getTrainDateId());

            trainRouteDTO.setEndRoutePieceId(arrTrainRoutePiece.getId());
            trainRouteDTO.setEndRoutePieceSerialNumber(arrTrainRoutePiece.getSerialNumber());
            trainRouteDTO.setStartRoutePieceId(depTrainRoutePiece.getId());
            trainRouteDTO.setStartRoutePieceSerialNumber(depTrainRoutePiece.getSerialNumber());
            trainRouteDTO.setDepTime(depStationStopTime.getDepartureTime());
            trainRouteDTO.setArrTime(arrStationStopTime.getArriveTime());

            Map<String, Integer> places = this.trainService.
                    getFreePlacesAmountForTrainRoute(
                            trainRouteDTO.getTrain().getId(),
                            trainRouteDTO.getTrainDateId(),
                            depTrainRoutePiece.getSerialNumber(),
                            arrTrainRoutePiece.getSerialNumber());

            trainRouteDTO.setFreePlaces(places);

        }

        return routesWithTrainDate;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainDateDto> list() {
        return super.dao.findAll("fullTrainDate")
                .stream()
                .map(e -> trainDateMapper.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TrainDateDto get(long id) {
        return trainDateMapper.toDto(super.dao.findOne(id, "fullTrainDate"));
    }
}
