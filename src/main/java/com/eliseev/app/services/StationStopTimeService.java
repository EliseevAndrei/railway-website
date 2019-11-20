package com.eliseev.app.services;

import com.eliseev.app.models.StationStopTime;
import com.eliseev.app.repository.custom.StationStopTimeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationStopTimeService extends AbstractService<StationStopTime, StationStopTimeDAO> {

    @Autowired
    public StationStopTimeService(StationStopTimeDAO dao) {
        super(dao);
    }


    public List<StationStopTime> findStationsStopTimeByTrainDateId(Long id) {

        return dao.findStationsStopTimeByTrainDateId(id);
    }
}
