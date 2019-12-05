package com.eliseev.app.repository.custom;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.repository.IDAO;

import java.util.List;

public interface TicketDAO extends IDAO<Ticket> {
    List<Ticket> listByUserId(Long id);
}
