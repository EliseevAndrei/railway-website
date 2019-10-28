package com.eliseev.app.services;

import com.eliseev.app.models.Train;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;


@Service
public class TrainService extends AbstractService<Train> {

    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        Train a = new Train(1L,"1",10 , 10, 10);
        super.entities.put(1L, a);
        Train b = new Train(1,"2", 20, 20, 20);
        super.entities.put(2L, b);
    }

}
