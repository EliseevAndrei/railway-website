package com.eliseev.app.controllers.rest;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.User;
import com.eliseev.app.services.OrderService;
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
@RequestMapping(path = "/tickets/list", produces = "application/json")
public class TicketRestController {

    private Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

    private UserService userService;
    private OrderService orderService;
    private TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService service,
                                OrderService orderService) {
        this.ticketService = service;
        this.orderService = orderService;
    }


    @PostMapping("/order")
    public Ticket orderTicket(Authentication authentication,
                              @RequestBody OrderDTO orderDTO) {
        logger.info("{}", orderDTO);
        orderDTO.getTicket().setUser((User) authentication.getPrincipal());
        return orderService.orderTicket(orderDTO);
    }




}
