package com.eliseev.app.services;


import com.eliseev.app.models.TrainStation;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrainStationsService extends AbstractService<TrainStation> {

    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        try {
            TrainStation a = new TrainStation(1, "Минск", simpleDateFormat.parse("10.10.19 18:30"), simpleDateFormat.parse("10.10.19 18:35"),
                    1L, 10, 10, 10);
            super.entities.put(1L, a);
            TrainStation b = new TrainStation(2, "Гродно", simpleDateFormat.parse("10.10.19 18:30"), simpleDateFormat.parse("10.10.19 18:35"),
                    2L, 10, 10, 10);
            super.entities.put(2L, b);
            TrainStation c = new TrainStation(3, "Брест", simpleDateFormat.parse("10.10.19 18:30"), simpleDateFormat.parse("10.10.19 18:35"),
                    3L, 10, 10, 10);
            super.entities.put(3L, c);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

