package com.eliseev.app.services;

import com.eliseev.app.dto.TicketDto;
import com.eliseev.app.dto.mapper.TicketMapper;
import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.User;
import com.eliseev.app.repository.custom.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return dao.listByUserId(user.getId(), "fullTicket")
                .stream()
                .map(e -> ticketMapper.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> list() {
        return super.dao.findAll("fullTicket").stream()
                .map(e -> ticketMapper.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto get(long id) {
        return ticketMapper.toDto(
                super.dao.findOne(id, "fullTicket"));
    }
}
