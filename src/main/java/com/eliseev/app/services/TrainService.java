package com.eliseev.app.services;

import com.eliseev.app.dto.CarriageDto;
import com.eliseev.app.dto.PlaceDto;
import com.eliseev.app.dto.SimpleTrainDto;
import com.eliseev.app.dto.TrainDto;
import com.eliseev.app.dto.TrainRoutePieceDto;
import com.eliseev.app.dto.mapper.CarriageMapper;
import com.eliseev.app.dto.mapper.SimpleTrainMapper;
import com.eliseev.app.dto.mapper.TrainMapper;
import com.eliseev.app.models.Train;
import com.eliseev.app.repository.custom.TrainDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class TrainService extends AbstractService<Train, SimpleTrainDto, TrainDAO> {

    private Logger logger = LoggerFactory.getLogger(TrainService.class);
    private TrainMapper trainMapper;
    private CarriageMapper carriageMapper;
    private SimpleTrainMapper simpleTrainMapper;

    @Autowired
    public TrainService(TrainDAO dao,
                        TrainMapper trainMapper,
                        CarriageMapper carriageMapper,
                        SimpleTrainMapper simpleTrainMapper) {
        super(dao, simpleTrainMapper);
        this.trainMapper = trainMapper;
        this.carriageMapper = carriageMapper;
        this.simpleTrainMapper = simpleTrainMapper;
    }

    @Transactional
    public TrainDto create(TrainDto train) {
        if (train.getTrainRoutePieceList().size() > 0) {
            for (TrainRoutePieceDto trainRoutePiece : train.getTrainRoutePieceList()) {
                trainRoutePiece.setTrainId(train.getId());
            }
        }
        for(CarriageDto carriage : train.getCarriages()) {
            carriage.setTrainId(train.getId());
            for (PlaceDto place : carriage.getPlaces()) {
                place.setCarriageId(carriage.getId());
            }
        }
        return trainMapper.toDto(super.dao.save(trainMapper.toEntity(train)));
    }

    /*@Transactional
    public List<TrainDto> getTrainsOnStations(String depStation, String arrStation, Date date) {
        logger.info("find trains with stations {}, - {} at date {}", depStation, arrStation, date);
        return super.list();
    }

    @Transactional
    public List<TrainRoutePiece> createRoute(List<TrainRoutePiece> trainRoutePieces, long trainId) {

        Train train = super.dao.findOne(trainId);

        trainRoutePieces.forEach(e -> {
            e.setTrain(train);
        });

        train.getTrainRoutePieceList().addAll(trainRoutePieces);

        return null;
    }*/

    @Transactional(readOnly = true)
    public Map<String, Integer> getFreePlacesAmountForTrainRoute(long trainId,
                                                                 long trainDateId,
                                                                 int depRoutePieceSerialNumber,
                                                                 int arrRoutePieceSerialNumber) {
        return super.dao.getFreePlacesAmountForTrainRoute(trainId, trainDateId,
                depRoutePieceSerialNumber, arrRoutePieceSerialNumber);
    }

    @Transactional(readOnly = true)
    public List<CarriageDto> getCarriages(long trainId, long trainDateId,
                                int depRoutePieceSerialNumber,
                                int arrRoutePieceSerialNumber) {
        return super.dao.getCarriages(trainId, trainDateId, depRoutePieceSerialNumber, arrRoutePieceSerialNumber, "fullCarriage")
                .stream()
                .map(e -> carriageMapper.toDto(e))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CarriageDto> getCarriages(long trainId) {
        return super.dao.getCarriages(trainId, "fullCarriage")
                .stream()
                .map(e -> carriageMapper.toDto(e))
                .collect(Collectors.toList());
    }



}
