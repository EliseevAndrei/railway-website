package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.TrainDate;
import com.eliseev.app.services.TrainDateService;
import com.eliseev.app.services.dto.StationStopTimeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/trains/list/{id}/dates/list", produces = "application/json")
public class TrainDateRestController extends AbstractRestController<TrainDate, TrainDateService> {

    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    @Autowired
    public TrainDateRestController(TrainDateService trainDateService) {
        super(trainDateService);
    }

    @Override
    public List<TrainDate> list(@PathVariable(required = false) long... id) {
        logger.info("User send GET /trains/list/{}/dates/list", id[0]);
        return super.service.list(id[0]);
    }

    @PostMapping("/stations-sequence")
    public TrainDate create(@RequestBody List<StationStopTimeDTO> stationStopTimeDTO, @PathVariable(required = false) long ...id) {
        logger.info("User send POST /trains/list/{}/stations/list with body {}", id[0], stationStopTimeDTO);
        return service.create(stationStopTimeDTO, id[0]);
    }
}
