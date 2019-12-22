package com.eliseev.app.repository.custom.impl;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.repository.AbstractDAO;
import com.eliseev.app.repository.custom.TicketDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TicketDAOImpl extends AbstractDAO<Ticket>
        implements TicketDAO {

    public TicketDAOImpl() {
        super(Ticket.class);
    }

    @Override
    public List<Ticket> listByUserId(Long id, String graphName) {
        Query query = entityManager.createQuery("select t from Ticket t where t.user.id = :id", Ticket.class)
                .setParameter("id", id);
        if (graphName.length() != 0) {
            EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
            query.setHint("javax.persistence.fetchgraph", entityGraph);
        }
        return query.getResultList();

    }
    
}
