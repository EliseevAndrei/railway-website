package com.eliseev.app.dto.mapper;


import com.eliseev.app.dto.TicketDto;
import com.eliseev.app.models.Place;
import com.eliseev.app.models.Ticket;
import com.eliseev.app.models.TrainDate;
import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class TicketMapper extends AbstractMapper<Ticket, TicketDto> {

    private final ModelMapper mapper;
    private final SimpleTrainMapper trainMapper;
    private final StationMapper stationMapper;
    private final SimpleCarriageMapper carriageMapper;

    @Autowired
    public TicketMapper(ModelMapper mapper, SimpleTrainMapper trainMapper,
                        StationMapper stationMapper, SimpleCarriageMapper carriageMapper) {
        super(Ticket.class, TicketDto.class);
        this.mapper = mapper;
        this.trainMapper = trainMapper;
        this.stationMapper = stationMapper;
        this.carriageMapper = carriageMapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Ticket.class, TicketDto.class)
                .addMappings(m -> {
                    m.skip(TicketDto::setUserId);
                    m.skip(TicketDto::setTrainDateId);
                    m.skip(TicketDto::setDepTrainRoutePieceId);
                    m.skip(TicketDto::setArrTrainRoutePieceId);
                    m.skip(TicketDto::setTrain);
                    m.skip(TicketDto::setDepStation);
                    m.skip(TicketDto::setArrStation);
                    m.skip(TicketDto::setCarriage);
                })
                .setPostConverter(toDtoConverter());

        mapper.createTypeMap(TicketDto.class, Ticket.class)
                .addMappings(m ->  {
                    m.skip(Ticket::setTrainDate);
                    m.skip(Ticket::setDepTrainRoutePiece);
                    m.skip(Ticket::setArrTrainRoutePiece);
                    m.skip(Ticket::setPlace);
                    m.skip(Ticket::setUser);
                })
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(Ticket source, TicketDto destination) {
        if (source == null) {
            return;
        }
        destination.setUserId(
                Objects.isNull(source.getUser()) ? null : source.getUser().getId()
        );
        destination.setTrainDateId(
                Objects.isNull(source.getTrainDate()) ? null : source.getTrainDate().getId()
        );
        destination.setDepTrainRoutePieceId(
                Objects.isNull(source.getDepTrainRoutePiece()) ? null : source.getDepTrainRoutePiece().getId()
        );
        destination.setArrTrainRoutePieceId(
                Objects.isNull(source.getArrTrainRoutePiece()) ? null : source.getArrTrainRoutePiece().getId()
        );
        destination.setTrain(
                Objects.isNull(source.getTrainDate()) ? null : trainMapper.toDto(source.getTrainDate().getTrain())
        );
        destination.setDepStation(
                Objects.isNull(source.getDepTrainRoutePiece())
                        ? null
                        : stationMapper.toDto(source.getDepTrainRoutePiece().getStartStation())
        );
        destination.setArrStation(
                Objects.isNull(source.getArrTrainRoutePiece())
                        ? null
                        : stationMapper.toDto(source.getArrTrainRoutePiece().getEndStation())
        );
        destination.setCarriage(
                Objects.isNull(source.getPlace()) ? null : carriageMapper.toDto(source.getPlace().getCarriage())
        );
    }


    @Override
    void mapSpecificFields(TicketDto source, Ticket destination) {
        TrainDate trainDate = new TrainDate();
        trainDate.setId(source.getTrainDateId());
        destination.setTrainDate(trainDate);

        TrainRoutePiece depTrainRoutePiece = new TrainRoutePiece();
        depTrainRoutePiece.setId(source.getDepTrainRoutePieceId());
        destination.setDepTrainRoutePiece(depTrainRoutePiece);

        TrainRoutePiece arrTrainRoutePiece = new TrainRoutePiece();
        arrTrainRoutePiece.setId(source.getArrTrainRoutePieceId());
        destination.setArrTrainRoutePiece(arrTrainRoutePiece);

        Place place = new Place();
        place.setId(source.getPlace().getId());
        destination.setPlace(place);

        User user = new User();
        user.setId(source.getUserId());
        destination.setUser(user);
    }
}

