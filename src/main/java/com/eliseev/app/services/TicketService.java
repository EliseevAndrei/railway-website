package com.eliseev.app.services;

import com.eliseev.app.models.Route;
import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.Train;

import com.eliseev.app.repository.custom.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
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

    @PostConstruct
    public void initialise() {
        Train train1 = trainService.get(1);
        Train train2 = trainService.get(2);
        Route route = new Route(train1.getId(), train1.getName(), "Минск", new Date(),
                "Гродно", new Date(), 10, 10, 10);
        Ticket a = new Ticket(1L, userService.get(1L), "Елисеев", "Андрей",
                "askl32f", "Купе", route);
        super.entities.put(1L, a);
        Ticket b = new Ticket(1L, userService.get(1L), "Елисеев", "Андрей",
                "askl32f", "Плацкарт", route);
        super.entities.put(2L, b);
    }

    public List<Ticket> list(long userId) {
        return super.entities.values().stream()
                .filter(e -> e.getUser().getId() == userId)
                .collect(Collectors.toList());
    }



}
