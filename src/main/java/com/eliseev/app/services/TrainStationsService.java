package com.eliseev.app.services;

import com.eliseev.app.models.Station;
import com.eliseev.app.models.TrainStation;
import com.eliseev.app.repository.custom.StationStopTimeDAO;
import com.eliseev.app.repository.custom.TrainStationsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TrainStationsService extends AbstractService<TrainStation, TrainStationsDAO> {

    private TrainService trainService;
    private StationService stationService;
    private StationStopTimeDAO stationStopTimeDAO;

    @Autowired
    public TrainStationsService(TrainStationsDAO dao,
                                TrainService trainService,
                                StationService stationService,
                                StationStopTimeDAO stationStopTimeDAO) {
        super(dao);
        this.trainService = trainService;
        this.stationService = stationService;
        this.stationStopTimeDAO = stationStopTimeDAO;
    }

    private Logger logger = LoggerFactory.getLogger(TrainStationsService.class);

    @Transactional
    public List<TrainStation> list(long trainId) {
        return super.dao.findByTrainId(trainId);
    }

}

