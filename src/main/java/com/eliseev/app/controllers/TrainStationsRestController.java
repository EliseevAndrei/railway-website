package com.eliseev.app.controllers;

import com.eliseev.app.models.TrainStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;


public class TrainStationsRestController {
    private final Logger logger = LoggerFactory.getLogger(TrainStationsRestController.class);

    public TrainStation createFindOne(@RequestParam("id") Integer id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        TrainStation s = null;
        logger.info("get trainStation by id = {}", id);
        return s;
    }
}
