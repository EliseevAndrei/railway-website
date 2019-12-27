package com.eliseev.app.controllers.rest;

import com.eliseev.app.dto.StationDto;
import com.eliseev.app.services.StationService;
import com.eliseev.app.services.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/stations/list", produces = "application/json")
@Slf4j
public class StationRestController extends AbstractRestController<StationDto, StationService> {

    private TicketService ticketService;

    @Autowired
    public StationRestController(StationService service,
                                 TicketService ticketService) {
        super(service);
        this.ticketService = ticketService;
    }

    @GetMapping("/{stationId}/tickets/amount")
    public Long getTicketAmountOnStation(@PathVariable("stationId") Long stationId) {
        log.info("user send GET /stations/list/{trainId}/tickets/amount");
        long amount = ticketService.ticketAmountOnStation(stationId);
        log.info("{}", amount);
        return amount;
    }

}
