package com.eliseev.app.services;

import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.repository.custom.TrainDateDAO;
import com.eliseev.app.services.dto.StationStopTimeDTO;
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
    private TrainStationsService trainStationsService;

    @Autowired
    public TrainDateService(TrainDateDAO dao,
                            StationStopTimeService stationStopTimeService,
                            TrainService trainService,
                            TrainStationsService trainStationsService) {
        super(dao);
        this.stationStopTimeService = stationStopTimeService;
        this.trainService = trainService;
        this.trainStationsService = trainStationsService;
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
    public TrainDate create(List<StationStopTimeDTO> stationStopTimeDTOs, long trainId) {

        Train train = trainService.get(trainId);
        TrainDate trainDate = new TrainDate(train);


        StationStopTime stationStopTime;
        List<StationStopTime> stationStopTimes = new ArrayList<>();
        for (StationStopTimeDTO stationStopTimeDTO : stationStopTimeDTOs) {
            stationStopTime = new StationStopTime(stationStopTimeDTO.getArriveTime(), stationStopTimeDTO.getDepartureTime(),
                    train.getCountCoupe(), train.getCountLying(), train.getCountCommon(), trainStationsService.get(stationStopTimeDTO.getTrainStationId()), trainDate);
            stationStopTimes.add(stationStopTime);
        }

        trainDate.getStationStopTimes().addAll(stationStopTimes);
        return dao.save(trainDate);
    }


    public List<TrainDate> list(long trainId) {
        return dao.findDatesByTrainId(trainId);
    }


}
