package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.services.TrainRoutePieceService;
import com.eliseev.app.services.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/trains/list/{id}/route-pieces/list", produces = "application/json")
public class TrainRoutePieceRestController extends AbstractRestController<TrainRoutePiece, TrainRoutePieceService> {

    private Logger logger = LoggerFactory.getLogger(TrainRoutePieceRestController.class);

    private TrainService trainService;

    @Autowired
    public TrainRoutePieceRestController(TrainRoutePieceService service, TrainService trainService) {
        super(service);
        this.trainService = trainService;
    }

    @Override
    public List<TrainRoutePiece> list(@PathVariable(required = false) long... id) {
        logger.info("User send GET /trains/list/{}/route-pieces/list", id[0]);
        return service.list(id[0]);
    }

    @Override
    public TrainRoutePiece create(@RequestBody @Valid TrainRoutePiece entity, @PathVariable(required = false) long ...id) {
        logger.info("User send POST /trains/list/{}/route-pieces/list with body {}", id[0], entity);
        entity.getTrain().setId(id[0]);
        TrainRoutePiece trainRoutePiece =  super.create(entity, id);
        logger.info("return trainStation {}", trainRoutePiece);
        return trainRoutePiece;
    }




}
