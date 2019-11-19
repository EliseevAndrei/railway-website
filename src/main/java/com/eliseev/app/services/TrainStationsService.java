package com.eliseev.app.services;


import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainStation;
import com.eliseev.app.repository.custom.TrainStationsDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainStationsService extends AbstractService<TrainStation, TrainStationsDAO> {

    private TrainService trainService;
    private StationService stationService;

    @Autowired
    public TrainStationsService(TrainStationsDAO dao,
                                TrainService trainService,
                                StationService stationService) {
        super(dao);
        this.trainService = trainService;
        this.stationService = stationService;
    }

    private Logger logger = LoggerFactory.getLogger(TrainStationsService.class);

    @PostConstruct
    public void initialise() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd@hh:mm");
        try {
            TrainStation a = new TrainStation(1L,trainService.get(1L), stationService.get(1), simpleDateFormat.parse("2019-09-10@10:10"), simpleDateFormat.parse("2019-09-10@10:10"),
                    1L, 10, 11, 12);
            super.entities.put(1L, a);
            TrainStation b = new TrainStation(2L,trainService.get(1L), stationService.get(2), simpleDateFormat.parse("2019-19-10@19:10"), simpleDateFormat.parse("2019-19-10@19:10"),
                    2L, 10, 10, 10);
            super.entities.put(2L, b);
            TrainStation c = new TrainStation(3L, trainService.get(2L), stationService.get(3), simpleDateFormat.parse("2019-09-10@16:10"), simpleDateFormat.parse("2019-19-10@19:10"),
                    3L, 10, 10, 10);
            super.entities.put(3L, c);
            TrainStation d = new TrainStation(4L, trainService.get(2L), stationService.get(4), simpleDateFormat.parse("2019-09-10@16:10"), simpleDateFormat.parse("2019-19-10@19:10"),
                    4L, 10, 10, 10);
            super.entities.put(4L, c);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<TrainStation> list(long trainId) {
        return super.entities.values().stream()
                .filter(e -> e.getTrain().getId() == trainId)
                .collect(Collectors.toList());
    }
}

