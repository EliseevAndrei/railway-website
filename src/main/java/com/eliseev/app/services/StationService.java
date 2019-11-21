package com.eliseev.app.services;

import com.eliseev.app.models.Station;
import com.eliseev.app.repository.custom.StationDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StationService extends AbstractService<Station, StationDAO> {

    @Autowired
    public StationService(StationDAO dao) {
        super(dao);
    }

    private Logger logger = LoggerFactory.getLogger(StationService.class);

    @Transactional
    public List<Station> getUnusedStations(long trainId) {
        return dao.getUnusedStations(trainId);
    }

}
