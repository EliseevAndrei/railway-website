package com.eliseev.app.services;

import com.eliseev.app.dto.StationDto;
import com.eliseev.app.dto.mapper.StationMapper;
import com.eliseev.app.models.Station;
import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.repository.custom.StationDAO;
import com.eliseev.app.repository.custom.TrainRoutePieceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StationService extends AbstractService<Station, StationDto, StationDAO> {

    private StationMapper stationMapper;
    private TrainRoutePieceDAO trainRoutePieceDAO;
    private TrainRoutePieceService trainRoutePieceService;

    @Autowired
    public StationService(StationDAO dao, StationMapper stationMapper, TrainRoutePieceDAO trainRoutePieceDAO,
                          TrainRoutePieceService trainRoutePieceService) {
        super(dao, stationMapper);
        this.stationMapper = stationMapper;
        this.trainRoutePieceDAO = trainRoutePieceDAO;
        this.trainRoutePieceService = trainRoutePieceService;
    }

    @Override
    @Transactional
    public void delete(long id) {
        List<TrainRoutePiece> trainRoutePieces1 = trainRoutePieceDAO.findByStartStationId(id);
        for (TrainRoutePiece trainRoutePiece : trainRoutePieces1) {
            trainRoutePieceService.deleteStartStationFromTrainRoutePiece(trainRoutePiece.getId());
        }

        List<TrainRoutePiece> trainRoutePieces2 = trainRoutePieceDAO.findByEndStationId(id);
        for (TrainRoutePiece trainRoutePiece : trainRoutePieces2) {
            trainRoutePieceService.deleteEndStationFromTrainRoutePiece(trainRoutePiece.getId());
        }
        super.delete(id);
    }
}
