package com.eliseev.app.services;

import com.eliseev.app.models.Station;
import com.eliseev.app.repository.custom.StationDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService extends AbstractService<Station, StationDAO> {

    @Autowired
    public StationService(StationDAO dao) {
        super(dao);
    }

    private Logger logger = LoggerFactory.getLogger(StationService.class);

    {
        Station a = new Station(1L, "Минск");
        super.entities.put(1L, a);
        Station b = new Station(2L, "Брест");
        super.entities.put(2L, b);
        Station c = new Station(3L, "Витебск");
        super.entities.put(3L, c);
        Station d = new Station(4L, "Гомель");
        super.entities.put(4L, d);
    }

}
