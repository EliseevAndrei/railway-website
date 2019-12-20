package com.eliseev.app.dao;

import com.eliseev.app.TestConfig;
import com.eliseev.app.repository.custom.TicketDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class TicketDAOTest {

    private Logger logger = LoggerFactory.getLogger(TicketDAOTest.class);

    @Autowired
    private TicketDAO ticketDAO;

    @Test
    @Transactional
    public void findOne() {
        assertNotNull(ticketDAO.findOne(1));
        assertNotNull(ticketDAO.findOne(2));
    }

    @Test
    @Transactional
    public void delete() {
        assertEquals(3, ticketDAO.count());
        ticketDAO.delete(1);
        assertEquals(2, ticketDAO.count());
    }

    @Test
    @Transactional
    public void findAll() {
        assertEquals(3, ticketDAO.findAll().size());
    }


    @Test
    @Transactional
    public void findByUserId() {
        assertEquals(1, ticketDAO.listByUserId(6L).size());
        assertEquals(2, ticketDAO.listByUserId(9L).size());
    }




}
