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

    @Autowired
    public TrainRoutePieceService(TrainRoutePieceDAO dao,
                                  TrainRoutePieceMapper trainRoutePieceMapper) {
        super(dao, trainRoutePieceMapper);
        this.trainRoutePieceMapper = trainRoutePieceMapper;
    }

    private Logger logger = LoggerFactory.getLogger(TrainRoutePieceService.class);

    @Transactional
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
    public List<TrainRoutePieceDto> list() {
        return trainRoutePieceMapper.toDto(
                dao.findAll("fullTrainRoutePiece"),
                new ArrayList<>()
        );
    }

    @Override
    public TrainRoutePieceDto get(long id) {
        return trainRoutePieceMapper.toDto(dao.findOne(id, "fullTrainRoutePiece"));
    }
}

