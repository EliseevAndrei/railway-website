package com.eliseev.app.services;

import com.eliseev.app.models.Station;
import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.custom.TrainDateDAO;
import com.eliseev.app.services.dto.RouteDTO;
import com.eliseev.app.services.dto.StationStopTimeDTO;
import com.eliseev.app.services.dto.TrainRouteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrainDateService extends AbstractService<TrainDate, TrainDateDAO> {

    private StationStopTimeService stationStopTimeService;
    private TrainService trainService;
    private TrainRoutePieceService trainRoutePieceService;
    private StationService stationService;

    @Autowired
    public TrainDateService(TrainDateDAO dao,
                            StationStopTimeService stationStopTimeService,
                            TrainService trainService,
                            TrainRoutePieceService trainRoutePieceService,
                            StationService stationService) {
        super(dao);
        this.stationStopTimeService = stationStopTimeService;
        this.trainService = trainService;
        this.trainRoutePieceService = trainRoutePieceService;
        this.stationService = stationService;
    }

    @Transactional
    public List<TrainRouteDTO> getDates(long trainId) {

        List<TrainRouteDTO> trainDateDTOS = new ArrayList<>();

        List<TrainDate> trainDates = dao.findDatesByTrainId(trainId);

        TrainRouteDTO trainDateDTO;

        for (TrainDate trainDate : trainDates) {

            List<StationStopTime> stationStopTimes = stationStopTimeService.findStationsStopTimeByTrainDateId(trainDate.getId());

            if (stationStopTimes.size() >= 2) {
                trainDateDTO = new TrainRouteDTO(stationStopTimes.get(0), stationStopTimes.get(stationStopTimes.size() - 1));
                trainDateDTOS.add(trainDateDTO);
            }
        }

        return trainDateDTOS;
    }

    @Transactional
    public TrainDate create(List<StationStopTimeDTO> stationStopTimeDTOs, long trainId) {

        Train train = trainService.get(trainId);
        TrainDate trainDate = new TrainDate(train);


        StationStopTime stationStopTime;
        List<StationStopTime> stationStopTimes = new ArrayList<>();
        for (StationStopTimeDTO stationStopTimeDTO : stationStopTimeDTOs) {
            stationStopTime = new StationStopTime(stationStopTimeDTO.getArriveTime(), stationStopTimeDTO.getDepartureTime(),
                    train.getCountCoupe(), train.getCountLying(), train.getCountCommon(), trainRoutePieceService.get(stationStopTimeDTO.getTrainStationId()), trainDate);
            stationStopTimes.add(stationStopTime);
        }

        trainDate.getStationStopTimes().addAll(stationStopTimes);
        return dao.save(trainDate);
    }


    public List<TrainDate> list(long trainId) {
        return dao.findDatesByTrainId(trainId);
    }

    @Transactional(readOnly = true)
    public List<RouteDTO> getTrainDates(Station depStation, Station arrStation, Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String depDate = dateFormat.format(date);
        String depDateLeftBorder = depDate + " 00:00:00";
        String depDateRightBorder = depDate + " 23:59:59";

        logger.info("{} {}", depDateLeftBorder, depDateRightBorder);

        return  super.dao.getTrainDates(depStation, arrStation, depDateLeftBorder, depDateRightBorder);
    }

    @Transactional(readOnly = true)
    public List<RouteDTO> setFreePlacesForTrainDates(List<RouteDTO> routesWithTrainDate) {

        for (RouteDTO routeDTO : routesWithTrainDate) {

        }
        return null;
    }


}
