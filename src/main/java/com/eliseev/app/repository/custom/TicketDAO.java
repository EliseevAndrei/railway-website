package com.eliseev.app.repository.custom;

import com.eliseev.app.models.Ticket;
import com.eliseev.app.repository.IDAO;

import java.util.Date;
import java.util.List;

public interface TicketDAO extends IDAO<Ticket> {
    List<Ticket> listByUserId(Long id, String graphName);
    List<Ticket> findByPlaceId(Long placeId,
                               Long trainId,
                               Long depTrainRoutePiece,
                               Long arrTrainRoutePiece,
                               Long trainDateId,
                               String graphName);
    Long ticketCountWithTrainIdAndDate(Long trainId, Date date);
    Long ticketAmountOnStationIdAndDate(Long stationId, Date date);

    long ticketAmountOnTrainDateIdAndDate(Long trainDateId, Date date);
}
