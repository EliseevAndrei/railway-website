package com.eliseev.app.services;

import com.eliseev.app.dto.TicketDto;
import com.eliseev.app.dto.mapper.TicketMapper;
import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.User;
import com.eliseev.app.repository.custom.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService extends AbstractService<Ticket, TicketDto, TicketDAO> {

    private TicketMapper ticketMapper;

    @Autowired
    public TicketService(TicketDAO ticketDAO,
                         TicketMapper ticketMapper) {
        super(ticketDAO, ticketMapper);
        this.ticketMapper = ticketMapper;
    }

    public List<TicketDto> listForUser(User user) {
        return ticketMapper.toDto(
                dao.listByUserId(user.getId(), "fullTicket"),
                new ArrayList<>()
        );
    }

    @Override
    public List<TicketDto> list() {
        return ticketMapper.toDto(
                super.dao.findAll("fullTicket"),
                new ArrayList<>()
        );
    }

    @Override
    public TicketDto get(long id) {
        return ticketMapper.toDto(
                super.dao.findOne(id, "fullTicket"));
    }
}
