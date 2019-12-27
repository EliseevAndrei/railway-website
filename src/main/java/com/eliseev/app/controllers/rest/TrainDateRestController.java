package com.eliseev.app.controllers.rest;

import com.eliseev.app.dto.StationStopTimeDto;
import com.eliseev.app.dto.TrainDateDto;
import com.eliseev.app.services.StationStopTimeService;
import com.eliseev.app.services.TicketService;
import com.eliseev.app.services.TrainDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/trains/list/{id}/dates/list", produces = "application/json")
@Slf4j
public class TrainDateRestController extends AbstractRestController<TrainDateDto, TrainDateService> {

    private StationStopTimeService stationStopTimeService;
    private TicketService ticketService;

    @Autowired
    public TrainDateRestController(TrainDateService trainDateService,
                                   StationStopTimeService stationStopTimeService,
                                   TicketService ticketService) {
        super(trainDateService);
        this.stationStopTimeService = stationStopTimeService;
        this.ticketService = ticketService;
    }

    @Override
    public List<TrainDateDto> list(@PathVariable(required = false) long... id) {
        log.info("User send GET /trains/list/{}/dates/list", id[0]);
        return super.service.list(id[0]);
    }

    @GetMapping("/{trainDateId}/intermediate-stops")
    public List<StationStopTimeDto> list(@PathVariable("id") long trainId, @PathVariable("trainDateId") long trainDateId) {
        log.info("User send GET /trains/list/{}/dates/list/{}/intermediate-stops", trainId, trainDateId);
        return stationStopTimeService.findStationsStopTimeByTrainDateId(trainDateId);
    }

    @PostMapping("/stations-sequence")
    public TrainDateDto create(@RequestBody List<StationStopTimeDto> stationStopTimeDTO, @PathVariable(required = false) long ...id) {
        log.info("User send POST /trains/list/{}/stations/list with body {}", id[0], stationStopTimeDTO);
        return service.create(stationStopTimeDTO, id[0]);
    }

    @GetMapping("/{trainDateId}/tickets/amount")
    public Long getTicketAmountOnTrainDate(@PathVariable("id") long trainId, @PathVariable("trainDateId") Long trainDateId) {
        log.info("User send GET /trains/list/{}/dates/list/{}/tickets/amount", trainId, trainDateId);
        long amount = ticketService.ticketAmountOnTrainDate(trainDateId);
        log.info("{}", amount);
        return amount;
    }

}
