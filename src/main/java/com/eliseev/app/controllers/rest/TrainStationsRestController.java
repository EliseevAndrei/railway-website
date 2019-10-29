package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.TrainStation;
import com.eliseev.app.services.TrainStationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/trains/list/{trainId}/stations/list", produces = "application/json")
public class TrainStationsRestController extends AbstractRestController<TrainStation, TrainStationsService> {

    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    @Autowired
    public TrainStationsRestController(TrainStationsService service) {
        super(service);
    }

    /*@Override
    public List<TrainStation> list() {

        logger.info("User send GET /trains/list/{}/stations/list",  trainId);
        return super.service.list(trainId);
    }*/

}
