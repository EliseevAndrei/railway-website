package com.eliseev.app.services;

import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.custom.TrainDateDAO;
import com.eliseev.app.services.dto.StationsStopTimeDTO;
import com.eliseev.app.services.dto.TrainDateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainDateService extends AbstractService<TrainDate, TrainDateDAO> {

    private StationStopTimeService stationStopTimeService;
    private TrainService trainService;

    @Autowired
    public TrainDateService(TrainDateDAO dao,
                            StationStopTimeService stationStopTimeService,
                            TrainService trainService) {
        super(dao);
        this.stationStopTimeService = stationStopTimeService;
        this.trainService = trainService;
    }

    @Transactional
    public List<TrainDateDTO> getDates(long trainId) {

        List<TrainDateDTO> trainDateDTOS = new ArrayList<>();

        List<TrainDate> trainDates = dao.findDatesByTrainId(trainId);

        TrainDateDTO trainDateDTO;

        for (TrainDate trainDate : trainDates) {

            List<StationStopTime> stationStopTimes = stationStopTimeService.findStationsStopTimeByTrainDateId(trainDate.getId());

            if (stationStopTimes.size() >= 2) {
                trainDateDTO = new TrainDateDTO(stationStopTimes.get(0), stationStopTimes.get(stationStopTimes.size() - 1));
                trainDateDTOS.add(trainDateDTO);
            }
        }

        return trainDateDTOS;
    }

    @Transactional
    public TrainDate create(StationsStopTimeDTO stationsStopTimeDTO, long trainId) {
        Train train = trainService.get(trainId);
        TrainDate trainDate = new TrainDate(train);
        trainDate.getStationStopTimes().addAll(stationsStopTimeDTO.getStationStopTimes());
        return dao.save(trainDate);
    }




}
