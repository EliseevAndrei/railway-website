package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.Carriage;
import com.eliseev.app.models.Train;
import com.eliseev.app.services.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/trains/list", produces = "application/json")
public class TrainRestController extends AbstractRestController<Train, TrainService> {

    private Logger logger = LoggerFactory.getLogger(TrainRestController.class);

    @Autowired
    public TrainRestController(TrainService service) {
        super(service);
    }

    @GetMapping("/{id}/carriages/onDate/{trainDateId}")
    public List<Carriage> getCarriages(@PathVariable("id") long trainId,
                                       @PathVariable("trainDateId") long trainDateId,
                                       @RequestParam("depRoutePieceNumber") int depRoutePieceNumber,
                                       @RequestParam("arrRoutePieceNumber") int arrRoutePieceNumber) {
        List<Carriage> carriages = service.getCarriages(trainId, trainDateId, depRoutePieceNumber, arrRoutePieceNumber);
        logger.info("{}", carriages);
        return carriages;
    }

    @GetMapping("/{id}/carriages/list")
    public List<Carriage> getCarriages(@PathVariable("id") long trainId) {
        logger.info("user send request GET /trains/list/{}/carriages/list", trainId);
        return service.getCarriages(trainId);
    }


}
