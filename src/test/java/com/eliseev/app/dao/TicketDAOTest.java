package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.models.Ticket;
import com.eliseev.app.repository.custom.TicketDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TicketDAOTest {

    private Logger logger = LoggerFactory.getLogger(TicketDAOTest.class);

    @Autowired
    private TicketDAO ticketDAO;

    @Test
    public void testListTicket() {
        List<Ticket> ticketList = ticketDAO.findAll();

        logger.info("{}", ticketList);


    }

}
