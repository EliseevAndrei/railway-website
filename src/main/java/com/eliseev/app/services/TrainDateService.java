package com.eliseev.app.services;

import com.eliseev.app.dto.StationDto;
import com.eliseev.app.dto.StationStopTimeDto;
import com.eliseev.app.dto.TrainDateDto;
import com.eliseev.app.dto.TrainRoutePieceDto;
import com.eliseev.app.dto.additional.StationStopTimeDTO;
import com.eliseev.app.dto.additional.TrainRouteDTO;
import com.eliseev.app.dto.additional.TrainStopTimeDTO;
import com.eliseev.app.dto.mapper.StationMapper;
import com.eliseev.app.dto.mapper.TrainDateMapper;
import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.custom.TrainDAO;
import com.eliseev.app.repository.custom.TrainDateDAO;
import com.eliseev.app.repository.custom.TrainRoutePieceDAO;
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
    private TrainDAO trainDAO;
    private TrainRoutePieceDAO trainRoutePieceDAO;
    private TrainDateMapper trainDateMapper;
    private StationMapper stationMapper;

    @Autowired
    public TrainDateService(TrainDateDAO dao,
                            StationStopTimeService stationStopTimeService,
                            TrainService trainService,
                            TrainRoutePieceService trainRoutePieceService,
                            TrainDAO trainDAO,
                            TrainRoutePieceDAO trainRoutePieceDAO,
                            TrainDateMapper trainDateMapper,
                            StationMapper stationMapper) {
        super(dao, trainDateMapper);
        this.stationStopTimeService = stationStopTimeService;
        this.trainService = trainService;
        this.trainRoutePieceService = trainRoutePieceService;
        this.trainDAO = trainDAO;
        this.trainRoutePieceDAO = trainRoutePieceDAO;
        this.trainDateMapper = trainDateMapper;
        this.stationMapper = stationMapper;
    }

    @Transactional
    public List<TrainStopTimeDTO> getDates(long trainId) {

        List<TrainStopTimeDTO> trainDateDTOS = new ArrayList<>();
        List<TrainDate> trainDates = dao.findDatesByTrainId(trainId, "fullTrainDate");
        TrainStopTimeDTO trainDateDTO;

        for (TrainDate trainDate : trainDates) {

            List<StationStopTimeDto> stationStopTimes = stationStopTimeService.findStationsStopTimeByTrainDateId(trainDate.getId());

            if (stationStopTimes.size() >= 1) {
                trainDateDTO = new TrainStopTimeDTO(
                        stationStopTimes.get(0),
                        stationStopTimes.get(stationStopTimes.size() - 1)
                );
                trainDateDTOS.add(trainDateDTO);
            }
        }

        return trainDateDTOS;
    }

    @Transactional
    public TrainDateDto create(List<StationStopTimeDTO> stationStopTimeDTOs, long trainId) {


        Train train = trainDAO.findOne(trainId);
        TrainDate trainDate = new TrainDate(train);


        StationStopTime stationStopTime;
        List<StationStopTime> stationStopTimes = new ArrayList<>();
        for (StationStopTimeDTO stationStopTimeDTO : stationStopTimeDTOs) {
            stationStopTime = new StationStopTime(stationStopTimeDTO.getArriveTime(), stationStopTimeDTO.getDepartureTime(),
                    trainRoutePieceDAO.findOne(stationStopTimeDTO.getTrainStationId()), trainDate);
            stationStopTimes.add(stationStopTime);
        }

        trainDate.getStationStopTimes().addAll(stationStopTimes);
        return trainDateMapper.toDto(dao.save(trainDate));
    }

    public List<TrainDateDto> list(long trainId) {
        return dao.findDatesByTrainId(trainId, "fullTraindate").stream()
                .map(e -> trainDateMapper.toDto(e))
                .collect(Collectors.toList());
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

    @Transactional(readOnly = true)
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
    public List<TrainDateDto> list() {
        return super.dao.findAll("fullTrainDate")
                .stream()
                .map(e -> trainDateMapper.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public TrainDateDto get(long id) {
        return trainDateMapper.toDto(super.dao.findOne(id, "fullTrainDate"));
    }
}
