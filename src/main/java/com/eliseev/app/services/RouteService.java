package com.eliseev.app.services;

import com.eliseev.app.models.Route;
import com.eliseev.app.models.Station;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.services.dto.TrainDateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RouteService {

    private TrainService trainService;
    private TrainRoutePieceService trainRoutePieceService;
    private TrainDateService trainDateService;
    private StationService stationService;
    private Logger logger = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    public RouteService(TrainService trainService,
                        TrainRoutePieceService trainRoutePieceService,
                        TrainDateService trainDateService,
                        StationService stationService) {
        this.trainService = trainService;
        this.trainRoutePieceService = trainRoutePieceService;
        this.trainDateService = trainDateService;
        this.stationService = stationService;
    }

    @Transactional(readOnly = true)
    public List<Train> findTrainsOnRoute(String depStation, String arrStation, Date date) {

        Station depStationObj = stationService.getStationByName(depStation);
        Station arrStationObj= stationService.getStationByName(arrStation);

        List<TrainDateDTO> trainDates = trainDateService.getTrainDates(depStationObj, arrStationObj, date);

        List<Route> routes = trainDateService.getFreePlacesForTrainDates(trainDates);




        return null;
    }

}
