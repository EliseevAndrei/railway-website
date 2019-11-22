package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.TrainStation;
import com.eliseev.app.services.TrainStationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/trains/list/{id}/stations/list", produces = "application/json")
public class TrainStationsRestController extends AbstractRestController<TrainStation, TrainStationsService> {

    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    @Autowired
    public TrainStationsRestController(TrainStationsService service) {
        super(service);
    }

    @Override
    public List<TrainStation> list(@PathVariable(required = false) long... id) {
        logger.info("User send GET /trains/list/{}/stations/list", id[0]);
        return service.list(id[0]);
    }

    @Override
    public TrainStation create(@RequestBody @Valid TrainStation entity, @PathVariable(required = false) long ...id) {
        logger.info("User send POST /trains/list/{}/stations/list with body {}", id[0], entity);
        entity.getTrain().setId(id[0]);
        TrainStation trainStation =  super.create(entity, id);
        logger.info("return trainStation {}", trainStation);
        return trainStation;
    }

}
