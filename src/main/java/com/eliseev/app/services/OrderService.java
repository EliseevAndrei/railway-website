package com.eliseev.app.services;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.repository.custom.StationStopTimeDAO;
import com.eliseev.app.services.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private StationStopTimeDAO stationStopTimeDAO;
    private TicketService ticketService;


    @Autowired
    public OrderService(StationStopTimeDAO stationStopTimeDAO,
                        TicketService ticketService) {
        this.stationStopTimeDAO = stationStopTimeDAO;
        this.ticketService = ticketService;
    }


    @Transactional
    public Ticket orderTicket(OrderDTO orderDTO) {

        Ticket ticket = orderDTO.getTicket();

        switch(ticket.getSeatType()) {
            case "Купе":
                stationStopTimeDAO.orderCoupePlace(orderDTO.getTrainDateId(), orderDTO.getStartRoutePieceSerialNumber(),
                        orderDTO.getEndRoutePieceSerialNumber());
                break;
            case "Плацкарт":
                stationStopTimeDAO.orderLyingPlace(orderDTO.getTrainDateId(), orderDTO.getStartRoutePieceSerialNumber(),
                        orderDTO.getEndRoutePieceSerialNumber());
                break;
            case "Общее":
                stationStopTimeDAO.orderCommonPlace(orderDTO.getTrainDateId(), orderDTO.getStartRoutePieceSerialNumber(),
                        orderDTO.getEndRoutePieceSerialNumber());
                break;

        }
        return ticketService.create(ticket);
    }

}
