package com.eliseev.app.services;

import com.eliseev.app.models.Train;
import com.eliseev.app.repository.custom.TrainDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TrainService extends AbstractService<Train, TrainDAO> {

    @Autowired
    public TrainService(TrainDAO dao) {
        super(dao);
    }

    private Logger logger = LoggerFactory.getLogger(TrainService.class);

    {
        Train a = new Train(1L,"23B3",10 , 10, 10);
        super.entities.put(1L, a);
        Train b = new Train(2L,"2DS3", 20, 20, 20);
        super.entities.put(2L, b);
    }

    public List<Train> getTrainsOnStations(String depStation, String arrStation, Date date) {

        logger.info("find trains with stations {}, - {} at date {}", depStation, arrStation, date);
        return super.list();
    }


}
