package com.eliseev.app.services;

import com.eliseev.app.dto.CarriageDto;
import com.eliseev.app.dto.SimpleTrainDto;
import com.eliseev.app.dto.TrainDto;
import com.eliseev.app.dto.mapper.CarriageMapper;
import com.eliseev.app.dto.mapper.SimpleTrainMapper;
import com.eliseev.app.dto.mapper.TrainMapper;
import com.eliseev.app.models.Carriage;
import com.eliseev.app.models.Place;
import com.eliseev.app.models.Train;
import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.repository.custom.TrainDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
        Train trainEntity = trainMapper.toEntity(train);


        if (trainEntity.getTrainRoutePieceList().size() > 0) {
            for (TrainRoutePiece trainRoutePiece : trainEntity.getTrainRoutePieceList()) {
                trainRoutePiece.setTrain(trainEntity);
            }
        }
        for(Carriage carriage : trainEntity.getCarriages()) {
            carriage.setTrain(trainEntity);
            for (Place place : carriage.getPlaces()) {
                place.setCarriage(carriage);
            }
        }

        return trainMapper.toDto(super.dao.save(trainEntity));
    }

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

        return carriageMapper.toDto(
                super.dao.getCarriages(trainId, trainDateId, depRoutePieceSerialNumber, arrRoutePieceSerialNumber, "fullCarriage"),
                new ArrayList<>()
        );
    }

    @Transactional(readOnly = true)
    public List<CarriageDto> getCarriages(long trainId) {
        return carriageMapper.toDto(
                super.dao.getCarriages(trainId, "fullCarriage"),
                new ArrayList<>()
        );

    }



}
