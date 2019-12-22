package com.eliseev.app.controllers.rest;

import com.eliseev.app.dto.TrainRoutePieceDto;
import com.eliseev.app.services.TrainRoutePieceService;
import com.eliseev.app.services.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/trains/list/{id}/route-pieces/list", produces = "application/json")
public class TrainRoutePieceRestController extends AbstractRestController<TrainRoutePieceDto, TrainRoutePieceService> {

    private Logger logger = LoggerFactory.getLogger(TrainRoutePieceRestController.class);


    @Autowired
    public TrainRoutePieceRestController(TrainRoutePieceService service, TrainService trainService) {
        super(service);
    }

    @Override
    public List<TrainRoutePieceDto> list(@PathVariable(required = false) long... id) {
        logger.info("User send GET /trains/list/{}/route-pieces/list", id[0]);
        return service.list(id[0]);
    }

    @Override
    public TrainRoutePieceDto create(@RequestBody @Valid TrainRoutePieceDto dto, @PathVariable(required = false) long ...id) {
        logger.info("User send POST /trains/list/{}/route-pieces/list with body {}", id[0], dto);
        dto.setTrainId(id[0]);
        TrainRoutePieceDto trainRoutePiece =  super.create(dto, id);
        logger.info("return trainStation {}", trainRoutePiece);
        return trainRoutePiece;
    }




}
