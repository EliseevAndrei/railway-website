package com.eliseev.app.controllers.rest;

import com.eliseev.app.dto.TicketDto;
import com.eliseev.app.models.User;
import com.eliseev.app.services.TicketService;
import com.eliseev.app.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tickets/list", produces = "application/json")
public class TicketRestController {

    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    private UserService userService;
    private TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService service) {
        this.ticketService = service;
    }


    @PostMapping("/order")
    public TicketDto orderTicket(Authentication authentication,
                              @RequestBody TicketDto ticket) {
        logger.info("{}", ticket);
        ticket.setUserId(((User) authentication.getPrincipal()).getId());
        return ticketService.create(ticket);
    }




}
