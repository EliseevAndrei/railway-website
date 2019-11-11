package com.eliseev.app.services;

import com.eliseev.app.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService extends AbstractService<Ticket> {


    private TrainService trainService;

    @Autowired
    public TicketService(TrainService trainService) {
        this.trainService = trainService;
    }

    @PostConstruct
    public void initialise() {
        Ticket a = new Ticket(1L, trainService.get(1).getName(), 1L, "Минск",
                "Гродно", "Купе", "Елисеев", "Андрей", "askl32f", new Date(), new Date());
        super.entities.put(1L, a);
        Ticket b = new Ticket(2L, trainService.get(2).getName(), 1L, "Минск",
                "Брест", "Плацкарт", "Козловская", "Вероника", "sdf23f", new Date(), new Date());
        super.entities.put(2L, b);
    }

    public List<Ticket> list(long userId) {
        return super.entities.values().stream()
                .filter(e -> e.getUserId() == userId)
                .collect(Collectors.toList());
    }



}
