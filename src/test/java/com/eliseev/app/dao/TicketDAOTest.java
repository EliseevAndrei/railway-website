package com.eliseev.app.dao;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.repository.custom.TicketDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoTestConfig.class)
public class TicketDAOTest {

    private Logger logger = LoggerFactory.getLogger(TicketDAOTest.class);

    @Autowired
    private TicketDAO ticketDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void findOne() {
        Ticket ticket = ticketDAO.findOne(1, "fullTicket");
        assertTicket(ticket);

        ticket = ticketDAO.findOne(2, "fullTicket");
        assertTicket(ticket);
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
        assertEquals(3, ticketDAO.findAll("fullTicket").size());
    }


    @Test
    @Transactional
    public void findByUserId() {
        assertEquals(1, ticketDAO.listByUserId(6L, "fullTicket").size());
        assertEquals(2, ticketDAO.listByUserId(9L, "fullTicket").size());
    }

    private static void assertTicket(Ticket ticket) {
        assertNotNull(ticket);
        assertNotNull(ticket.getUser());
        assertNotNull(ticket.getArrTrainRoutePiece());
        assertNotNull(ticket.getArrTrainRoutePiece().getStartStation());
        assertNotNull(ticket.getDepTrainRoutePiece());
        assertNotNull(ticket.getDepTrainRoutePiece().getEndStation());
        assertNotNull(ticket.getTrainDate());
        assertNotNull(ticket.getPlace());
        assertNotNull(ticket.getPlace().getCarriage());
    }

    @Test
    @Transactional
    public void findTicketByPlaceId() {

        List<Ticket> ticketList =ticketDAO.findByPlaceId(2208L,
                25L, 37L, 42L,
                32L, "fullTicket");
        assertEquals(2, ticketList.size());

        entityManager.clear();

        ticketList = ticketDAO.findByPlaceId(2210L,
                25L, 37L, 39L,
                32L, "fullTicket");
        assertEquals(0, ticketList.size());
    }

    @Test
    @Transactional
    public void amountTicketByTrainIdAndDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2019-11-10");
        assertEquals(3, (long) ticketDAO.ticketCountWithTrainIdAndDate(25L, date));

    }

    @Test
    @Transactional
    public void amountTicketByStationIdAndDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2019-11-10");
        assertEquals(1, (long) ticketDAO.ticketAmountOnStationIdAndDate(16L, date));

    }

}
