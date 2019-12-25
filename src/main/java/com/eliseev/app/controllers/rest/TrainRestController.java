package com.eliseev.app.controllers.rest;

import com.eliseev.app.dto.CarriageDto;
import com.eliseev.app.dto.SimpleTrainDto;
import com.eliseev.app.dto.TrainDto;
import com.eliseev.app.services.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/trains/list", produces = "application/json")
public class TrainRestController extends AbstractRestController<SimpleTrainDto, TrainService> {

    private Logger logger = LoggerFactory.getLogger(TrainRestController.class);

    @Autowired
    public TrainRestController(TrainService service) {
        super(service);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/full",consumes = "application/json")
    public TrainDto create(@Valid @RequestBody TrainDto dto) {
        logger.info("User send POST /<entities>/list with body {}", dto);
        return service.create(dto);
    }



    @GetMapping("/{id}/carriages/onDate/{trainDateId}")
    public List<CarriageDto> getCarriages(@PathVariable("id") long trainId,
                                       @PathVariable("trainDateId") long trainDateId,
                                       @RequestParam("depRoutePieceNumber") int depRoutePieceNumber,
                                       @RequestParam("arrRoutePieceNumber") int arrRoutePieceNumber) {
        List<CarriageDto> carriages = service.getCarriages(trainId, trainDateId, depRoutePieceNumber, arrRoutePieceNumber);
        logger.info("{}", carriages);
        return carriages;
    }

    @GetMapping("/{id}/carriages/list")
    public List<CarriageDto> getCarriages(@PathVariable("id") long trainId) {
        logger.info("user send request GET /trains/list/{}/carriages/list", trainId);
        return service.getCarriages(trainId);
    }


}
