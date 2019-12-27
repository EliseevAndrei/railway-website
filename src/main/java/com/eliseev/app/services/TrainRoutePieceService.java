package com.eliseev.app.services;

import com.eliseev.app.dto.TrainRoutePieceDto;
import com.eliseev.app.dto.mapper.TrainRoutePieceMapper;
import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.repository.custom.TrainRoutePieceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainRoutePieceService extends AbstractService<TrainRoutePiece, TrainRoutePieceDto, TrainRoutePieceDAO> {

    private TrainRoutePieceMapper trainRoutePieceMapper;
    private Logger logger = LoggerFactory.getLogger(TrainRoutePieceService.class);

    @Autowired
    public TrainRoutePieceService(TrainRoutePieceDAO dao,
                                  TrainRoutePieceMapper trainRoutePieceMapper) {
        super(dao, trainRoutePieceMapper);
        this.trainRoutePieceMapper = trainRoutePieceMapper;
    }


    @Transactional(readOnly = true)
    public List<TrainRoutePieceDto> list(long trainId) {
        return trainRoutePieceMapper.toDto(
                super.dao.findByTrainId(trainId, "fullTrainRoutePiece"),
                new ArrayList<>()
        );
    }

    @Transactional(readOnly = true)
    public TrainRoutePieceDto findByTrainIdAndStartStationId(long trainId, long startStationId) {
        return trainRoutePieceMapper.toDto(
                super.dao.findByTrainIdAndStartStationId(trainId, startStationId, "fullTrainRoutePiece"));
    }

    @Transactional(readOnly = true)
    public TrainRoutePieceDto findByTrainIdAndEndStationId(long trainId, long endStationId) {
        return trainRoutePieceMapper.toDto(
                super.dao.findByTrainIdAndEndStationId(trainId, endStationId, "fullTrainRoutePiece"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainRoutePieceDto> list() {
        return trainRoutePieceMapper.toDto(
                dao.findAll("fullTrainRoutePiece"),
                new ArrayList<>()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public TrainRoutePieceDto get(long id) {
        return trainRoutePieceMapper.toDto(dao.findOne(id, "fullTrainRoutePiece"));
    }

    @Transactional
    public void deleteStartStationFromTrainRoutePiece(long trainRoutePieceId) {
        TrainRoutePiece trainRoutePiece = dao.findOne(trainRoutePieceId, "fullTrainRoutePiece");

        long trainId = trainRoutePiece.getTrain().getId();
        int serialNumber = trainRoutePiece.getSerialNumber();

        if (trainRoutePiece.getSerialNumber() > 1) {

            TrainRoutePiece newTrainRoutePiece = new TrainRoutePiece();
            TrainRoutePiece above = dao.findByTrainIdAndEndStationId(
                    trainRoutePiece.getTrain().getId(), trainRoutePiece.getStartStation().getId(), "fullTrainRoutePiece");

            newTrainRoutePiece.setTrain(trainRoutePiece.getTrain());
            newTrainRoutePiece.setSerialNumber(trainRoutePiece.getSerialNumber() - 1);
            newTrainRoutePiece.setStartStation(above.getStartStation());
            newTrainRoutePiece.setDistance(trainRoutePiece.getDistance() + above.getDistance());
            newTrainRoutePiece.setEndStation(trainRoutePiece.getEndStation());
            dao.delete(trainRoutePieceId);
            dao.delete(above.getId());
            dao.save(newTrainRoutePiece);

        } else {
            dao.delete(trainRoutePieceId);
        }

        dao.updateAllAboveSerialNumber(trainId, serialNumber);

    }

    @Transactional
    public void deleteEndStationFromTrainRoutePiece(long trainRoutePieceId) {
        TrainRoutePiece trainRoutePiece = dao.findOne(trainRoutePieceId, "fullTrainRoutePiece");

        long trainId = trainRoutePiece.getTrain().getId();
        int serialNumber = trainRoutePiece.getSerialNumber();

        TrainRoutePiece under = dao.findByTrainIdAndStartStationId(
                trainRoutePiece.getTrain().getId(), trainRoutePiece.getEndStation().getId(), "fullTrainRoutePiece");

        if (under != null) {
            TrainRoutePiece newTrainRoutePiece = new TrainRoutePiece();

            newTrainRoutePiece.setTrain(trainRoutePiece.getTrain());
            newTrainRoutePiece.setSerialNumber(trainRoutePiece.getSerialNumber());
            newTrainRoutePiece.setStartStation(trainRoutePiece.getStartStation());
            newTrainRoutePiece.setDistance(trainRoutePiece.getDistance() + under.getDistance());
            newTrainRoutePiece.setEndStation(under.getEndStation());

            dao.delete(trainRoutePieceId);
            dao.delete(under.getId());
            dao.save(newTrainRoutePiece);
            dao.updateAllAboveSerialNumber(trainId, serialNumber + 1);

        } else {
            dao.delete(trainRoutePieceId);
        }

    }
}

