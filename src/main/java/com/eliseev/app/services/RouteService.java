package com.eliseev.app.services;

import com.eliseev.app.dto.StationDto;
import com.eliseev.app.dto.additional.TrainRouteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RouteService {

    private TrainDateService trainDateService;
    private Logger logger = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    public RouteService(TrainDateService trainDateService) {
        this.trainDateService = trainDateService;
    }

    @Transactional(readOnly = true)
    public List<TrainRouteDTO> findTrainsOnRoute(StationDto depStation, StationDto arrStation, Date date) {

        List<TrainRouteDTO> routesWithTrainDate = trainDateService.getTrainDates(depStation, arrStation, date);
        return trainDateService.setFreePlacesForTrainDates(routesWithTrainDate, depStation, arrStation);
    }

}
