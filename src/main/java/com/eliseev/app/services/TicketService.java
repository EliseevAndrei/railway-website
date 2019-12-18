package com.eliseev.app.services;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.User;
import com.eliseev.app.repository.custom.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService extends AbstractService<Ticket, TicketDAO> {

    @Autowired
    public TicketService(TicketDAO ticketDAO) {
        super(ticketDAO);
    }

    public List<Ticket> listForUser(User user) {
        return dao.listByUserId(user.getId());
    }

}
