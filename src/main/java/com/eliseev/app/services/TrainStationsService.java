package com.eliseev.app.services;


import com.eliseev.app.models.TrainStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainStationsService extends AbstractService<TrainStation> {

    private Logger logger = LoggerFactory.getLogger(TrainStationsService.class);

    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd@hh:mm");
        try {
            TrainStation a = new TrainStation(1L,1L, "Минск", simpleDateFormat.parse("2019-09-10@10:10"), simpleDateFormat.parse("2019-09-10@10:10"),
                    1L, 10, 11, 12);
            super.entities.put(1L, a);
            TrainStation b = new TrainStation(2L, 1L, "Гродно", simpleDateFormat.parse("2019-19-10@19:10"), simpleDateFormat.parse("2019-19-10@19:10"),
                    2L, 10, 10, 10);
            super.entities.put(2L, b);
            TrainStation c = new TrainStation(3L, 2L, "Брест", simpleDateFormat.parse("2019-09-10@16:10"), simpleDateFormat.parse("2019-19-10@19:10"),
                    3L, 10, 10, 10);
            super.entities.put(3L, c);
            TrainStation d = new TrainStation(4L, 2L, "Витебск", simpleDateFormat.parse("2019-09-10@16:10"), simpleDateFormat.parse("2019-19-10@19:10"),
                    4L, 10, 10, 10);
            super.entities.put(4L, c);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<TrainStation> list(long trainId) {
        return super.entities.values().stream()
                .filter(e -> e.getIdTrain() == trainId)
                .collect(Collectors.toList());
    }
}

