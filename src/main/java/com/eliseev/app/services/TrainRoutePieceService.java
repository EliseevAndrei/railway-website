package com.eliseev.app.services;

import com.eliseev.app.models.TrainRoutePiece;
import com.eliseev.app.repository.custom.StationStopTimeDAO;
import com.eliseev.app.repository.custom.TrainRoutePieceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainRoutePieceService extends AbstractService<TrainRoutePiece, TrainRoutePieceDAO> {




    @Autowired
    public TrainRoutePieceService(TrainRoutePieceDAO dao) {
        super(dao);
    }

    private Logger logger = LoggerFactory.getLogger(TrainRoutePieceService.class);

    @Transactional
    public List<TrainRoutePiece> list(long trainId) {
        return super.dao.findByTrainId(trainId);
    }


}

