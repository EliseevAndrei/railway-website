package com.eliseev.app.services;

import com.eliseev.app.dto.TicketDto;
import com.eliseev.app.dto.mapper.TicketMapper;
import com.eliseev.app.exception.custom.NotUniqueTicket;
import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.User;
import com.eliseev.app.repository.custom.StationStopTimeDAO;
import com.eliseev.app.repository.custom.TicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService extends AbstractService<Ticket, TicketDto, TicketDAO> {

    private TicketMapper ticketMapper;
    private StationStopTimeDAO stationStopTimeDAO;

    @Autowired
    public TicketService(TicketDAO ticketDAO,
                         TicketMapper ticketMapper,
                         StationStopTimeDAO stationStopTimeDAO) {
        super(ticketDAO, ticketMapper);
        this.ticketMapper = ticketMapper;
        this.stationStopTimeDAO = stationStopTimeDAO;
    }

    @Transactional(readOnly = true)
    public List<TicketDto> listForUser(User user) {
        return ticketMapper.toDto(
                dao.listByUserId(user.getId(), "fullTicket"),
                new ArrayList<>()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<TicketDto> list() {
        return ticketMapper.toDto(
                super.dao.findAll("fullTicket"),
                new ArrayList<>()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public TicketDto get(long id) {
        return ticketMapper.toDto(
                super.dao.findOne(id, "fullTicket"));
    }

    @Override
    public TicketDto create(TicketDto dto) {

        List<Ticket> tickets = super.dao.findByPlaceId(
                dto.getPlace().getId(),
                dto.getTrain().getId(),
                dto.getDepTrainRoutePieceId(),
                dto.getArrTrainRoutePieceId(),
                dto.getTrainDateId(),
                "fullTicket");

        if (tickets.size() > 0) {
            throw new NotUniqueTicket("Что-то пошло не так. Попробуйте позже");
        }
        return super.create(dto);
    }

    @Transactional(readOnly = true)
    public Long ticketAmountOnTrain(Long trainId) {
        return dao.ticketCountWithTrainIdAndDate(trainId, new Date());
    }

    @Transactional(readOnly = true)
    public long ticketAmountOnStation(Long stationId) {
        return dao.ticketAmountOnStationIdAndDate(stationId, new Date());
    }

    @Transactional(readOnly = true)
    public long ticketAmountOnTrainDate(Long trainDateId) {
        return dao.ticketAmountOnTrainDateIdAndDate(trainDateId, new Date());
    }
}
