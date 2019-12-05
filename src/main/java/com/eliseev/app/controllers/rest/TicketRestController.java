package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.User;
import com.eliseev.app.services.TicketService;
import com.eliseev.app.services.UserService;
import com.eliseev.app.services.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tickets", produces = "application/json")
public class TicketRestController {

    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    private TicketService ticketService;
    private UserService userService;

    @Autowired
    public TicketRestController(TicketService service) {
        this.ticketService = service;
    }

    @PostMapping("/order")
    public Ticket orderTicket(Authentication authentication,
                              @RequestBody OrderDTO orderDTO) {
        logger.info("{}", orderDTO);
        Ticket ticket = orderDTO.getTicket();
        ticket.setUser((User) authentication.getPrincipal());
        ticketService.create(ticket);
        return ticket;

    }

}
