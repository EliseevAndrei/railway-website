package com.eliseev.app.services;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TicketService extends AbstractService<Ticket> {


    private UserService userService;
    private TrainService trainService;

    @Autowired
    public TicketService(UserService userService,
                         TrainService trainService) {
        this.userService = userService;
        this.trainService = trainService;
    }

    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dateFormat.parse("10-10-2018");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Ticket a = new Ticket(1L, trainService.get(1), userService.get(1),
                "Минск", "Гродно", date, "Купе");
        super.entities.put(1L, a);
        Ticket b = new Ticket(2L, trainService.get(2), userService.get(1),
                "Минск", "Брест", date, "Плацкарт");
        super.entities.put(2L, b);
    }

}
