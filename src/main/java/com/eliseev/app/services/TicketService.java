package com.eliseev.app.services;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.repository.custom.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService extends AbstractService<Ticket, TicketDAO> {


    private TrainService trainService;
    private UserService userService;

    @Autowired
    public TicketService(TrainService trainService,
                         TicketDAO ticketDAO,
                         UserService userService) {
        super(ticketDAO);
        this.trainService = trainService;
        this.userService = userService;
    }


    public List<Ticket> list(long userId) {
        return super.entities.values().stream()
                .filter(e -> e.getUser().getId() == userId)
                .collect(Collectors.toList());
    }



}
