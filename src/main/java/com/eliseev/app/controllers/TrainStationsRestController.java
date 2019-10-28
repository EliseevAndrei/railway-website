package com.eliseev.app.controllers;

import com.eliseev.app.models.TrainStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/trainStations")
public class TrainStationsRestController {
    private final Logger logger = LoggerFactory.getLogger(TrainStationsRestController.class);
    @GetMapping("/findOne")
    public TrainStation createFindOne(@RequestParam("id") Integer id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        TrainStation s = null;
        try {
            s = new TrainStation(1, "Минск", simpleDateFormat.parse("10.10.19 18:30"), simpleDateFormat.parse("10.10.19 18:35"),
                    1, 10,10,10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Siswa tmp = siswaDao.getOne(id);
        //init
        /*s.setId(tmp.getId());
        s.setNama(tmp.getNama());
        s.setJurusan(tmp.getJurusan());*/
        logger.info("get trainStation by id = {}", id);
        return s;
    }
}
