package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TicketDAO;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDAOImpl extends AbstractDAO<Ticket>
        implements TicketDAO {

    public TicketDAOImpl() {
        super(Ticket.class);
    }
}
